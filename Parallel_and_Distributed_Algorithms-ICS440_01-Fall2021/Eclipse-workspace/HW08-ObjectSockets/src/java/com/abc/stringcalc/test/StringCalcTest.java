package com.abc.stringcalc.test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import com.abc.stringcalc.client.*;
import com.abc.stringcalc.dto.*;
import com.abc.stringcalc.server.*;
import com.abc.stringcalc.util.*;
import com.programix.gui.*;
import com.programix.testing.*;

public class StringCalcTest extends AbstractRegression {
    private static final String HOST = "localhost";
    private static final int PORT = 4501;

    private LogPane testLogPane;
    private LogPane serverLogPane;
    private LogPane clientALogPane;
    private LogPane clientBLogPane;

    public StringCalcTest() {
        testLogPane = new LogPane("Regression Output");
        serverLogPane = new LogPane("StringCalcServer");
        clientALogPane = new LogPane("StringCalcClient-A");
        clientBLogPane = new LogPane("StringCalcClient-B");
        buildUI();

        setOutput(testLogPane);
    }

    private void buildUI() {
        JPanel mainP = new JPanel(new GridLayout(2, 0, 0, 0));
        mainP.add(testLogPane);
        mainP.add(serverLogPane);
        mainP.add(clientALogPane);
        mainP.add(clientBLogPane);

        JFrame f = new JFrame("StringCalcTest");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setContentPane(mainP);
        GuiTools.setSizeToMax(f);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @SuppressWarnings("unused")
    @Override
    protected void runBundles() throws Exception {
        new ServerLauncher();

        Thread.sleep(1000);
        StringCalcClient clientA = new ClientLauncher(clientALogPane).getClient();

        outln("Testing clientA...");
        runSimpleClientTest(clientA);

        StringCalcClient clientB = new ClientLauncher(clientBLogPane).getClient();

        outln("Testing clientB...");
        runSimpleClientTest(clientB);

        // Be sure that we can go back to A and then back to B
        outln("Confirming that after starting clientB, clientA still works...");
        runSimpleClientTest(clientA);
        outln("Confirming that clientB still works...");
        runSimpleClientTest(clientB);

        outln("Disconnecting clientA...");
        clientA.disconnect();

        outln("Confirming that after disconnecting clientA, " + "clientB still works...");
        runSimpleClientTest(clientB);

        runClientTestSetA(clientB);

        outln("Disconnecting clientB...");
        clientB.disconnect();
    }

    private void runSimpleClientTest(StringCalcClient client) {
        String[] list1 = { "a", "ab", "abc", "abcd" };
        testCalculate(client, list1, true);
    }

    private void runClientTestSetA(StringCalcClient client) {
        String[] list2 =
            new String[] { "Hello!", "calculating", "lengths", null, "of", "strings", "is", null, "fun", null };
        testCalculate(client, list2, true);
        testCalculate(client, list2, false);

        String[] list3 = new String[0];
        testCalculate(client, list3, true);

        String[] list4 = null;
        testCalculate(client, list4, true);

        String[] list5 = new String[] { null, null, null };
        testCalculate(client, list5, true);
        testCalculate(client, list5, false);

        String[] list6 = new String[] { "", "abcdef", "", null };
        testCalculate(client, list6, true);
        testCalculate(client, list6, false);
    }

    private void testCalculate(StringCalcClient client, String[] s, boolean ignoreNulls) {

        try {
            outln("==== in testCalculate() ====");
            outln("   ignoreNulls=" + ignoreNulls);
            outln("Strings used:");

            if (s == null) {
                outln("  null reference passed in");
            } else {
                outln("  s.length=" + s.length);

                for (int i = 0; i < s.length; i++ ) {
                    outln("  s[" + i + "] = " + ( (s[i] == null) ? "null" : "\"" + s[i] + "\""));
                }
            }

            StringCalcRequest req = new StringCalcRequest();
            req.setData(s);
            req.setIgnoreNulls(ignoreNulls);

            StringCalcResponse res = client.calculate(req);

            StringCalculator sc = new StringCalculator(s, ignoreNulls);

            outln("Validating the response");

            outln("  getTotalLength()=", res.getTotalLength(), sc.getTotalLength());
            outln("  getMinLength()=", res.getMinLength(), sc.getMinLength());
            outln("  getMaxLength()=", res.getMaxLength(), sc.getMaxLength());
            outln("  getAverageLength()=", res.getAverageLength(), sc.getAverageLength(), 0.001);
            outln("  getCount()=", res.getCount(), sc.getCount());
        } catch (IOException x) {
            printStackTrace(x);
        }
    }

    public static void main(String[] args) {
        AbstractRegression ar = new StringCalcTest();
        ar.runTests();
    }

    private class ServerLauncher implements Runnable {
        public ServerLauncher() {
            outln("Attempting to startup StringCalcServer...");
            new Thread(this, "StringCalcServer-Launcher").start();
        }

        @SuppressWarnings("unused")
        @Override
        public void run() {
            try {
                new StringCalcServer(PORT, serverLogPane);
            } catch (IOException x) {
                serverLogPane.out(x);
            }
        }
    } // class ServerLauncher


    private class ClientLauncher implements Runnable {
        private Log log;
        private StringCalcClient client;

        public ClientLauncher(Log log) {
            this.log = log;
            outln("Attempting to startup StringCalcClient...");
            new Thread(this, "StringCalcClient-Launcher").start();
        }

        @Override
        public void run() {
            try {
                setClient(new StringCalcClient(HOST, PORT, log));
            } catch (IOException x) {
                log.out(x);
            }
        }

        private synchronized void setClient(StringCalcClient client) {
            this.client = client;
            notifyAll();
        }

        public synchronized StringCalcClient getClient() throws InterruptedException {

            while (client == null) {
                wait();
            }

            return client;
        }
    } // class ClientLauncher
}

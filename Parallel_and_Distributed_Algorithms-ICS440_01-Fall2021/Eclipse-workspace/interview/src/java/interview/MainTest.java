package interview;

class MainTest extends Thread {

    // @Test
    // void test() {
    // assertEquals("test", new Main().testString());
    // }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++ ) {
            System.out.println("Run: " + i);
        }
    }

    public static void main(String[] args) {
        MainTest mt = new MainTest();
        mt.start();
        mt.run();
        for (int i = 0; i <= 50; i++ ) {
            System.out.println("Main: " + i);
        }
    }

}

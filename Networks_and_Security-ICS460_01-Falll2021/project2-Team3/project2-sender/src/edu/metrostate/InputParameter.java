package edu.metrostate;

/**
 * ICS460-01 Fall2021, Project 2, stop and wait, sender program - client side.
 * Instructor: Damodar Chetty Write by Team #3: Nalongsone Danddank, Asha Hassan
 */
// singleton class create for get the parameters from user input by command line
// and some other parameter that need to use on the entry program.
public class InputParameter {
	private static InputParameter singleton;

	// initilize default to all parameter if user input nothing.
	String senderIpAddress = "localhost";
	int senderPort = 58972;
	String filePath = System.getProperty("user.dir") + "/";
	String fileName = "cat.jpg";
	int packetSize = 500;
	int totalPacket = 0;
	int timeoutInterval = 2000;
	String receiverIpAddress = "localhost";
	int receiverPort = 58973;
	double percentError = 0.25;

	private InputParameter() {
	}

	// create only one instance object to use for entry program.
	public static InputParameter instance() {
		if (singleton == null)
			singleton = new InputParameter();
		return singleton;
	}

	// processing get argument from user input by command line. and if user typing
	// wrong than throw an InputException.
	public void getArgs(String[] args) throws InputException {
		if (args.length == 0)
			return;
		if (args.length == 1) {
			if (args[0].matches(".+\\.\\w+")) {
				fileName = args[0];
				return;
			}
			throw new InputException("Type file name wrong format.");
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s")) {
				if (args[i + 1].matches("\\d+")) {
					packetSize = Integer.parseInt(args[i + 1]);
				} else {
					throw new InputException("Type wrong -s size packet number format: " + args[i + 1]);
				}
			} else if (args[i].equals("-t")) {
				if (args[i + 1].matches("\\d+")) {
					timeoutInterval = Integer.parseInt(args[i + 1]);
				} else {
					throw new InputException("Type wrong -t  timeout interval: " + args[i + 1]);
				}
			} else if (args[i].equals("-d")) {
				if (args[i + 1].matches("\\d+\\.\\d+")) {
					percentError = Double.parseDouble(args[i + 1]);
				} else {
					throw new InputException("Type wrong -d  percent drop: " + args[i + 1]);
				}
			} else if (args[i].equals("-f")) {
				if (args[i + 1].matches("\\w+\\.\\w+")) {
					fileName = args[i + 1];
				} else {
					throw new InputException("Type file name wrong format: " + args[i + 1]);
				}
			}

		}
		if (args[args.length - 1].matches("\\d+")) {
			receiverPort = Integer.parseInt(args[args.length - 1]);
		} else {
			throw new InputException("Type port number wrong format: " + args[args.length - 1]);
		}
		if (args[args.length - 2].matches("\\d{1,3}:\\d{1,3}:\\d{1,3}:\\d{1,3}")) {
			receiverIpAddress = args[args.length - 2];
		} else if (args[args.length - 2].matches("localhost")) {
			receiverIpAddress = "localhost";
		} else {
			throw new InputException("Type receiver ip address wrong format: " + args[args.length - 2]);
		}

	}

	@Override
	public String toString() {
		return "SenderParameter [fileName=" + fileName + ", filePath=" + filePath + ", packetSize=" + packetSize
				+ ", percentError=" + percentError + ", receiverIpAddress=" + receiverIpAddress + ", receiverPort="
				+ receiverPort + ", senderIpAddress=" + senderIpAddress + ", senderPort=" + senderPort
				+ ", timeoutInterval=" + timeoutInterval + "]";
	}
}

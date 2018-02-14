package P1.iperfer;

import java.io.IOException;

public class TestIperfer {

    private static void printError() {
        System.err.println("Error: missing or additional arguments");
        System.exit(1);
    }

    private static void printPortError() {
        System.err.println("Error: port number must be in the range 1024 to 65535");
        System.exit(-1);
    }

    private static void checkArguments(String[] cargs, boolean isServer, boolean isClient) {

        for(int count = 0; count < cargs.length; count++) {
            if(cargs[count].equals("-s")) {
                if(cargs.length != 3) {
                    printError();
                }
                isServer = true;
                break;
            }
            if(cargs[count].equals("-c")) {
                if(cargs.length != 7) {
                    printError();
                }
                isClient = true;
                break;
            }
        }

        if((isClient || isServer) == false) {
            printError();
        }
    }

    private static void handleArguments(String[] cargs, boolean isServer, boolean isClient) throws IOException {
        long time = 0;
        int port = 0;
        String host = "";

        for(int count = 0; count < cargs.length && cargs[count].startsWith("-"); count++) {
            String arg = cargs[count];
            if (arg.charAt(1) == 'c' || arg.charAt(1) == 's')
                continue;
            else {
                switch (arg) {
                    case "p":
                        port = Integer.parseInt(cargs[count++]);
                        if (port <= 1024 || port >= 65535) {
                            printPortError();
                        }
                        break;
                    case "h":
                        if(!isClient) {
                            printError();
                        } else {
                            host = cargs[count++];
                        }
                        break;
                    case "t":
                        if(!isClient) {
                            printError();
                        } else {
                            time = Integer.parseInt(cargs[count++]);
                        }
                        break;
                    default:
                        printError();
                }
            }
        }

        if(isServer) {
            Server server = new Server(port);
            try {
                server.establishConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(isClient) {

        }
    }

    public static void main(String[] args) throws IOException {
        boolean isServer = false;
        boolean isClient = false;

        checkArguments(args, isServer, isClient);
        handleArguments(args, isServer, isClient);

    }

}

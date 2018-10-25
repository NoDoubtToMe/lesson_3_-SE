import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        public static void main(String[] args) {
            new ClientConsole();
        }
    }

    class ClientConsole {

        private final static String server_add = "localhost";
        private final static int server_port = 8189;
        Socket sock = null;
        Scanner input;
        PrintWriter output;

        ClientConsole() {
            try {
                sock = new Socket(server_add, server_port);
                input = new Scanner(sock.getInputStream());
                output = new PrintWriter((sock.getOutputStream()));
                Scanner scMsg = new Scanner(System.in);
                showMsg();
                while (true) {
                    String message = scMsg.nextLine();
                    output.println(message);
                    output.flush();
                    System.out.println("message sends");
                }
            } catch (IOException e) {
            }
        }
        void showMsg(){new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (input.hasNext()) {
                            System.out.println(" you have a new message");
                            String msg = input.nextLine();
                            System.out.println(msg);

                            if (msg.equalsIgnoreCase("end session")) break;

                        }
                    }
                }
                catch(Exception e){
                }

            }
        }).start();
        }
    }

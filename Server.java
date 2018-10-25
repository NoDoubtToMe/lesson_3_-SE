import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
        public static void main(String[] args) {
            new ServerPart();
        }
    }

    class ServerPart {
        ServerSocket serv = null;
        Socket sock;
        Scanner sc;
        PrintWriter pw;
        Thread thread;
        final SaveToFile save = new SaveToFile();

        ServerPart() {

            thread = new Thread(new Runnable() {
                @Override
                public void run()   {
                    while (true) {
                        if (sc.hasNext()) {
                            System.out.println("thread starts");
                            String str = sc.nextLine();
                            System.out.println("You have a new message");
                            System.out.println(str);
                            try{ save.save(str);}  //Запись в файл
                            catch(IOException e){}
                            if (str.equals("end")) {
                                pw.println("end session");
                                pw.flush();
                                break;
                            }
                        }
                    }
                }
            });

            try {
                serv = new ServerSocket(8189);
                System.out.println("Running the server.... ");
                sock = serv.accept();
                System.out.println("Clieant has connected");
                sc = new Scanner(sock.getInputStream());
                pw = new PrintWriter(sock.getOutputStream());
                save.extract();
                pw.flush();
                thread.start();
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.nextLine();
                    System.out.println(message);
                    pw.println(message);
                    pw.flush();
                    System.out.println("message sends");
                }
            } catch (IOException e) {
            } finally {
                try {
                    serv.close();
                } catch (IOException e) {
                }
            }
        }
    }


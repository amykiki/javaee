package Test.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Amysue on 2016/1/25.
 */
public class Server {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private List<ServerThread> clients;
    public static final int    PORT             = 5656;
    public static final String DISCONNECT_TOKEN = "exit";
    public static final String SERVER_CLOSE     = "SERVER CLOSED";

    public static void main(String[] args) {
        Server s = new Server();
        s.listen();
    }

    public void listen() {
        ServerSocket ss      = null;
        Socket       sClient = null;

        try {
            ss = new ServerSocket(PORT);
            clients = new ArrayList<>();
            while (true) {
                sClient = ss.accept();
                ServerThread st = new ServerThread(sClient);
                Thread       t  = new Thread(st);
                t.start();
                System.out.println(t.getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ServerThread implements Runnable {
        private Socket      sClient;
        private PrintWriter out;
        private Scanner     in;
        private String      clientName;
        private String      user;
        private boolean     shutdown;

        public ServerThread(Socket aClient) throws IOException {
            this.sClient = aClient;
            out = new PrintWriter(sClient.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(sClient.getInputStream()));
            user = in.nextLine();
            clientName = user + "-[" + sClient.getInetAddress().getHostName() + "]" + ":" + sClient.getPort();
            shutdown = false;
            clients.add(this);
        }

        private void stop() {
            shutdown = true;
            clients.remove(this);
            send("LEAVE");
        }

        private void send(String msg) {
            for (ServerThread st : clients) {
                st.out.println(this.user + ":" + msg);
            }
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    recvMsg();
                }
            } catch (NoSuchElementException e) {
                stop();
                System.out.println(clientName + " CLOSED ERROR");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void recvMsg() throws IOException {
            String msg = null;
            System.out.println(clientName + " CONNECT");
            send("Login in");
            while ((msg = in.nextLine()) != null) {
                if (msg.equals(DISCONNECT_TOKEN)) {
                    out.println(SERVER_CLOSE);
                    stop();
                    break;
                }
                System.out.println(sdf.format(new Date()) + ":" + clientName + " sent " + msg);
//                out.println("Received " + msg);
                send(msg);
            }
            System.out.println(clientName + " CLOSED");
        }
    }

}

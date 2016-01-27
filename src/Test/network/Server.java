package Test.network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Amysue on 2016/1/25.
 */
public class Server {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Map<String, ServerThread> clients;
    public static final int    PORT             = 5656;
    public static final String DISCONNECT_TOKEN = "exit";
    public static final String SERVER_CLOSE     = "SERVER CLOSED";
    public static final String ADD_USERS        = "#ADD_USERSLISTS:";
    public static final String DEL_USERS        = "#DEL_USERSLISTS:";
    public static final String TO_USERS_START   = "#TO:";
    public static final String TO_USERS_END     = ":END#";

    public static void main(String[] args) {
        Server s = new Server();
        s.listen();
    }

    public void listen() {
        ServerSocket ss      = null;
        Socket       sClient = null;

        try {
            ss = new ServerSocket(PORT);
            clients = new ConcurrentHashMap<>(new HashMap<>());
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
            synchronized (clients) {
                sendUsers(user);
                clients.put(user, this);
            }
        }

        private void stop() {
            synchronized (clients) {
                shutdown = true;
                clients.remove(user);
                sendMsg("LEAVE");
                deleteUser(user);
            }
        }

        private void send(String msg) {
            synchronized (clients) {
                Set<String> users = clients.keySet();
                for (String userName : users) {
                    ServerThread st = clients.get(userName);
                    st.out.println(msg);
                }
            }
        }

        private void sendMsg(String msg) {
            String msg2 = this.user + ":" + msg;
            send(msg2);
        }

        private void sendUsers(String nUser) {
            Set<String>   users = clients.keySet();
            String        str   = ADD_USERS + nUser;
            StringBuilder sb    = new StringBuilder(str + ",");
            for (String userName : users) {
                clients.get(userName).out.println(str);
                sb.append(userName + ",");
            }
            this.out.println(sb.toString());
        }

        private void deleteUser(String dUser) {
            String msg = DEL_USERS + dUser;
            send(msg);
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
            sendMsg("Login in");
            while ((msg = in.nextLine()) != null) {
                if (msg.equals(DISCONNECT_TOKEN)) {
                    out.println(SERVER_CLOSE);
                    stop();
                    break;
                }
                System.out.println(sdf.format(new Date()) + ":" + clientName + " sent " + msg);
                String users = getUser(msg);
                String uMsg  = formatMsg(msg);
                System.out.println(users);
                if (users.equals("All")) {
                    sendMsg(uMsg + " [group msg]");
                }else {
                    sendPrivateMsg(uMsg, users);
                }
            }
            System.out.println(clientName + " CLOSED");
        }

        private void sendPrivateMsg(String uMsg, String user) {
            String[] users = user.split(",");
            synchronized (clients) {
                for (String u : users) {
                    if (u.equals(this.user)) continue;
                    clients.get(u).out.println(this.user + ":" + uMsg + " [private msg]");
                }
                this.out.println(this.user + ":" + uMsg + " [private msg]");
            }
        }

        private String getUser(String msg) {
            String users = msg.substring(TO_USERS_START.length(), msg.indexOf(TO_USERS_END));
            return users;
        }

        private String formatMsg(String msg) {
            String uMsg = msg.substring(msg.indexOf(TO_USERS_END) + TO_USERS_END.length());
            return uMsg;
        }
    }

}

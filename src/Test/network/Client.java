package Test.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Amysue on 2016/1/25.
 */
public class Client {
    private Socket  sClient  = null;
    private Scanner in       = new Scanner(System.in);
    private String  name     = null;
    private boolean shutdown = false;
    PrintWriter    pw;
    BufferedReader br;

    public Client() throws IOException {
        System.out.println("Please input your name:");
        name = in.nextLine();
    }

    public void connect() {
        try {
            sClient = new Socket(InetAddress.getLocalHost(), 5656);
            br = new BufferedReader(new InputStreamReader(sClient.getInputStream()));
            pw = new PrintWriter(sClient.getOutputStream(), true);
            pw.println(name);
            ClientThread cRead = new ClientThread();
            new Thread(cRead).start();
            String input = null;
            while (!shutdown) {
                input = in.nextLine();
                if (shutdown) break;
                pw.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            try {
                if (sClient != null) {
                    sClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        shutdown = true;
    }
    private class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    String rs = br.readLine();
                    if (rs.equals("SERVER CLOSED")) {
                        stop();
                        break;
                    }
                    System.out.println(rs);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client cl = new Client();
        cl.connect();
    }
}

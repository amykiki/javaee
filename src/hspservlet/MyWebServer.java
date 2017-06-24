package hspservlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zoushumin on 2017/6/24.
 */
public class MyWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8090);
        System.out.println("Waiting for connect...");
        while (true) {
            Socket socket = ss.accept();
            OutputStream output = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new FileReader("D:\\codeProject\\git_repo\\javaee\\src\\resources\\helloworld.html"));
            String line;
            while ((line = br.readLine()) != null) {
                output.write(line.getBytes());
            }
            br.close();
            output.close();
            socket.close();
        }
    }
}

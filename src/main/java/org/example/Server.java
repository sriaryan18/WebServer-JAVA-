package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        serverSocket.setSoTimeout(20000);
        while (true){
             System.out.println("Server is listening at port "+port);
             Socket acceptConnection = serverSocket.accept();
             System.out.println("Connection accepted from client "+acceptConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptConnection.getOutputStream(),true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptConnection.getInputStream()));
             toClient.println("Hello from the server");
             String line = fromClient.readLine();
             System.out.println("Request from Client"+line);
                toClient.close();
                fromClient.close();
                acceptConnection.close();
        }

    }


    public static void main(String[] args) {
        Server server = new Server();
        try{
        server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
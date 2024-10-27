package org.example.Multithreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){
        return (socket -> {
            try{
                PrintWriter toClient = new PrintWriter(socket.getOutputStream());
                toClient.println("Hello from sever");
                toClient.close();
                socket.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    public void run(Server server) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
        System.out.println("Server is listening on port"+port);
        while (true){
            Socket acceptedConnection = serverSocket.accept();
            Thread thread  = new Thread(()->server.getConsumer().accept(acceptedConnection));
            thread.start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

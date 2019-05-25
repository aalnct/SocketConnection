package com.learning.socketprogramming.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by AmitAgarwal on 5/25/19.
 */
public class Server {

    public static void main(String [] args){
        // server is listening on port 5056

        ServerSocket serverSocket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try{
            serverSocket = new ServerSocket(5056);

            while(true){
                Socket socket = null;
                socket = serverSocket.accept();
                System.out.println("A new client is connected :" +socket);

                // obtaining input and output stream

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                // assigning a new thread for this client

                Thread thread = new ClientHandler(socket,dataInputStream,dataOutputStream);

                // invoking start method
                thread.start();
            }
        }catch (IOException ioException){
            System.out.print(ioException);
        }


    }

}

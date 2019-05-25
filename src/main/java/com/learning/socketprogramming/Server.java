package com.learning.socketprogramming;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by AmitAgarwal on 5/25/19.
 */
public class Server {

    // initialize socket and input stream

    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream dataInputStream = null;

    public static void main(String [] args){
        Server server = new Server(5000);
    }


    // constructor with port
    public Server(int port){

        // start server and wait for connection

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started");

            System.out.println("Waiting for a client");

            socket = serverSocket.accept();
            System.out.println("Client Accepted");

            // takes input from client socket
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent

            while(!line.equals("Over")) try{
                line = dataInputStream.readUTF();
                System.out.println(line);
            }catch (IOException ioException){
                System.out.println(ioException);
            }

            System.out.print("Closing connections");

            socket.close();
            dataInputStream.close();

        }catch(IOException ioException){
            System.out.println(ioException);
        }
    }
}

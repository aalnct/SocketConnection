package com.learning.socketprogramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by AmitAgarwal on 5/25/19.
 */
public class Client {


    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    public static void main(String [] args){
        Client client  = new Client("127.0.0.1",5000);
    }

    public Client(String address, int port){

        //establish a connection

        try{

            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal

            dataInputStream = new DataInputStream(System.in);

            // sends output to socket

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        }catch(UnknownHostException unknownException){
            System.out.println(unknownException);
        }
        catch(IOException ioException){
            System.out.println(ioException);
        }

        // String to read input from message

        String line = "";

        // keep reading until over is input

        while(!line.equals("Over")) try {
            line = dataInputStream.readLine();
            dataOutputStream.writeUTF(line);

        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        // close the connection
        try{

            dataInputStream.close();
            dataOutputStream.close();
            socket.close();

        }catch(IOException ioException){
                System.out.println(ioException);
        }

    }

}

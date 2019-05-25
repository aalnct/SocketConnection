package com.learning.socketprogramming.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by AmitAgarwal on 5/25/19.
 */
public class Client {

    public static void main(String [] args){

        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try{
            Scanner scanner = new Scanner(System.in);
            InetAddress inetAddress = InetAddress.getByName("localhost");

            socket = new Socket(inetAddress,5056);

            // obtaining the input and output stream

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            /**
             * the following loop with handle the exchange the information b/w client and client handler
             */

            while(true){
                System.out.println(dataInputStream.readUTF());
                String toSend = scanner.nextLine();
                dataOutputStream.writeUTF(toSend);

                if(toSend.equalsIgnoreCase("Exit")){
                    System.out.println("Closing the connection");
                    socket.close();
                    System.out.println("Connection Closed");

                    break;
                }

                String received = dataInputStream.readUTF();
                System.out.println(received);
            }

            // closing resource
            scanner.close();
            dataInputStream.close();
            dataOutputStream.close();

        }catch(Exception exception){
            System.out.println(exception);
        }
    }



}

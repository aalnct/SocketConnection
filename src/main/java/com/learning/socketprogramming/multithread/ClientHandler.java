package com.learning.socketprogramming.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AmitAgarwal on 5/25/19.
 */
public class ClientHandler extends  Thread{

    DateFormat forDate =  new SimpleDateFormat("yyyy/MM/dd");
    DateFormat forTime = new SimpleDateFormat("hh:mm:ss");

    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;

    public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream){

        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public void run() {
        String received = null;
        String toReturn = null;

        while(true){
            try{
                // Ask user what he wants

                dataOutputStream.writeUTF("What do you want? [Date | Time]..\\n\"+ \n" +
                        "\" Type Exit to terminate connection.");

                // receive the answer from client

                received = dataInputStream.readUTF();

                if(received.equalsIgnoreCase("Exit")){

                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");

                    this.socket.close();

                    System.out.println("Connection closed");
                    break;
                }

                Date date = new Date();

                if (received.equals("Date")) {
                    toReturn = forDate.format(date);
                    dataOutputStream.writeUTF(toReturn);

                    break;
                }

                else if(received.equals("Time")){
                    toReturn = forTime.format(date);
                    dataOutputStream.writeUTF(toReturn);

                    break;
                }

                else if(!received.equalsIgnoreCase("Date") && !received.equalsIgnoreCase("Time")){
                    dataOutputStream.writeUTF("Invalid Input");
                    break;
                }

                try {
                    this.dataInputStream.close();
                    this.dataOutputStream.close();
                }catch (IOException ioException){
                    System.out.println(ioException);
                }

            }catch(IOException exception){
                System.out.println(exception);
            }
        }
    }
}

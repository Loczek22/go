package org.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            while (true) {
                Socket socket1 = serverSocket.accept();
                OutputStream output1 = socket1.getOutputStream();
                PrintWriter out1 = new PrintWriter(output1, true);

                System.out.println("Gracz 1 dolaczyl");
                out1.println("Oczekiwanie na drugiego gracza");

                Socket socket2 = serverSocket.accept();
                OutputStream output2 = socket1.getOutputStream();
                PrintWriter out2 = new PrintWriter(output2, true);

                System.out.println("Gracz 2 dolaczyl");
                out1.println("Rozpoczynanie gry");
                out2.println("Rozpoczynanie gry");

                new Game(socket1, socket2).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

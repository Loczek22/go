package Server;

import GUI.BoardSizeSelector;

import java.net.*;
import java.io.*;


public class Client {
    public void startClient() {

        try  {

            Socket socket = new Socket("localhost", 4444);
            // Wysylanie do serwera
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // czekanie az klient wybierze rozmiar planszy
            while (BoardSizeSelector.getSelectedBoardSize() == 0) {
                Thread.sleep(100);
            }

            out.println(BoardSizeSelector.getSelectedBoardSize());

            // Pierwszy komunikat od serwera (czy znaleziono drugiego gracza)
            String first_message = in.readLine();
            System.out.println(first_message);

            if("Oczekiwanie na drugiego gracza".equals(first_message)) {
                System.out.println(in.readLine());
            }
            System.out.println(in.readLine());

            //do {
                //TODO: logika dla gracza

                // Wysylanie do serwera
                //out.println(\);
                // Odbieranie z serwera
                //in.readLine();

            //} while ("Server.Game over".equals(messageFromServer));
            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server.Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
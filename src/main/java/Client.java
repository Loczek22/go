import java.net.*;
import java.io.*;


public class Client {

    public static void main(String[] args) {

        try  {

            Socket socket = new Socket("localhost", 4444);
            // Wysylanie do serwera
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Pierwszy komunikat od serwera (czy znaleziono drugiego gracza)
            String first_message = in.readLine();
            System.out.println(first_message);

            if("Oczekiwanie na drugiego gracza".equals(first_message)) {
                System.out.println(in.readLine());
            }


            //do {
                //TODO: logika dla gracza

                // Wysylanie do serwera
                //out.println(\);
                // Odbieranie z serwera
                //in.readLine();

            //} while ("Game over".equals(messageFromServer));
            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

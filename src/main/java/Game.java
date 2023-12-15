import java.io.*;
import java.net.*;


public class Game extends Thread {
    private Socket socket1, socket2;
    private boolean gameIsOver = false;

    public Game(Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
    }

    public void run() {

        try {
            //Odbieranie od socketa1
            InputStream input1 = socket1.getInputStream();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(input1));

            //Odbieranie od socketa2
            InputStream input2 = socket2.getInputStream();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(input2));

            //Wysylanie do socketa1
            OutputStream output1 = socket1.getOutputStream();
            PrintWriter out1 = new PrintWriter(output1, true);

            //Wysylanie do socketa2
            OutputStream output2 = socket2.getOutputStream();
            PrintWriter out2 = new PrintWriter(output2, true);

            String line;
            do {

                //TODO: logika gry

                // Odbieranie od socketa
                // in.readLine();
                // Wysylanie do socketa
                // out.println(message);

            } while (!gameIsOver);

            socket1.close();
            socket2.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void sendMessageToPlayers(String message, PrintWriter out1, PrintWriter out2){
        out1.println(message);
        out2.println(message);
    }
}
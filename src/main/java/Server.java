import java.io.*;
import java.net.*;


public class Server {

    public static void main(String[] args) {
        PlayerToGameAdapter playerToGameAdapter = new PlayerToGameAdapter();
        try (ServerSocket serverSocket = new ServerSocket(4444)) {

            while (true) {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));

                int boardSize = Integer.parseInt(in.readLine());

                System.out.println("Dolaczyl nowy gracz " + boardSize);
                playerToGameAdapter.addNewPlayer(socket, boardSize);
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

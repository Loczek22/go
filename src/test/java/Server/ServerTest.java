package Server;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;

public class ServerTest {

    @Test
    public void testClientConnection() {
        // Tworzenie nowego watku dla serwera
        Thread serverThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(4444);
                Socket clientSocket = new Socket("localhost", 4444);
                assertNotNull(clientSocket);
                serverSocket.close();
            } catch (IOException e) {
                fail("Wyjatek podczas uruchamiania serwera: " + e.getMessage());
            }
        });

        serverThread.start();

        // Oczekiwanie na zakończenie watku serwera
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            fail("Przerwano oczekiwanie na watek serwera: " + e.getMessage());
        }
    }
}
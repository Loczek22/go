package Server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

class PlayerToGameAdapterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddNewPlayerWhenSocketIsNull() {
        PlayerToGameAdapter playerToGameAdapter = new PlayerToGameAdapter();
        Socket mockSocket = mock(Socket.class);

        try {
            playerToGameAdapter.addNewPlayer(mockSocket, 9);
            assertEquals("Oczekiwanie na drugiego gracza", outContent.toString().trim());

            playerToGameAdapter.addNewPlayer(mockSocket, 13);
            assertEquals("Oczekiwanie na drugiego gracza", outContent.toString().trim());

            playerToGameAdapter.addNewPlayer(mockSocket, 19);
            assertEquals("Oczekiwanie na drugiego gracza", outContent.toString().trim());

            assertEquals(mockSocket, playerToGameAdapter.socket9);
            assertNotNull(playerToGameAdapter.out9);

            assertEquals(mockSocket, playerToGameAdapter.socket13);
            assertNotNull(playerToGameAdapter.out13);

            assertEquals(mockSocket, playerToGameAdapter.socket19);
            assertNotNull(playerToGameAdapter.out19);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSocketWith2Players() {
        PlayerToGameAdapter playerToGameAdapter = new PlayerToGameAdapter();
        Socket mockSocket = mock(Socket.class);

        try {
            playerToGameAdapter.addNewPlayer(mockSocket, 9);
            playerToGameAdapter.addNewPlayer(mockSocket, 9);

            assertNull(playerToGameAdapter.socket9);
            assertNull(playerToGameAdapter.out9);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSendMessageGameStarts() {
        PlayerToGameAdapter playerToGameAdapter = new PlayerToGameAdapter();
        PrintWriter mockOut1 = mock(PrintWriter.class);
        PrintWriter mockOut2 = mock(PrintWriter.class);

        playerToGameAdapter.sendMessageGameStarts(mockOut1, mockOut2, 9);

        verify(mockOut1, times(1)).println("Znaleziono grę");
        verify(mockOut2, times(1)).println("Znaleziono grę");
        assertEquals("Nowa gra 9 startuje", outContent.toString().trim());
    }
}
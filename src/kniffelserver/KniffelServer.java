package kniffelserver;

import gamedb.GameData;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KniffelServer {

    private static final int portNumber = 38233;

    private static final int maxPlayers = 5;

    private static final int maxConnectedUsers = 20;

    public static void main(String[] args) {

        System.out.println("Starting Server...");

        GameData gameDB;

        gameDB = new GameData(maxConnectedUsers, maxPlayers);

        try {
            
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("connecting...");
            while (true) {

                try {

                    Socket clientSocket = serverSocket.accept();
                    
                    Thread threadClient = new Thread(new ClientHandler(clientSocket, gameDB));
                    
                    threadClient.start();
                    
                    System.out.println("Server is ready to go!")

                } catch (IOException ex) {

                    System.out.print("exception: while accepting socket ");
                    System.out.println(ex.toString());
                    System.exit(-1);
                }
            }

        } catch (IOException e) {

            System.out.println("exception: " + e.getMessage());
            System.exit(-1);
        }
    }
}

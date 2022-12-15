package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataToMuchPlayersException;
import gamedb.GameDicesManagement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private boolean shutdown = false;
    private final Socket clientSocket;
    private final GameData gameDB;
    private PrintWriter outBuf = null;

    private final ArrayList<CmdClient> clientCmdList;

    ClientHandler(Socket clientSocket, GameData gameDB) {
        this.clientSocket = clientSocket;
        this.gameDB = gameDB;

        clientCmdList = new ArrayList<>();
        clientCmdList.add(new CmdClientHelp(gameDB, clientSocket, "help"));
        clientCmdList.add(new CmdClientRename(gameDB, clientSocket, "rename"));
        clientCmdList.add(new CmdClientUsers(gameDB, clientSocket, "users"));
        clientCmdList.add(new CmdClientIdentify(gameDB, clientSocket, "identify"));
        clientCmdList.add(new CmdClientMessage(gameDB, clientSocket, "message"));
        clientCmdList.add(new CmdClientExit(gameDB, clientSocket, "exit"));
        clientCmdList.add(new CmdClientRollDice(gameDB, clientSocket, "rolldice"));

    }

    @Override
    public void run() {
        try {

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outBuf = new PrintWriter(this.clientSocket.getOutputStream(), true);

            outBuf.println("**** Welcome at kniffel server ****");

            try {
                gameDB.addConnectedUser(clientSocket);

                outBuf.println("Type help for further information.");
                outBuf.println("server: ok\r\n");

                String inputString;

                while (((inputString = inBuf.readLine()) != null) && (shutdown == false)) {

                    inputString = inputString.trim();
                    String parsedData[] = inputString.split(" ");

                    boolean foundCmd = false;
                    for (int index = 0; index < clientCmdList.size(); index++) {
                        if (clientCmdList.get(index).getCmdName().compareTo(parsedData[0]) == 0) {
                            outBuf.print(clientCmdList.get(index).excuteLocalCmd(inputString));
                            foundCmd = true;
                            if (CmdClientExit.class.isInstance(clientCmdList.get(index))) {
                                shutdown = ((CmdClientExit) clientCmdList.get(index)).isClientExit();
                            }
                        }
                    }

                    if (foundCmd == false) {
                        outBuf.println("error: unknown command " + parsedData[0]);
                    }

                    outBuf.println("server: ok");
                    outBuf.println("");
                }
                gameDB.deleteConnectedUser(clientSocket);
                clientSocket.close();

            } catch (GameDataToMuchPlayersException ex) {
                outBuf.println("error: to much users are be connected");
                outBuf.println("byebye");
                outBuf.println("server: okay");
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.out.println("exception: " + ex.toString());
        }
    }
}

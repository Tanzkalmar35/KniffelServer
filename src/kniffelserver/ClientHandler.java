package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataToMuchPlayersException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final GameData gameDB;
    private final ArrayList<CmdClient> clientCmdList;
    private boolean shutdown = false;
    private PrintWriter outBuf = null;

    ClientHandler(Socket clientSocket, GameData gameDB) {
        this.clientSocket = clientSocket;
        this.gameDB = gameDB;

        clientCmdList = new ArrayList<>();
        clientCmdList.add(new CmdClientHelp(gameDB, clientSocket, "help"));
        clientCmdList.add(new CmdClientRename(gameDB, clientSocket, "rename"));
        clientCmdList.add(new CmdClientList(gameDB, clientSocket, "list"));
        clientCmdList.add(new CmdClientLogout(gameDB, clientSocket, "logout")); // except this one
        clientCmdList.add(new CmdClientVersion(gameDB, clientSocket, "version"));
        clientCmdList.add(new CmdClientCreate(gameDB, clientSocket, "creategame")); // all above implemented
        clientCmdList.add(new CmdClientJoin(gameDB, clientSocket, "joingame"));
        clientCmdList.add(new CmdClientLeave(gameDB, clientSocket, "leavegame"));
        clientCmdList.add(new CmdClientStart(gameDB, clientSocket, "startgame"));
        clientCmdList.add(new CmdClientRollDice(gameDB, clientSocket, "rolldice"));
        clientCmdList.add(new CmdClientKeepDIce(gameDB, clientSocket, "keepdice"));
        clientCmdList.add(new CmdClientSort(gameDB, clientSocket, "sort"));
        clientCmdList.add(new CmdClientKick(gameDB, clientSocket, "kick"));

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

                while (((inputString = inBuf.readLine()) != null) && (!shutdown)) {

                    inputString = inputString.trim();
                    String[] parsedData = inputString.split(" ");

                    boolean foundCmd = false;
                    for (int index = 0; index < clientCmdList.size(); index++) {
                        if (clientCmdList.get(index).getCmdName().compareTo(parsedData[0]) == 0) {
                            outBuf.print(clientCmdList.get(index).excuteLocalCmd(inputString));
                            foundCmd = true;
                            if (clientCmdList.get(index) instanceof CmdClientExit) {
                                shutdown = ((CmdClientExit) clientCmdList.get(index)).isClientExit();
                            }
                        }
                    }

                    if (!foundCmd) {
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

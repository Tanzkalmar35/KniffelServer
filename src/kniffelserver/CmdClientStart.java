package kniffelserver;

import gamedb.DataConnectedUser;
import gamedb.GameData;
import gamedb.GameDataException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CmdClientStart extends CmdClient {

    public CmdClientStart(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException, IOException {
        PrintWriter outBuf = new PrintWriter(clientSocket.getOutputStream(), true);
        if (db.gameCreated) {
            DataConnectedUser user = db.getConnectedUser(clientSocket);
            if (user.isGameAdmin()) {
                db.gameStarted = true;
                db.sendToAll(db.getConnectedUser(clientSocket).getNickname() + " started the game.");
                new GameLoop(db, clientSocket).start();
            } else {
                outBuf.println("You are not the admin of this game. Contact the game admin to start the game.");
            }
        } else {
            outBuf.println("No game created yet. Please create one before trying to start one.");
        }
        return "";
    }

}
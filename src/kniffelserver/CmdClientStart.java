package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.io.IOException;
import java.net.Socket;

public class CmdClientStart extends CmdClient {

    public CmdClientStart(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException, IOException {
        db.gameStarted = true;
        db.sendToAll(db.getConnectedUser(clientSocket).getNickname() + " started the game.");
        new GameLoop(db, clientSocket).start();
        return "";
    }

}

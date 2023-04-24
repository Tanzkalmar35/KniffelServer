package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientStart extends CmdClient {

    public CmdClientStart(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        db.gameStarted = true;
        db.sendToAll(db.getConnectedUser(clientSocket).getNickname() + " started the game.");
        return "";
    }

}

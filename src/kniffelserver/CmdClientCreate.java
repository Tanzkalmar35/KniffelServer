package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientCreate extends CmdClient {

    public CmdClientCreate(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        // set user as player (true) in users (GameData)
        createGame();
        return "";
    }

    private void createGame() {
        if (gameCreatable()) {
            db.gameRunning = true;
        }


    }

    public Boolean gameCreatable() {
        return db.connectedUserList.size() > 1 && !db.gameRunning;
    }

}

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
        createGame();
        return "Game created successfully \r\n";
    }

    private void createGame() {
        if (gameCreatable()) db.gameCreated = true;
        String username = db.getConnectedUser(clientSocket).getNickname();
        db.users.put(username, true);
    }

    public Boolean gameCreatable() {
        return db.connectedUserList.size() > 1 && !db.gameCreated;
    }

}

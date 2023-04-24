package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientLeave extends CmdClient {

    public CmdClientLeave(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String username = db.getConnectedUser(clientSocket).getNickname();
        db.users.put(username, false);
        db.sendToAll(username + " left the game.");
        return "Game left.";
    }

}

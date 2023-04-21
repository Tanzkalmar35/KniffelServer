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
        db.users.put(db.getConnectedUser(clientSocket).getNickname(), false);
        return "Game left.";
    }

}

package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientJoin extends CmdClient {

    public CmdClientJoin(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        if (gameExisting()) {
            String username = db.getConnectedUser(clientSocket).getNickname();
            db.users.put(username, true);
            return "Joined game. \r\n";
        } else return "No game running. You can create a game using the creategame command.\r\n";
    }

    private Boolean gameExisting() {
        return db.gameCreated;
    }

}

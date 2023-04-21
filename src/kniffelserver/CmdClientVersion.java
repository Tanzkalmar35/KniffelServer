package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientVersion extends CmdClient {

    public CmdClientVersion(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        return "You are using: KniffelServer " + db.serverVersion + "\r\n";
    }

}

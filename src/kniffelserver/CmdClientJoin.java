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
        throw new UnsupportedOperationException("Unimplemented method 'excuteLocalCmd'");
    }


}

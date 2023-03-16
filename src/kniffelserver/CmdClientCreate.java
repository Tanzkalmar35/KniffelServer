package kniffelserver;

import java.net.Socket;

import gamedb.GameData;
import gamedb.GameDataException;

public class CmdClientCreate extends CmdClient {
    
    public CmdClientCreate(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);    
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        // set user as player (true) in users (GameData)
        return "";
    }
    
}

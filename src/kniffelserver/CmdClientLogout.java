package kniffelserver;

import java.net.Socket;

import gamedb.GameData;
import gamedb.GameDataException;

public class CmdClientLogout extends CmdClient {

    public CmdClientLogout(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excuteLocalCmd'");
    }   
    
}

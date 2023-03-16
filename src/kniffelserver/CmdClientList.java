package kniffelserver;
import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnknownUserException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class CmdClientList extends CmdClient {

    public CmdClientList(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excuteLocalCmd'");
    }
    
}

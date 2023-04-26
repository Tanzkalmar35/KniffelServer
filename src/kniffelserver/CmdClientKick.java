package kniffelserver;

import java.io.IOException;
import java.net.Socket;

import gamedb.GameData;
import gamedb.GameDataException;


public class CmdClientKick extends CmdClient {

    public CmdClientKick(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        try {
            db.sendToAll(db.getConnectedUser(clientSocket).getNickname() + " was kicked from the server");
            new CmdClientLogout(db, clientSocket, "Kick").executeCmd(parameter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" ;
    }



}

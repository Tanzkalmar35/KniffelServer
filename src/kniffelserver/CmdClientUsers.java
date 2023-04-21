package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnknownUserException;

import java.net.Socket;

public class CmdClientUsers extends CmdClient {

    public CmdClientUsers(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String outputString = "";
        try {
            for (int index = 0; index < db.getNumberOfConnectedUsers(); index++) {
                outputString = outputString + (db.getConnectedUserNickname(index) + "\r\n");
            }
        } catch (GameDataUnknownUserException ex) {
            outputString += "error: user does not exist\r\n";
        }
        return outputString;
    }
}

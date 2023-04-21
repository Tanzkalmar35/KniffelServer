package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.io.IOException;
import java.net.Socket;

public class CmdClientLogout extends CmdClient {

    boolean clientExit = false;

    public CmdClientLogout(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String outputString = "byebye\r\n";
        clientExit = true;
        db.connectedUserList.remove(db.getConnectedUser(clientSocket).getNickname());
        db.users.remove(db.getConnectedUser(clientSocket).getNickname());
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputString;
    }

    public boolean isClientExit() {
        return clientExit;
    }

}

package kniffelserver;

import gamedb.DataConnectedUser;
import gamedb.GameData;
import gamedb.GameDataException;

import java.net.Socket;

public class CmdClientCreate extends CmdClient {

    public CmdClientCreate(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        createGame();
        return "Game created successfully \r\n";
    }

    private void createGame() {
        if (gameCreatable()) db.gameCreated = true;
        System.out.println(gameCreatable() + "ASDKJALKSDJLKASJDLKAJKLSDJL");
        DataConnectedUser user = db.getConnectedUser(clientSocket);
        db.users.put(user.getNickname(), true);
        user.setGameAdmin(true);
        db.sendToAll(user.getNickname() + " created a game. Type joingame to join.");
    }

    public Boolean gameCreatable() {
        return db.connectedUserList.size() >= 1 && !db.gameCreated;
    }

}

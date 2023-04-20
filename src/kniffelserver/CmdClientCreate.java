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
        // set user as player (true) in users (GameData)
        createGame();
        DataConnectedUser user = new DataConnectedUser(clientSocket);
        db.users.put(user.getNickname(), true);
        System.out.println(db.users);
        return "Game created successfully \n";
    }

    private void createGame() {
        if (gameCreatable()) db.lobbyCreated = true;


    }

    public Boolean gameCreatable() {
        return db.connectedUserList.size() > 1 && !db.lobbyCreated;
    }

}

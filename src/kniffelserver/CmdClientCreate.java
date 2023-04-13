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
        createGame();
        return "";
    }

    private void createGame() {
      if (gameCreatable()) {
        db.gameRunning = true;
      }  
       
        
    }

    public Boolean gameCreatable() {
      db.connectedUserList.size() <= 1 ? return false;
      !db.gameRunning ? return false;
      return true;
    }
    
}

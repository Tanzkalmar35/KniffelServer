package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import java.net.Socket;

public class CmdClientHelp extends CmdClient {

    public CmdClientHelp(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        
        String outputString 
                = "help - prints this\r\n"
                + "rename <nickname> - lets you change your nickname\r\n"
                + "list <specification(eg.users)> - lists things\r\n"
                + "logout - logs you out of the session\r\n"
                + "version - gives you the current game version\r\n"
                + "creategame - creates a new game\r\n"
                + "joingame - joins the already created game\r\n"
                + "leavegame - leaves the current game\r\n"
                + "startgame - starts the game\r\n"
                + "rolldice - rolls the dice\r\n"
                + "keepdice - keeps the current dice\r\n"
                + "sort - sorts the dice\r\n"
                + "kick <nickname> - kicks a user from the current game\r\n";
        
        return outputString;
    }
}

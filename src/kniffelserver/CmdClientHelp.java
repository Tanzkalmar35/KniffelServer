/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import java.net.Socket;

/**
 *
 * @author carst
 */
public class CmdClientHelp extends CmdClient {

    public CmdClientHelp(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        
        String outputString 
                = "exit - close server connection\r\n"
                + "help - informs about all server commands\r\n"
                + "identify - show your identification\r\n"
                + "message <text> - send <text> to all connected users\r\n"
                + "rename <nickname> - rename user to <nickname>\r\n"
                + "users - show all users\r\n"
                + "rolldice - rolls one dice\r\n";
        
        return outputString;
    }
}

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
public class CmdClientExit extends CmdClient {

    boolean clientExit = false;
    
    public CmdClientExit(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String outputString = "byebye\r\n";
        clientExit = true;
        return outputString;
    }

    public boolean isClientExit() {
        return clientExit;
    }
}

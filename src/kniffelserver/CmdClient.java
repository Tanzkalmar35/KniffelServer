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
abstract class CmdClient {
    
    GameData db;
    private final String cmdName;
    final Socket clientSocket;
    
    private String cmdOutputString;

    public CmdClient(GameData db, Socket clientSocket, String cmdName) {
        this.db = db;
        this.clientSocket = clientSocket;
        this.cmdName = cmdName;
    }
    
    public String getCmdName() {
        return cmdName;
    }
    
    public String executeCmd(String parameter) {
       cmdOutputString = "server: "+getCmdName()+"\r\n";
       cmdOutputString += excuteLocalCmd(parameter);
       
       return cmdOutputString;
    }
    
    abstract String excuteLocalCmd(String parameter) throws GameDataException;   
}

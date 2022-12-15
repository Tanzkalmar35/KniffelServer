/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnknownUserException;
import java.net.Socket;

/**
 *
 * @author carst
 */
public class CmdClientUsers extends CmdClient {

    public CmdClientUsers(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
     String outputString = "";
        try {
            for (int index = 0; index < db.getNumberOfConnectedUsers(); index++) {
                outputString += db.getConnectedUserNickname(index) + "\r\n";
            }
        } catch (GameDataUnknownUserException ex) {
            outputString += "error: user not exists\r\n";
        }
        return outputString;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnknownUserException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author carst
 */
public class CmdClientIdentify extends CmdClient {

    public CmdClientIdentify(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String outputString = "";
        try {
            outputString = db.getConnectedUserNickname(clientSocket) + "\r\n";
            InetSocketAddress socketAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
            outputString += "IP " + socketAddress.getAddress().getHostAddress() + "\r\n";        
        } catch (GameDataUnknownUserException ex) {
            outputString += "error: user not exists\r\n";
        }
        return outputString;
    }
}

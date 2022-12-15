/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnknownUserException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author carst
 */
public class CmdClientMessage extends CmdClient {

    public CmdClientMessage(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        
        String trimedInputString = parameter.replaceFirst(getCmdName(), "");
        trimedInputString = trimedInputString.trim();

        String outputString = "";
        
        try {
            for (int index = 0; index < db.getNumberOfConnectedUsers(); index++) {

                Socket socket = db.getConnectedUserSocket(index);

                if (socket != clientSocket) {
                    PrintWriter sLocalOutString = new PrintWriter(socket.getOutputStream(), true);

                    sLocalOutString.println("server: message from " + db.getConnectedUserNickname(clientSocket));
                    sLocalOutString.println(trimedInputString);
                    sLocalOutString.println("server: ok");
                    sLocalOutString.println("");
                }
            }
        } catch (GameDataUnknownUserException ex) {
            outputString += "error: user not exists\r\n";
        } catch (IOException ex) {
            outputString += "exception: " + ex.toString() + "\r\n";
        }
        return outputString;
    }
    
}

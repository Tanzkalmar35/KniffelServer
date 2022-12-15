/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnsupportedCharactersException;
import gamedb.GameDataUserExistsException;
import java.net.Socket;

/**
 *
 * @author carst
 */
public class CmdClientRename extends CmdClient {

    public CmdClientRename(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        String trimedCmdString = parameter.replaceFirst(getCmdName(), "");
        trimedCmdString = trimedCmdString.trim();
        String outputString = "";
        try {
            db.renameConnectedUser(clientSocket, trimedCmdString);
        } catch (GameDataUserExistsException ex) {
            outputString  += "error: user not exists\r\n";
        } catch (GameDataUnsupportedCharactersException ex) {
            outputString  += "error: only alphanumeric characters are allowed in the nickname.\r\n";
        }
        return outputString;
    }
    
}

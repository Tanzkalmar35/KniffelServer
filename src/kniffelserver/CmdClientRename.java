package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDataUnsupportedCharactersException;
import gamedb.GameDataUserExistsException;
import java.net.Socket;

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

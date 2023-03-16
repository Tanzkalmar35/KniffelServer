package kniffelserver;
import gamedb.GameData;
import gamedb.GameDataException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class CmdClientList extends CmdClient {
    private PrintWriter outBuf;
    private ArrayList<String> rules = new ArrayList<>();

    public CmdClientList(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
        setRules();
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException, IOException {
        outBuf = new PrintWriter(clientSocket.getOutputStream(), true);
        switch (trimInputString(parameter).toLowerCase()) {
            case "users":
                outBuf.println("\n=== Users ===");
                outBuf.println(new CmdClientUsers(db, clientSocket, parameter).excuteLocalCmd(parameter));
                break;
        
            case "players":
                outBuf.println("\n=== Players ===");
                outBuf.println(db.users.toString());
                break;

            case "rules":
                printRules();
                break;

            default:

                break;
        }
        return "";
    }

    public String trimInputString(String parameter) {
        String result = "";
        String[] parts = parameter.split(" ");
        if (parts.length > 0 || parts.length > 1) {
            result = parts[1];
        }
        return result;
    }

    private void setRules(){
        rules.add("Rule 1");
        rules.add("Rule 2");
        rules.add("Rule 3");
        rules.add("Rule 4");
        rules.add("Rule 5");
        
    }

    private void printRules() {
        outBuf.println(rules.toString());
    }
    
}

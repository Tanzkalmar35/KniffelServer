package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.io.IOException;
import java.net.Socket;

abstract class CmdClient {

    final Socket clientSocket;
    private final String cmdName;
    GameData db;
    private String cmdOutputString;

    public CmdClient(GameData db, Socket clientSocket, String cmdName) {
        this.db = db;
        this.clientSocket = clientSocket;
        this.cmdName = cmdName;
    }

    public String getCmdName() {
        return cmdName;
    }

    public String executeCmd(String parameter) throws GameDataException, IOException {
        cmdOutputString = "server: " + getCmdName() + "\r\n";
        cmdOutputString += excuteLocalCmd(parameter);

        return cmdOutputString;
    }

    abstract String excuteLocalCmd(String parameter) throws GameDataException, IOException;
}

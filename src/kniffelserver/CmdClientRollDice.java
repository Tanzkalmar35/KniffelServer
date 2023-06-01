/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class CmdClientRollDice extends CmdClient {

    public CmdClientRollDice(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException, IOException {
        if (db.gameStarted) {
            return getDice() + "\r\n";
        } else {
            PrintWriter outBuf = new PrintWriter(System.out);
            outBuf.println("You are not in a running game. Create and start one first.");
            return "";
        }
    }

    private ArrayList<Integer> getDice() throws IOException {
        return new CmdClientKeepDIce(db, clientSocket, "", new GameDice().getDices()).checkDices();
    }
}

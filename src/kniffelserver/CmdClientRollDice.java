/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDice;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author carst
 */
public class CmdClientRollDice extends CmdClient {

    ArrayList<Integer> dice;

    public CmdClientRollDice(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException, IOException {

        return getDice() + "\r\n";
    }

    private ArrayList<Integer> getDice() throws IOException {
        return dice = new CmdClientKeepDIce(db, clientSocket, "", new GameDice().getDices()).checkDices();
    }
}

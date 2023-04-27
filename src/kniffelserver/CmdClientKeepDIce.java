package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CmdClientKeepDIce extends CmdClient {

    private ArrayList<Integer> Dices;
    private PrintWriter outBuf;
    private BufferedReader inBuf;

    private Integer rerolls = 0;

    private boolean end = false;
    public CmdClientKeepDIce(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }
    public CmdClientKeepDIce(GameData db, Socket clientSocket, String cmdName, ArrayList<Integer> Dices) {
        super(db, clientSocket, cmdName);
        this.Dices = Dices;
        try {
            this.outBuf = new PrintWriter(clientSocket.getOutputStream(), true);
            this.inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        return "";
    }

    public ArrayList<Integer> checkdices(){

        outBuf.println(this.Dices);
        outBuf.println("Keep [ all || [1,2,3,4,5]]");


        try {
            checkInput(inBuf.readLine().split(" "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.Dices;
    }

    private void checkInput(String[] input){

       if(input[1].equals("all")){
            this.Dices = new CmdClientKeepDIce(db, clientSocket, "", new GameDice().getDices()).checkdices();
            outBuf.println(this.Dices);

       }

        }


}

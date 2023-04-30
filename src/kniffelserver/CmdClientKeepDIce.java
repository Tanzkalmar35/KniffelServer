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

public class CmdClientKeepDIce extends CmdClient {

    private ArrayList<Integer> Dices;
    private PrintWriter outBuf;
    private BufferedReader inBuf;

    private int rerolls = 3;
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

    public ArrayList<Integer> checkDices() throws IOException {


        boolean check = true;

        String[] finalInput = new String[1];

        for (int i = 0; i < rerolls; i++) {
            outBuf.println(this.Dices);
            outBuf.println("Keep [ [all] || [1,2,3,4,5] ]");

            check = true;
            while (check) {

                String input = inBuf.readLine().toLowerCase();
                if (checkInput(input.split(" "))) {
                    finalInput = input.split(" ");
                    check = !check;
                } else {
                    outBuf.println("error:  pls enter a valid option");
                }
            }
            this.Dices = rerollDices(finalInput);
        }
        return this.Dices;
    }

    private boolean checkInput(String[] input) {

        if (input[0].equals("keep") && input.length == 2) {
            return true;
        } else if (input.length > 2) {
            outBuf.println("error: to many parameter");
        } else {
            outBuf.println("error: pls enter a valid option");
        }
        return false;

    }

    private ArrayList<Integer> rerollDices(String[] input) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        if (!input[1].equals("all")) {
            String[] keepdices = input[1].split(",");
            ArrayList<Integer> dicenumbers = new ArrayList<Integer>();
            for (String keepdice : keepdices) {
                dicenumbers.add(Integer.valueOf(keepdice));
            }

            for (Integer dicenumber : dicenumbers) {
                result.add(this.Dices.get(dicenumber - 1));
            }

            int remaining = 5 - dicenumbers.size();

            for (int i = 0; i < remaining; i++) {
                result.add(new GameDice().randomDice());
            }

        } else {
            this.rerolls = 1;
            result = this.Dices;
        }

        return result;

    }

}

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

public class CmdClientKeepDice extends CmdClient {

    private static ArrayList<Integer> dices;
    private PrintWriter outBuf;
    private BufferedReader inBuf;

    private int rerolls = 3;
    private boolean end = false;


    public CmdClientKeepDice(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
    }

    public CmdClientKeepDice(GameData db, Socket clientSocket, String cmdName, ArrayList<Integer> dices) {
        super(db, clientSocket, cmdName);
        CmdClientKeepDice.dices = dices;
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


        boolean check;

        String[] finalInput = new String[1];

        for (int i = 0; i < rerolls; i++) {
            outBuf.println(dices);
            outBuf.println("Keep [ [all] || [1,2,3,4,5] ]");
            while (true) {

                outBuf.println("Going on");

                BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String input = inputBuffer.readLine();
                String[] splitInput = input.split(" ");

                if (splitInput[0].equals("keep") && splitInput.length == 2) {
                    if (checkInput(splitInput)) {
                        outBuf.println("Runs through");
                        finalInput = splitInput;
                        break;
                    } else {
                        outBuf.println("error:  pls enter a valid option");
                    }
                } else {
                    outBuf.println("Sorry, Java is shit");
                }
            }
            outBuf.println("Ended");
            dices = rerollDices(finalInput);
        }
        return dices;
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
            String[] keepDices = input[1].split(",");
            ArrayList<Integer> dicenumbers = new ArrayList<Integer>();
            for (String keepDice : keepDices) {
                dicenumbers.add(Integer.valueOf(keepDice));
            }

            for (Integer diceNumber : dicenumbers) {
                result.add(dices.get(diceNumber - 1));
            }

            int remaining = 5 - dicenumbers.size();

            for (int i = 0; i < remaining; i++) {
                result.add(new GameDice().randomDice());
            }

        } else {
            this.rerolls = 1;
            result.addAll(dices);

        }

        return result;

    }

    public ArrayList<Integer> getDice() {
        return dices;
    }

}

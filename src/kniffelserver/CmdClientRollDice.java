/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;
import gamedb.GameDice;

import java.net.Socket;

/**
 * @author carst
 */
public class CmdClientRollDice extends CmdClient {

    GameDice dice;

    public CmdClientRollDice(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
        dice = new GameDice();
    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {

        /* String emptyOutput = "";

        try {

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outBuf = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String outputString = dice.getDices() + "\r\n";

            outputString = outputString.replaceAll(",", "");
            outputString = outputString.replaceAll(" ", "");
            String[] dices = outputString.split("");

            outBuf.println(outputString);
            outBuf.println("Which ones do you want to keep?");

            String inputString = inBuf.readLine();

            String workingInput = inputString.replaceAll(",", "");
            workingInput = workingInput.replaceAll(" ", "");
            String[] workingInputList = workingInput.split("");
            if (workingInput.equalsIgnoreCase("all") || workingInput.equalsIgnoreCase("keepdice")) {
                outBuf.println("Keeping all.");
                return emptyOutput;
            }
            System.out.println("Length of list: " + workingInputList.length);

            System.out.println("Edited input: " + workingInput);

            List<String> list = new ArrayList<>();

            for (int i = 0; i < workingInputList.length; i++) {
                String a = dices[Integer.parseInt(workingInputList[i])];
                list.add(i, a);
            }

            outBuf.println("Keeping " + list);

        } catch (IOException e) {
            e.printStackTrace();
        } */


        return "";
    }
}

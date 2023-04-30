package kniffelserver;

import gamedb.GameData;
import gamedb.GameDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CmdClientSort extends CmdClient {
    private final HashMap<String, ArrayList<Integer>> sortedDices = new HashMap<>();

    private final String[] pairs = {"Two Pair", "Three Pair", "Four Pair", "Kniffel"};

    private final PrintWriter outBuf;
    private final BufferedReader inBuf;

    public CmdClientSort(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);
        try {
            this.outBuf = new PrintWriter(clientSocket.getOutputStream(), true);
            this.inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    String excuteLocalCmd(String parameter) throws GameDataException {
        // TODO Auto-generated method stub
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(2);
        test.add(1);
        test.add(3);
        test.add(4);
        test.add(3);
        sort(test);
        outBuf.println(this.sortedDices.toString());
        return "";

    }

    public HashMap<String, ArrayList<Integer>> sort(ArrayList<Integer> dices) {

        if (pairSort(dices)) {
            if (fullHouseSort(dices)) {
                if (streetSort(dices)) {

                } else {
                    outBuf.println("error: sorting street");
                }
            } else {
                outBuf.println("error: sorting fullhouse");
            }
        } else {
            outBuf.println("error: sorting pairs");
        }
        return this.sortedDices;
    }

    public boolean pairSort(ArrayList<Integer> dices) {

        Integer[] diceNumbers = {1, 2, 3, 4, 5, 6};
        for (Integer diceNumber : diceNumbers) {

            int pairScore = Collections.frequency(dices, diceNumber);
            if (pairScore > 1) {
                for (int j = 1; j < pairScore; j++) {
                    this.sortedDices.put(this.pairs[j - 1], dices);

                }
            }
        }

        return true;

    }

    private boolean streetSort(ArrayList<Integer> dices) {
        return smallStreet(dices) && bigStreet(dices);

    }

    private boolean fullHouseSort(ArrayList<Integer> dices) {
        Integer[] diceNumbers = {1, 2, 3, 4, 5, 6};
        for (Integer diceNumber : diceNumbers) {
            int fullHouseThrees = Collections.frequency(dices, diceNumber);
            if (fullHouseThrees == 3) {
                for (Integer number : diceNumbers) {
                    int fullHouseTwos = Collections.frequency(dices, number);
                    if (fullHouseTwos == 2) {
                        this.sortedDices.put("Full House", dices);
                        return true;
                    }
                }
            }
        }
        return true;
    }

    private boolean smallStreet(ArrayList<Integer> dices) {
        if (dices.contains(1) && dices.contains(2) && dices.contains(3) && dices.contains(4)) {
            this.sortedDices.put("Small Street", dices);
        } else if (dices.contains(2) && dices.contains(3) && dices.contains(4) && dices.contains(5)) {
            this.sortedDices.put("Small Street", dices);
        }

        return true;
    }

    private boolean bigStreet(ArrayList<Integer> dices) {
        Collections.sort(dices);
        if (dices.get(0) == 1 && dices.get(1) == 2 && dices.get(2) == 3 && dices.get(3) == 4 && dices.get(4) == 5
                || dices.get(0) == 2 && dices.get(1) == 3 && dices.get(2) == 4 && dices.get(3) == 5
                && dices.get(4) == 6) {
            this.sortedDices.put("Straight Big Street", dices);
        }
        return true;
    }

}

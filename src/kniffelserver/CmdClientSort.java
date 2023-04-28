package kniffelserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import gamedb.GameData;
import gamedb.GameDataException;

public class CmdClientSort extends CmdClient {
    private HashMap<String, ArrayList<Integer>> SortetDices = new  HashMap<>();

    private String[] pairs = {"Two Pair", "Three Pair", "Four Pair", "Kniffel" };

    private PrintWriter outBuf;
    private BufferedReader inBuf;
    public CmdClientSort(GameData db, Socket clientSocket, String cmdName) {
        super(db, clientSocket, cmdName);try {
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
        test.add(1);
        test.add(1);
        test.add(2);
        test.add(2);
        test.add(2);
        sort(test);
        outBuf.println(this.SortetDices.toString());
        return "";

    }


    public HashMap<String, ArrayList<Integer>> sort(ArrayList<Integer> dices){

        if(pairsort(dices )){

        }else{
            outBuf.println("error: now Pairs");
        }
        return this.SortetDices;
    }

    public boolean pairsort(ArrayList<Integer> dices){
        Integer pairs = 0;
        Integer[] dicenumbers = {1,2,3,4,5,6};
        for(int i = 0; i < dicenumbers.length; i++){

            int pairscore = Collections.frequency(dices, dicenumbers[i]);
            if(pairscore > 1){
                for(int j = 1; j < pairscore; j++){
                    this.SortetDices.put(this.pairs[j - 1], dices);

                }
            }
        }

        return true;
    }

}

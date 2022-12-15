/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedb;

import java.util.ArrayList;

/**
 *
 * @author carst
 */
public class GameDicesManagement {

    private final ArrayList<GameDice> diceList;

    public GameDicesManagement() {
        this(5);
    }

    public GameDicesManagement(int numberOfDices) {

        diceList = new ArrayList<>();

        for (int index = 0; index < numberOfDices; index++) {
            diceList.add(new GameDice());
        }
    }

    public void resetAllDices() {
        for (int index = 0; index < diceList.size(); index++) {
            diceList.get(index).resetDice();
        }
    }

    public void rollUnselectedDices() {
        for (int index = 0; index < diceList.size(); index++) {
            if (!diceList.get(index).isRolled()) {
                diceList.get(index).rollDice();
            }
        }
    }

    public int getNumberOfDices() {
        return diceList.size();
    }

    public String showAllDices() {
        String outputText = "";
        for (int index = 0; index < diceList.size(); index++) {
            outputText += "dice " + index + ": " + diceList.get(index).getDice() + "\r\n";
        }
        return outputText;
    }

    public GameDice getDice(int index) {
        if ((index >= 0) && (index < diceList.size())) {
            return diceList.get(index);
        } else {
            return null;
        }
    }

    public void unselectDices(int index) {
        if ((index >= 0) && (index < diceList.size())) {
            diceList.get(index).resetDice();
        }
    }
}

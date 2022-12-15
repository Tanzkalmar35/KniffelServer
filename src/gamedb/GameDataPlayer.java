/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedb;

/**
 *
 * @author carst
 */
public class GameDataPlayer {

    private int ones;
    private int twos;
    private int threes;
    private int fours;
    private int fives;
    private int sixes;

    private int threeOfKind;
    private int fourOfKind;
    private int fullHouse;
    private int smallStraight;
    private int largeStraight;
    private int kniffel;
    private int chance;

    public GameDataPlayer() {
        this.ones = -1;
        this.twos = -1;
        this.threes = -1;
        this.fours = -1;
        this.fives = -1;
        this.sixes = -1;

        this.threeOfKind = -1;
        this.fourOfKind = -1;
        this.fullHouse = -1;
        this.smallStraight = -1;
        this.largeStraight = -1;
        this.kniffel = -1;
        this.chance = -1;
    }

    public boolean isBonusAchieved() {
        int counter = 0;
        
        if (isOnesSet()) counter += getOnes();
        if (isTwosSet()) counter += getTwos();
        if (isThreesSet()) counter += getThrees();
        if (isFoursSet()) counter += getFours();
        if (isFivesSet()) counter += getFives();
        if (isSixesSet()) counter += getSixes();
        
        return (counter >= 63); 
    }
    
    
    public int getOnes() {
        return ones;
    }

    public void setOnes(int ones) {
        this.ones = ones;
    }

    public boolean isOnesSet() {
        return (this.ones != -1);
    }

    public int getTwos() {
        return twos;
    }

    public void setTwos(int twos) {
        this.twos = twos;
    }

    public boolean isTwosSet() {
        return (this.twos != -1);
    }

    public int getThrees() {
        return threes;
    }

    public void setThrees(int threes) {
        this.threes = threes;
    }

    public boolean isThreesSet() {
        return (this.threes != -1);
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public boolean isFoursSet() {
        return (this.fours != -1);
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public boolean isFivesSet() {
        return (this.fives != -1);
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public boolean isSixesSet() {
        return (this.sixes != -1);
    }

    public int getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(int threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    public boolean isThreeOfKindSet() {
        return (this.threeOfKind != -1);
    }
    
    public int getFourOfKind() {
        return fourOfKind;
    }

    public void setFourOfKind(int fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    public boolean isFourOfKindSet() {
        return (this.fourOfKind != -1);
    }
    
    public int getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(int fullHouse) {
        this.fullHouse = fullHouse;
    }
    
    public boolean isFullHouseSet() {
        return (this.fullHouse != -1);
    }

    public int getSmallStraight() {
        return smallStraight;
    }

    public void setSmallStraight(int smallStraight) {
        this.smallStraight = smallStraight;
    }
    
    public boolean isSmallStraightSet() {
        return (this.smallStraight != -1);
    }

    public int getLargeStraight() {
        return largeStraight;
    }

    public void setLargeStraight(int largeStraight) {
        this.largeStraight = largeStraight;
    }

    public boolean isLargeStraightSet() {
        return (this.largeStraight != -1);
    }
    
    public int getKniffel() {
        return kniffel;
    }

    public void setKniffel(int kniffel) {
        this.kniffel = kniffel;
    }
    
    public boolean isKniffelSet() {
        return (this.kniffel != -1);
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
    
    public boolean isChanceSet() {
        return (this.chance != -1);
    }
}

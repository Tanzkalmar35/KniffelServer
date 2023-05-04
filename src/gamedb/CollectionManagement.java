package gamedb;

import java.util.HashMap;

public class CollectionManagement {

    public HashMap<String, Integer> data = new HashMap<>();

    public CollectionManagement() {
        userCollection();
    }

    public HashMap<String, Integer> userCollection() {

        this.data.put("ones", 0);
        this.data.put("twos", 0);
        this.data.put("threes", 0);
        this.data.put("fours", 0);
        this.data.put("fives", 0);
        this.data.put("sixes", 0);
        this.data.put("threePair", 0);
        this.data.put("fourPair", 0);
        this.data.put("fullHouse", 0);
        this.data.put("smallStreet", 0);
        this.data.put("bigStreet", 0);
        this.data.put("kniffel", 0);
        this.data.put("chance", 0);

        return this.data;
    }
}

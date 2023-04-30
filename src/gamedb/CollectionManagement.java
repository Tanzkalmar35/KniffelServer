package gamedb;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionManagement {

    public ArrayList<HashMap<String, String>> userCollection() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        data.add(generateUserData("Nur 1"));
        data.add(generateUserData("Nur 2"));
        data.add(generateUserData("Nur 3"));
        data.add(generateUserData("Nur 4"));
        data.add(generateUserData("Nur 5"));
        data.add(generateUserData("Nur 6"));
        data.add(generateUserData("Dreierpasch"));
        data.add(generateUserData("Viererpasch"));
        data.add(generateUserData("Full house"));
        data.add(generateUserData("Kleine Straße"));
        data.add(generateUserData("Große Straße"));
        data.add(generateUserData("Kniffel"));
        data.add(generateUserData("Chance"));

        return data;
    }

    private HashMap<String, String> generateUserData(String collectionName) {
        HashMap<String, String> line = new HashMap<>();
        line.put(collectionName, "\r\n");
        return line;
    }

}

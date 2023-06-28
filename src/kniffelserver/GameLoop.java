package kniffelserver;

import gamedb.DataConnectedUser;
import gamedb.GameData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class GameLoop {

    GameData db;
    Socket socket;

    PrintWriter outBuf;
    BufferedReader inBuf;

    public GameLoop(GameData gameData, Socket clientSocket) {
        db = gameData;
        socket = clientSocket;
    }

    public void start() throws IOException {
        for (String player : getPlayers()) {

            outBuf = new PrintWriter(getSocket(player).getOutputStream(), true);
            inBuf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataConnectedUser user = getUser(player);

            db.sendToAll("It's " + player + "'s turn. \r\n");

            outBuf.println("Your collection: \r\n" + user.gameCollection.data + "\r\n");
            outBuf.println("Your dice: \r\n");

            new CmdClientRollDice(db, getSocket(player), "").excuteLocalCmd(""); // fix logging (wrong client)

            new CmdClientSort(db, getSocket(player), "").sortDice(new CmdClientKeepDIce(db, getSocket(player), "").getDice()); // fix loggings

            // get which option to choose
            outBuf.println("What do you want to sort this in?"); // fix loggings

            String input = inBuf.readLine();
            storeCollection(user, input); // fix loggings

            outBuf.println("Collection stored. Updated collection: \r\n" + user.gameCollection.data); // fix loggings

        }
    }

    public ArrayList<String> getPlayers() {
        ArrayList<String> players = new ArrayList<>();
        for (String user : db.users.keySet()) {
            if (db.users.get(user)) {
                players.add(user);
            }
        }
        return players;
    }

    public Socket getSocket(String username) {
        for (DataConnectedUser i : db.connectedUserList) {
            if (i.getNickname().equals(username)) {
                return i.getSocket();
            }
        }
        return new Socket();
    }

    public DataConnectedUser getUser(String username) {
        for (DataConnectedUser i : db.connectedUserList) {
            if (i.getNickname().equals(username)) {
                return i;
            }
        }
        return new DataConnectedUser(socket);
    }

    private void storeCollection(DataConnectedUser user, String collection) {
        if (!collectionValid(collection)) {
            outBuf.println("Not Valid.");
            return;
        }
        if (collectionUsed(user, collection)) {
            outBuf.println("Breeits vergeben");
            return;
        }

        user.gameCollection.data.put(collection, getTotal(collection));
    }

    private Boolean collectionValid(String collection) {
        return new CmdClientSort(db, socket, "").getSortedDices().containsKey(collection);
    }

    private Boolean collectionUsed(DataConnectedUser user, String collection) {
        return user.gameCollection.data.get(collection) != 0;
    }

    private int getTotal(String input) {
        outBuf.println(input);
        switch (input) {
            case "ones" -> {
                if (CmdClientSort.sortedDices.containsKey("ones")) {
                    return Collections.frequency(CmdClientSort.sortedDices.get("ones"), 1);
                }
            }
            case "twos" -> {
                if (CmdClientSort.sortedDices.containsKey("twos")) {
                    outBuf.println(2 * Collections.frequency(CmdClientSort.sortedDices.get("twos"), 2));
                    return 2 * Collections.frequency(CmdClientSort.sortedDices.get("twos"), 2);
                }
            }
            case "threes" -> {
                if (CmdClientSort.sortedDices.containsKey("threes")) {
                    return 3 * Collections.frequency(CmdClientSort.sortedDices.get("threes"), 3);
                }
            }
            case "fours" -> {
                if (CmdClientSort.sortedDices.containsKey("fours")) {
                    return 4 * Collections.frequency(CmdClientSort.sortedDices.get("fours"), 4);
                }
            }
            case "fives" -> {
                if (CmdClientSort.sortedDices.containsKey("fives")) {
                    return 5 * Collections.frequency(CmdClientSort.sortedDices.get("fives"), 5);
                }
            }
            case "sixes" -> {
                if (CmdClientSort.sortedDices.containsKey("sixes")) {
                    return 6 * Collections.frequency(CmdClientSort.sortedDices.get("sixes"), 6);
                }
            }
            case "threePair" -> {
                if (CmdClientSort.sortedDices.containsKey("threePair")) {
                    return sum(CmdClientSort.sortedDices.get("threePair"));
                }
            }
            case "fourPair" -> {
                if (CmdClientSort.sortedDices.containsKey("fourPair")) {
                    return sum(CmdClientSort.sortedDices.get("fourPair"));
                }
            }
            case "smallStreet" -> {
                if (CmdClientSort.sortedDices.containsKey("smallStreet")) {
                    return 30;
                }

            }
            case "bigStreet" -> {
                if (CmdClientSort.sortedDices.containsKey("bigStreet")) {
                    return 40;
                }

            }
            case "fullHouse" -> {
                if (CmdClientSort.sortedDices.containsKey("fullHouse")) {
                    return 25;
                }

            }
            case "kniffel" -> {
                if (CmdClientSort.sortedDices.containsKey("kniffel")) {
                    return 50;
                }
            }
            case "chance" -> {
                return sum(CmdClientSort.sortedDices.get("ones"));
            }
        }
        return 0;
    }

    public int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (int i : list)
            sum += i;
        return sum;
    }

}

package kniffelserver;

import gamedb.DataConnectedUser;
import gamedb.GameData;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class GameLoop {

    GameData db;
    Socket socket;

    PrintWriter outBuf;

    public GameLoop(GameData gameData, Socket clientSocket) {
        db = gameData;
        socket = clientSocket;
    }

    public void start() throws IOException {
        for (String player : getPlayers()) {

            outBuf = new PrintWriter(socket.getOutputStream(), true);

            db.sendToAll("It's " + player + "'s turn.");
            Socket currentUser;
            DataConnectedUser.sendMessage("Your collection: \r\n" + db.userCollections(), getSocket(player));


            new CmdClientRollDice(db, socket, "").excuteLocalCmd("");
            // ask what to fill the dice in (nick sort)
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

}

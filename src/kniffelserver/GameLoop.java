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

            db.sendToAll("It's " + player + "'s turn. \r\n");
            Socket currentUser;
            DataConnectedUser.sendMessage("Your collection: \r\n" + getUser(player).getCollection().userCollection() + "\r\n", getSocket(player));

            DataConnectedUser.sendMessage("Your dice: \r\n", getSocket(player));

            new CmdClientRollDice(db, socket, "").excuteLocalCmd("");
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

}

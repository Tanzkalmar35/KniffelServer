package gamedb;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

public class GameData extends Thread {

    public final String serverVersion = "v1.0.0";
    public final ArrayList<DataConnectedUser> connectedUserList;
    public final ArrayList<Socket> clientList = new ArrayList<>();
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;
    private final int maxPlayers;
    private final int maxUsers;
    public HashMap<String, Boolean> users = new HashMap<>();
    public Boolean gameCreated = false;
    public Boolean gameStarted = false;

    public GameData(int maxNumberUsers, int maxNumberPlayers) {
        this.maxUsers = maxNumberUsers;
        this.maxPlayers = maxNumberPlayers;
        connectedUserList = new ArrayList<>();
        readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public int getNumberOfConnectedUsers() {
        try {
            readLock.lock();
            return connectedUserList.size();
        } finally {
            readLock.unlock();
        }
    }

    public DataConnectedUser getConnectedUser(Socket socket) throws GameDataUnknownUserException {
        for (DataConnectedUser connectedUser : connectedUserList) {
            if (connectedUser.getSocket() == socket) {
                return connectedUser;
            }
        }
        throw new GameDataUnknownUserException();
    }

    public void deleteConnectedUser(Socket socket) throws GameDataUnknownUserException {
        try {
            writeLock.lock();
            connectedUserList.remove(this.getConnectedUser(socket));
        } finally {
            writeLock.unlock();
        }
    }

    public void addConnectedUser(Socket clientSocket) throws GameDataUserExistsException, GameDataToMuchPlayersException {
        try {
            writeLock.lock();
            if (connectedUserList.size() >= maxUsers) {
                throw new GameDataToMuchPlayersException();
            }
            try {
                getConnectedUser(clientSocket);
                throw new GameDataUserExistsException();
            } catch (GameDataUnknownUserException ex) {
                DataConnectedUser user = new DataConnectedUser(clientSocket);
                connectedUserList.add(user);
                users.put(user.getNickname(), false);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void renameConnectedUser(Socket clientSocket, String nickname) throws GameDataUnknownUserException, GameDataUnsupportedCharactersException {
        try {
            writeLock.lock();
            Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");

            if (p.matcher(nickname).matches()) {
                try {
                    getConnectedUser(clientSocket).setNickname(nickname);
                    for (String k : users.keySet()) {
                        if (getConnectedUser(clientSocket).getNickname().equals(k)) {
                            users.remove(k);
                            users.put(nickname, false);
                        }
                    }
                } catch (GameDataUnknownUserException ex) {
                    throw new GameDataUnknownUserException();
                }
            } else {
                throw new GameDataUnsupportedCharactersException();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public String getConnectedUserNickname(int index) throws GameDataUnknownUserException {
        try {
            readLock.lock();
            if ((index < 0) || (index > connectedUserList.size())) {
                throw new GameDataUnknownUserException();
            }
            return this.connectedUserList.get(index).getNickname();
        } finally {
            readLock.unlock();
        }
    }

    public String getConnectedUserNickname(Socket clientSocket) throws GameDataUnknownUserException {
        try {
            readLock.lock();
            return this.getConnectedUser(clientSocket).getNickname();
        } finally {
            readLock.unlock();
        }
    }

    public Socket getConnectedUserSocket(int index) throws GameDataUnknownUserException {
        try {
            readLock.lock();
            if ((index < 0) || (index > connectedUserList.size())) {
                throw new GameDataUnknownUserException();
            }
            return this.connectedUserList.get(index).getSocket();
        } finally {
            readLock.unlock();
        }
    }

    public void sendToAll(String message) {
        for (Socket socket : clientList) {
            DataConnectedUser.sendMessage(message, socket);
        }
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedb;

import java.net.Socket;

/**
 * Class to handle connected user data
 *
 * @author carst
 */

public class DataConnectedUser {

    /**
     * socket of connected user
     */
    private final Socket socket;

    /**
     * nickname of connected user
     */
    private String nickname;

    /**
     * Constructor of class
     *
     * @param socket of connected user
     */
    public DataConnectedUser(Socket socket) {
        this.socket = socket;
        this.nickname = "Anonymous";
    }

    /**
     * Get socket of connected user
     *
     * @return socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Get nickname of connected user
     *
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    
    /**
     * Set nickname of connected user
     *
     * @param nickname of connected user
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

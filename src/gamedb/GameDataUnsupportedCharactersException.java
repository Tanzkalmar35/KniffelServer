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
public class GameDataUnsupportedCharactersException extends GameDataException {
    public GameDataUnsupportedCharactersException() {
        super("Unsupported characters");
    }
}

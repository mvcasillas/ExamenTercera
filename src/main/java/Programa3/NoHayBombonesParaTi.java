/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa3;

/**
 *
 * @author DAW
 */
public class NoHayBombonesParaTi extends Exception{
    
    String message;

    public NoHayBombonesParaTi(String message) {
        this.message=message;
    }

    @Override
    public String toString() {
        return "No hay bombones para ti: " + message;
    }
    
    
    
}

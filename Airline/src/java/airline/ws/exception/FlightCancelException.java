/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline.ws.exception;

/**
 *
 * @author lucacambiaghi
 */
public class FlightCancelException extends Exception {
    
    public FlightCancelException(String message){
        super(message);
    }
    
}

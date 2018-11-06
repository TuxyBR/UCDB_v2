/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author alber
 */
public class BatataC {
    
    public float cutPot (String ser) {
        return Float.parseFloat(ser.substring(0, 3));
    }
    
    public boolean cutLed (String ser) {
        return Boolean.parseBoolean(ser.substring(4,5));
    }
    
    public boolean cutBtn (String ser) {
        return Boolean.parseBoolean(ser.substring(5));
    }
    
}

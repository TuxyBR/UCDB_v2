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
public class BatataChulainn {
    
    public static String cuPot (String ser) {
        return ser.substring(0, 4);
    }
    
    public static String cuLed (String ser) {
        String comp = ser.substring(4,5);
        String resposta;
        if(comp.equals("0")){
            resposta="DESLIGADO";
        }
        else{
            resposta="LIGADO";
        }
        return resposta;
    }
    
    public static String cuBtn (String ser) {
        String comp=ser.substring(5);
        String resposta;
        if(comp.equals("0")){
            resposta="DESLIGADO";
        }
        else{
            resposta="LIGADO";
        }
        return resposta;
    }
    
}

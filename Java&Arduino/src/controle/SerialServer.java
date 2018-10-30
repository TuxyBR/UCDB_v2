//isso eh a servidor de intreacao base do rxtx do jeandro como base

package controle;

import interfaceGrafica.Janela;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import javax.xml.bind.DatatypeConverter;

public class SerialServer implements SerialPortEventListener {

    public InputStream entrada;
    public OutputStream saida;
    public SerialPort porta;
    public static String leitura = "";

    public Boolean Run(String comName, Integer comNum) throws PortInUseException {

        CommPortIdentifier COM;
        try {
            COM = CommPortIdentifier.getPortIdentifier(comName);
            porta = (SerialPort) COM.open(comName, comNum);
        } catch (NoSuchPortException | PortInUseException ex) {
            //Logger.getLogger(SerialServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(comName + " :  Erro Ao Abri porta Serial **********: " + ex.getMessage());
            return false;
        }
        try {
            entrada = porta.getInputStream();
        } catch (IOException ex) {
            //Logger.getLogger(SerialServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(comName + " :Erro Ao Abri porta Serial **********: " + ex.getMessage());
            return false;
        }
        try {
            porta.addEventListener(this);
        } catch (TooManyListenersException ex) {
            //Logger.getLogger(SerialServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(comName + " :Erro Ao Abri porta Serial **********: " + ex.getMessage());
            return false;
        }
        porta.notifyOnDataAvailable(true);

        System.out.println("\n\nStarting COM" + comNum + "\n\n");
        return true;
    }

    public void enviarDados(String texto) throws IOException,
            InterruptedException {

        try {
            // get the outputstream
            saida = porta.getOutputStream();
        } catch (Exception e) {
        }

        try {
            // activate the OUTPUT_BUFFER_EMPTY notifier
            porta.notifyOnOutputEmpty(true);
        } catch (Exception e) {
            System.out.println("ERRO! O dado " + texto
                    + " nao pode ser enviado!");
            e.printStackTrace();
            return;
        }

        // write string to serial port
        saida.write(texto.getBytes());

        Thread.sleep(1);

        saida.flush();
        saida.close();

    }

    @Override
    public void serialEvent(SerialPortEvent ev) {
        StringBuffer buffer = new StringBuffer();
        int dado = 0;

        if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            while (true) {
                try {
                    dado = entrada.read();
                   
                    if (dado == -1) {
                        break;
                    }

                    if ('\n' == (char) dado) {
                        buffer.append('\n');
                        break;
                    } else {
                        buffer.append((char) dado);
                    }

                } catch (IOException e) {
                    System.err.println("Erro ao ler os dados");
                }
            }
        }
        leitura = new String(buffer);
        Janela.jTEntrada.setText(leitura);
        System.out.println("Dado Recebido: " + leitura);

    }

    public void closeSerial() {
        this.porta.close();
    }
}

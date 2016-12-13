/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import utils.Compra;
import utils.Constantes;
import utils.Usuario;

/**
 *
 * @author lalo
 */
public class Envio {

    static boolean validarUsuario(Usuario usuario) {
        try {
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(Constantes.AUTENTICAR);
            String inputLine;
            if ((inputLine = in.readLine()) != null && inputLine.equals("OK")) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(usuario);
            }
            if ((inputLine = in.readLine()) != null && inputLine.equals("true")) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    static DefaultListModel obtenerListaProductos() {
        DefaultListModel defaultListModel = null;
        try {
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("productos");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            defaultListModel = (DefaultListModel)ois.readObject();
            ois.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defaultListModel;
    }

    static boolean guardarCompra(Compra compra) {
        try {
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("compra");
            String inputLine;
            if ((inputLine = in.readLine()) != null && inputLine.equals("OK")) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(compra);
            }
            if ((inputLine = in.readLine()) != null && inputLine.equals("true")) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

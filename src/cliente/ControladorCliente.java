/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author lalo
 */
public class ControladorCliente {

    private Login login;
    private ModeloCliente modeloCliente;
    private ComprasUs comprasUs;

    public ControladorCliente() {
        login = new Login();
        setUpEventosLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    private void setUpEventosLogin() {
        login.getEntrar().setAction(new AbstractAction("Entrar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String usuario = login.getUsuarioText().getText();
                String password = login.getPasswordText().getText();
                if (!usuario.isEmpty() && !password.isEmpty() && validarPassword(new Usuario(usuario, password))) {
                    passwordValido();
                } else {
                    JOptionPane.showMessageDialog(login, "Usuario o Contraseña incorrecta  \nvuelve a intentar", "Error", JOptionPane.DEFAULT_OPTION);
                }
            }
        });

    }

    private boolean validarPassword(Usuario usuario) {
        return true;//TODO: validar password con soket
    }

    private void passwordValido() {
        modeloCliente = new ModeloCliente();
        comprasUs = new ComprasUs();
        bindingModelo();
        comprasUs.setLocationRelativeTo(null);
        comprasUs.setVisible(true);
        login.setVisible(false);
    }

    private void bindingModelo() {
        
    }
    //validar compra
    //JOptionPane.showConfirmDialog(null, "Total $", "Total de compra", JOptionPane.OK_OPTION);

}

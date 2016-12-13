/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import utils.Compra;
import utils.Productos;
import utils.Usuario;

/**
 *
 * @author lalo
 */
public class ControladorCliente {

    private Login login;
    private DefaultListModel listProductos;
    private ComprasUs comprasUs;
    private String nombreUsuario;

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
                    passwordValido(usuario);
                } else {
                    JOptionPane.showMessageDialog(login, "Usuario o Contraseña incorrecta  \nvuelve a intentar", "Error", JOptionPane.DEFAULT_OPTION);
                }
            }
        });

    }

    private boolean validarPassword(Usuario usuario) {
        return Envio.validarUsuario(usuario);//TODO: validar password con soket
    }

    private void passwordValido(String usuario) {
        nombreUsuario = usuario;
        comprasUs = new ComprasUs();
        bindingModelo();
        comprasUs.setLocationRelativeTo(null);
        comprasUs.setVisible(true);
        login.setVisible(false);
        setUpEventosCompra();
    }

    private void bindingModelo() {
        listProductos = Envio.obtenerListaProductos();
        comprasUs.getListaProductos().setModel(listProductos);
    }

    private void setUpEventosCompra() {
        comprasUs.getComprar().setAction(new AbstractAction("Comprar") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                List<Productos> productos = comprasUs.getListaProductos().getSelectedValuesList();
                int total = 0;
                for (Productos producto : productos) {
                    total += producto.getPrecio();
                }
                int selectedOption = JOptionPane.showConfirmDialog(comprasUs, String.format("Total $%s", total), "Total de compra", JOptionPane.OK_CANCEL_OPTION);
                String mensaje;
                if (selectedOption == JOptionPane.YES_OPTION) {
                    if (Envio.guardarCompra(new Compra(productos,total,nombreUsuario))) {
                        mensaje = "Compra exitosa";
                    }else{
                        mensaje = "Compra fallida";
                    }
                }else{
                    mensaje = "Compra Cancelada";
                }
                JOptionPane.showConfirmDialog(comprasUs, mensaje, "Compra", JOptionPane.DEFAULT_OPTION);

            }
        });

    }

}

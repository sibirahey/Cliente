/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import javax.swing.DefaultListModel;

/**
 *
 * @author lalo
 */
public class ModeloCliente {
    
    private DefaultListModel listProductos;

    public ModeloCliente() {
        //TODO: listProductos = obtenerProductos;
    }

    public DefaultListModel getListProductos() {
        return listProductos;
    }

    public void setListProductos(DefaultListModel listProductos) {
        this.listProductos = listProductos;
    }
    
    
    
}

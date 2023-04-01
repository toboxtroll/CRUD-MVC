package com.supermercado.view;

import javax.swing.*;
public class Application extends JFrame{
    public JTextField identificacionTextField;
    public JTextField nombresTextField;
    public JTextField apellidosTextField;
    public JComboBox<String> comboBoxCargo;
    public JButton btnGuardar;
    public JPanel panelContenido;
    public JTextField idTextField;
    public JButton btnEditar;
    public JButton btnBuscar;
    public JButton btnLimpiar;
    public JButton btnEliminar;
    public JButton consultasButton;

    public JPanel getPanelContenido() {
        return panelContenido;
    }

}

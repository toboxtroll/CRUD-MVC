package com.supermercado.controller;

import com.supermercado.model.Cargo;
import com.supermercado.model.Cliente;
import com.supermercado.model.DAO.CargoDAO;
import com.supermercado.model.DAO.ClienteDAO;
import com.supermercado.model.DAO.ConsultaCargoDAO;
import com.supermercado.view.Application;
import com.supermercado.view.ConsultaPorCargo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class CtrCliente implements ActionListener {
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private Application formApplication;
    private TreeMap<Integer, String> cargoMap = new TreeMap<Integer, String>();

    public CtrCliente(Cliente cliente, ClienteDAO clienteDAO, Application formApplication) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.formApplication = formApplication;
        this.formApplication.btnGuardar.addActionListener(this);
        this.formApplication.btnEditar.addActionListener(this);
        this.formApplication.btnBuscar.addActionListener(this);
        this.formApplication.btnEliminar.addActionListener(this);
        this.formApplication.btnLimpiar.addActionListener(this);
        this.formApplication.consultasButton.addActionListener(this);
        comboBoxCargo();
    }
    private void comboBoxCargo(){
        ArrayList<Cargo> cargos = CargoDAO.consultarCargos();
        this.formApplication.comboBoxCargo.addItem("Seleccione");

        for (Cargo cargo : cargos) {
            this.formApplication.comboBoxCargo.addItem(cargo.getCargNombre());
            this.cargoMap.put(cargo.getCargId(), cargo.getCargNombre());
        }
    }
    public  void iniciar(){
        formApplication.setTitle("Clientes");
        formApplication.setLocationRelativeTo(null);
        formApplication.idTextField.setVisible(false);
        formApplication.setContentPane(formApplication.getPanelContenido());
        formApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formApplication.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formApplication.btnGuardar)){
            crearCliente();
        }

        if(e.getSource().equals(formApplication.btnEditar)){
            modificarCliente();
        }

        if(e.getSource().equals(formApplication.btnEliminar)){
            eliminarCliente();
        }

        if(e.getSource().equals(formApplication.btnBuscar)){
            buscar();
        }

        if(e.getSource().equals(formApplication.btnLimpiar)){
            limpiar();
        }

        if(e.getSource().equals(formApplication.consultasButton)){
            CtrConsultaCargo ctrConsultaCargo = new CtrConsultaCargo(new Cliente(), new ConsultaCargoDAO(), new ConsultaPorCargo());
            ctrConsultaCargo.iniciar();
        }

    }

    private void crearCliente(){
        if(datosCorrectos()) {
            cliente.setNumeroCedula(Integer.parseInt(formApplication.identificacionTextField.getText()));
            cliente.setNombres(formApplication.nombresTextField.getText());
            cliente.setApellidos(formApplication.apellidosTextField.getText());
            cliente.setIdCargo(obtenerlLavePorValor(String.valueOf(formApplication.comboBoxCargo.getSelectedItem())));

            if (clienteDAO.crearCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
        }
    }

    private void modificarCliente(){
        if(datosCorrectos()) {
            cliente.setIdCliente(Integer.parseInt(formApplication.idTextField.getText()));
            cliente.setNumeroCedula(Integer.parseInt(formApplication.identificacionTextField.getText()));
            cliente.setNombres(formApplication.nombresTextField.getText());
            cliente.setApellidos(formApplication.apellidosTextField.getText());
            cliente.setIdCargo(obtenerlLavePorValor(String.valueOf(formApplication.comboBoxCargo.getSelectedItem())));

            if (clienteDAO.modificarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }
        }
    }

    private void eliminarCliente(){
        int respuesta = JOptionPane.showConfirmDialog(null, "Está segurdo de eliminar el Cliente " + cliente.getNombres(),
                "Eliminar Cliente", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0) {
            if (clienteDAO.eliminarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        }
    }

    private void buscar(){
        if(Pattern.matches("\\d+", formApplication.identificacionTextField.getText())){
            cliente = clienteDAO.buscarCliente(Integer.valueOf(formApplication.identificacionTextField.getText()));
        }

        formApplication.idTextField.setText(String.valueOf(cliente.getIdCliente()));
        formApplication.identificacionTextField.setText(String.valueOf(cliente.getNumeroCedula()));
        formApplication.nombresTextField.setText(cliente.getNombres());
        formApplication.apellidosTextField.setText(cliente.getApellidos());
        formApplication.comboBoxCargo.setSelectedIndex(obtenerlLavePorValor(String.valueOf(cliente.getCargoNombre())));
    }
    private int obtenerlLavePorValor(String value) {
        for (Map.Entry<Integer, String> entry : cargoMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }


    private void limpiar(){
        formApplication.identificacionTextField.setText(null);
        formApplication.nombresTextField.setText(null);
        formApplication.apellidosTextField.setText(null);
        formApplication.comboBoxCargo.setSelectedIndex(0);
    }
    private boolean datosCorrectos(){
        boolean validacionCorrecta = true;
        if(formApplication.identificacionTextField.getText().matches("\\D") || formApplication.identificacionTextField.getText().equals("")){
            validacionCorrecta = false;
            JOptionPane.showMessageDialog(null, "El número de indentificación es requerido y debe ser numérico");

        } else if (formApplication.nombresTextField.getText().equals("")) {
            validacionCorrecta = false;
            JOptionPane.showMessageDialog(null, "Los Nombres son requeridos");

        } else if (formApplication.apellidosTextField.getText().equals("")) {
            validacionCorrecta = false;
            JOptionPane.showMessageDialog(null, "Los Apellidos son requeridos");

        } else if (String.valueOf(formApplication.comboBoxCargo.getSelectedItem()).equals("Seleccione")) {
            validacionCorrecta = false;
            JOptionPane.showMessageDialog(null, "El cargo es requerido");

        }
        return validacionCorrecta;
    }


}

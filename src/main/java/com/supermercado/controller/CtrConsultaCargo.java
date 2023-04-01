package com.supermercado.controller;

import com.supermercado.model.Cargo;
import com.supermercado.model.Cliente;
import com.supermercado.model.DAO.CargoDAO;
import com.supermercado.model.DAO.ConsultaCargoDAO;
import com.supermercado.view.ConsultaPorCargo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CtrConsultaCargo implements ActionListener {
    private Cliente cliente;
    private ConsultaCargoDAO consultaCargoDAO;
    private ConsultaPorCargo formConsultaPorCargo;
    private TreeMap<Integer, String> cargoMap = new TreeMap<Integer, String>();
    private ArrayList<Cliente> clientes;

    public CtrConsultaCargo(Cliente cliente, ConsultaCargoDAO consultaCargoDAO, ConsultaPorCargo formConsultaPorCargo) {
        this.cliente = cliente;
        this.consultaCargoDAO = consultaCargoDAO;
        this.formConsultaPorCargo = formConsultaPorCargo;
        this.formConsultaPorCargo.btnBuscar.addActionListener(this);
        comboBoxCargo();
    }
    private void comboBoxCargo(){
        ArrayList<Cargo> cargos = CargoDAO.consultarCargos();
        this.formConsultaPorCargo.comboBoxCargo.addItem("Seleccione");

        for (Cargo cargo : cargos) {
            this.formConsultaPorCargo.comboBoxCargo.addItem(cargo.getCargNombre());
            this.cargoMap.put(cargo.getCargId(), cargo.getCargNombre());
        }
    }

    public  void iniciar(){
        formConsultaPorCargo.setTitle("Supermercado Clientes");
        formConsultaPorCargo.setLocationRelativeTo(null);
        formConsultaPorCargo.setContentPane(formConsultaPorCargo.panelContenido);
        formConsultaPorCargo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formConsultaPorCargo.pack();
        formConsultaPorCargo.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formConsultaPorCargo.btnBuscar)){
            buscar();
        }
    }

    private void buscar(){
        ArrayList<Cliente> clientes = consultaCargoDAO.buscarCliente(obtenerlLavePorValor(String.valueOf(formConsultaPorCargo.comboBoxCargo.getSelectedItem())));
        Object[] fila = new Object[4];
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Identificacion");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Cargo");

        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNombres());
            fila[0] = cliente.getNumeroCedula();
            fila[1] = cliente.getNombres();
            fila[2] = cliente.getApellidos();
            fila[3] = cliente.getCargoNombre();
            model.addRow(fila);
        }
        table.setFillsViewportHeight(true);
        formConsultaPorCargo.scrollPane.setViewportView(table);
    }

    private int obtenerlLavePorValor(String value) {
        for (Map.Entry<Integer, String> entry : cargoMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}

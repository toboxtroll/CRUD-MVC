package com.supermercado;

import com.supermercado.controller.CtrCliente;
import com.supermercado.model.Cliente;
import com.supermercado.model.DAO.ClienteDAO;
import com.supermercado.view.Application;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        Application formApplication = new Application();

        CtrCliente ctrCliente = new CtrCliente(cliente, clienteDAO, formApplication);
        ctrCliente.iniciar();
        formApplication.setVisible(true);

    }
}
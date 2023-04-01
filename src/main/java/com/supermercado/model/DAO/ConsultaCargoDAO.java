package com.supermercado.model.DAO;

import com.supermercado.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConsultaCargoDAO {

    public ArrayList<Cliente> buscarCliente(Integer cargid){
        PreparedStatement preparedStatement;
        String query;
        ArrayList<Cliente> clientes = new ArrayList<>();

        ResultSet resultSet;
        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();

            query = "SELECT * FROM cliente c1 JOIN cargo c2 on c1.cargid = c2.cargid WHERE c1.cargid = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cargid);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("clieid"));
                cliente.setNumeroCedula(resultSet.getInt("clienumeroidentificacion"));
                cliente.setNombres(resultSet.getString("clienombre"));
                cliente.setApellidos(resultSet.getString("clieapellido"));
                cliente.setIdCargo(resultSet.getInt("cargid"));
                cliente.setCargoNombre(resultSet.getString("cargnombre"));
                clientes.add(cliente);
            }

            System.out.println("Message Create Successfully");
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return clientes;
    }
}

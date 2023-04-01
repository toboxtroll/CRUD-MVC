package com.supermercado.model.DAO;

import com.supermercado.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    public boolean crearCliente(Cliente cliente) {

        String query = "INSERT INTO public.cliente(clienumeroidentificacion, clienombre, clieapellido, cargid)VALUES (?, ?, ?, ?)";
        PreparedStatement insertCliente = null;

        Connection connection = null;
        try {
            connection = ConnectionPostgres.getInstance().getConnectionPostgres();

            insertCliente = connection.prepareStatement(query);
            insertCliente.setInt(1, cliente.getNumeroCedula());
            insertCliente.setString(2, cliente.getNombres());
            insertCliente.setString(3, cliente.getApellidos());
            insertCliente.setInt(4, cliente.getIdCargo());
            insertCliente.executeUpdate();

            System.out.println("Message Create Successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public Cliente buscarCliente(Integer clieNumeroIdentificacion){
        PreparedStatement preparedStatement;
        String query;
        Cliente cliente = new Cliente();
        ResultSet resultSet;
        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();

            query = "SELECT * FROM cliente c1 JOIN cargo c2 on c1.cargid = c2.cargid WHERE clienumeroidentificacion = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clieNumeroIdentificacion);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                cliente.setIdCliente(resultSet.getInt("clieid"));
                cliente.setNumeroCedula(resultSet.getInt("clienumeroidentificacion"));
                cliente.setNombres(resultSet.getString("clienombre"));
                cliente.setApellidos(resultSet.getString("clieapellido"));
                cliente.setIdCargo(resultSet.getInt("cargid"));
                cliente.setCargoNombre(resultSet.getString("cargnombre"));
            }

            System.out.println("Message Create Successfully");
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return cliente;
    }

    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement preparedStatement;
        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();
            String query;
            query = "UPDATE public.cliente SET clienumeroidentificacion = ?, clienombre = ?, clieapellido = ?, cargid = ? WHERE clieid = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cliente.getNumeroCedula());
            preparedStatement.setString(2, cliente.getNombres());
            preparedStatement.setString(3, cliente.getApellidos());
            preparedStatement.setInt(4, cliente.getIdCargo());
            preparedStatement.setInt(5, cliente.getIdCliente());
            preparedStatement.executeUpdate();
            System.out.println("Message Create Successfully");
            return true;
        } catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement preparedStatement;
        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();
            String query;
            query = "DELETE FROM public.cliente WHERE clieid = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.execute();
            System.out.println("Message Create Successfully");
            return true;
        } catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        }
    }
}

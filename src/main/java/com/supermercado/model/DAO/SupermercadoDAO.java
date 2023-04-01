package com.supermercado.model.DAO;

import com.supermercado.model.Supermercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupermercadoDAO {
    public static ArrayList<Supermercado> consultarSupermercados(){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<Supermercado> supermercados = new ArrayList<Supermercado>();
        String query = "SELECT * FROM supermercado";

        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Supermercado supermercado = new Supermercado();
                supermercado.setSupeId(resultSet.getInt("supeid"));
                supermercado.setSupeNombre(resultSet.getString("supenombre"));
                supermercados.add(supermercado);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return supermercados;

    }
}

package com.supermercado.model.DAO;

import com.supermercado.model.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CargoDAO {

    public static ArrayList<Cargo> consultarCargos(){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ArrayList<Cargo> cargos = new ArrayList<Cargo>();
        Cargo cargo;

        try {
            Connection connection = ConnectionPostgres.getInstance().getConnectionPostgres();
            String query;
            query = "SELECT * FROM cargo";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cargo = new Cargo(resultSet.getInt("cargid"), resultSet.getString("cargnombre"));
                cargos.add(cargo);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return cargos;

    }

}

package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serv {
    public static void sortByName(Connection connection) throws SQLException {
        List<String> json = new ArrayList<>();
        List<String> jsonb = new ArrayList<>();
        long start;
        long finish;


        PreparedStatement preparedStatement = connection.prepareStatement("SELECT (a -> 'name') as a FROM jtest ORDER by b ASC;");


        start = System.nanoTime();
        ResultSet resultSet = preparedStatement.executeQuery();
        finish = System.nanoTime();
        System.out.println("Sort time A:  " + (finish - start));



        preparedStatement = connection.prepareStatement("SELECT (b -> 'name') as b FROM apteka ORDER by b ASC;");


        start = System.nanoTime();
        resultSet = preparedStatement.executeQuery();
        finish = System.nanoTime();
        System.out.println("Sort time B:  " + (finish - start));

    }
    public static void search(Connection connection )throws SQLException {
        long start;
        long finish;

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT a -> 'name' as json FROM apteka");
        Pharmacist[] doctors=new Pharmacist[2];

        start = System.nanoTime();
        ResultSet resultSet = preparedStatement.executeQuery();
        finish = System.nanoTime();
        System.out.println("Search time A:  " + (finish - start) );

        preparedStatement=connection.prepareStatement("SELECT b -> 'name' as jsonb FROM apteka");



        start = System.nanoTime();
        resultSet = preparedStatement.executeQuery();
        finish = System.nanoTime();
        System.out.println("Search time B:  " + (finish - start) );
    }


}
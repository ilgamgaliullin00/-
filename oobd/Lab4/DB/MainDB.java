package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainDB {
    public static void main(String[] args) {

        try {

            Class.forName("org.postgresql.Driver");

            Connection connection =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","ilgam2000");
            Long beg;
            Long end;

            Load4mDB.loadPharmacistsListB(connection);
            Load4mDB.loadPharmacistsListB(connection);
            Load4mDB.loadPharmacistsListB(connection);
            Load4mDB.loadPharmacistsListB(connection);
            beg=System.nanoTime();
            Load4mDB.loadPharmacistsListB(connection);
            end= System.nanoTime();
            System.out.println("Load TIME B="+(end-beg));
            Load4mDB.loadPharmacistsListA(connection);Load4mDB.loadPharmacistsListA(connection);Load4mDB.loadPharmacistsListA(connection);Load4mDB.loadPharmacistsListA(connection);Load4mDB.loadPharmacistsListA(connection);
            beg=System.nanoTime();
            Load4mDB.loadPharmacistsListA(connection);
            end= System.nanoTime();
            System.out.println("Load TIME A="+(end-beg));

            Serv.search(connection);
            Serv.sortByName(connection);
            Pharmacist pharmacist=new Pharmacist("Name",new Date(),Gender.MALE,new Area("12"),"ортодонт");
            ArrayList<Pharmacist> doc=new ArrayList<>();
            doc.add(pharmacist);
            Save.save(doc,connection);

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
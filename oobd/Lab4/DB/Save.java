package DB;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Save {
    public static void save(List<Pharmacist> pharmacists, Connection connection) throws SQLException {

        if (pharmacists != null && pharmacists.size() > 0) {
            Gson gson=new Gson();
            String personAsJson=gson.toJson(pharmacists);

            Long beg=System.nanoTime();
            PreparedStatement statement = connection.
                    prepareStatement("insert into jtest (a) values ( cast( ? as json))");
            statement.setString(1, personAsJson);
            statement.executeUpdate();
            Long end=System.nanoTime();
            System.out.println("SAVE TIME A="+(end-beg));
            statement.close();

            beg=System.nanoTime();
            statement = connection.
                    prepareStatement("insert into jtest (b) values ( cast( ? as json) )");
            statement.setString(1, personAsJson);
            statement.executeUpdate();
            end=System.nanoTime();
            System.out.println("SAVE TIME B="+(end-beg));

        }
    }

}

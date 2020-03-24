package DB;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.postgresql.core.SqlCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Load4mDB {

    public static Apteka load() throws IOException {
        Apteka apteka = null;
        String clinicStr = "";
        File file = new File("pharmacist.json");

        if (file.exists()) {
            clinicStr = new String(Files.readAllBytes(file.toPath()));
        }

        apteka = new Gson().fromJson(clinicStr, Apteka.class);

        return apteka;
    }

    /**
     * Пример чтения из файла массива JSON объектов
     */
    public static List<Pharmacist> loadPharmacistsListB(Connection connection) throws JsonSyntaxException, SQLException {

        String pStr = "";


        PreparedStatement statement =
                connection.prepareStatement("select b from apteka ");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("b");

        }

        statement.close();

        Gson gson = new Gson();

        Pharmacist[] plst = gson.fromJson(pStr, Pharmacist[].class);


        statement =
                connection.prepareStatement("select b  as c from apteka ");

        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("c");

        }

        return Arrays.asList(plst);

    }
    public static List<Pharmacist> loadPharmacistsListA(Connection connection) throws JsonSyntaxException, SQLException {

        String pStr = "";


        PreparedStatement statement =
                connection.prepareStatement("select a from apteka ");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("a");

        }

        statement.close();

        Gson gson = new Gson();

        Pharmacist[] plst = gson.fromJson(pStr, Pharmacist[].class);


        statement =
                connection.prepareStatement("select a  as c from apteka ");

        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("c");

        }
        return Arrays.asList(plst);

    }
}


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class ScanDB {

    private static Connection connection;

    public LinkedHashMap<String, LinkedHashSet<String>> getTablesInfo() {
        // Структура для хранения имен таблиц и полей (в HashSet)
        LinkedHashMap<String, LinkedHashSet<String>> tables = new LinkedHashMap<>();

        try (Connection connection = getConnection()) {

//      System.out.println("Список таблиц:");
            List<String> tbls = getTables(connection);
//      tbls.forEach(System.out::println);

            for (String table : tbls) {
//        System.out.println("Список полей таблицы " + table + ":");
                List<String> fields = getFields(connection, table);

                //          System.out.println(f);
                LinkedHashSet<String> hashSetFields = new LinkedHashSet<>(fields);

                tables.put(table, hashSetFields);
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tables;
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        Class.forName("org.postgresql.Driver");
        String dbURL = "jdbc:postgresql://localhost:5432/lab7";
        connection = DriverManager.getConnection(dbURL, "postgres", "ilgam2000");

        return connection;
    }

    static List<String> getTables(Connection connection) throws SQLException {

        List<String> lst = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(
                "SELECT table_name FROM information_schema.tables " +
                        "WHERE table_type = 'BASE TABLE' AND" +
                        " table_schema NOT IN ('pg_catalog', 'information_schema')");

        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()) {
            String s = resultSet.getString("table_name");
            lst.add(s);
        }

        st.close();
        return lst;
    }

    public static List<String> getFields(Connection connection, String tableName) throws SQLException {

        List<String> lst = new ArrayList<>();

        PreparedStatement st = connection.prepareStatement(
                "SELECT a.attname " +
                        "FROM pg_catalog.pg_attribute a " +
                        "WHERE a.attrelid = (SELECT c.oid FROM pg_catalog.pg_class c " +
                        "LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace " +
                        " WHERE pg_catalog.pg_table_is_visible(c.oid) AND c.relname = ? )" +
                        " AND a.attnum > 0 AND NOT a.attisdropped");

        st.setString(1, tableName);
        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()) {
            String s = resultSet.getString("attname");
            lst.add(s);
        }

        st.close();
        return lst;
    }

}
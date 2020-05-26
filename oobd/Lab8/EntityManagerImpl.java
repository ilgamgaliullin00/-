

import annotation.Column;
import annotation.ManyToOne;
import classes.Pharmacist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class EntityManagerImpl implements EntityManager {

    private final String URL = "jdbc:postgresql://localhost:5432/lab8";
    private Connection connection;
    private Properties props;
    private List classes;


    EntityManagerImpl() {

    }
    public EntityManagerImpl( Properties props, List classes) {
        this.connection = getConnection();
        this.props = props;
        this.classes = classes;
    }

    public EntityManagerImpl(Connection connection, Properties props, List classes) {
        this.connection = connection;
        this.props = props;
        this.classes = classes;
    }

    private Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                connection = DriverManager.getConnection(URL,
                        "postgres", "ilgam2000");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void close() {
        closeConnection();
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    Annotation[] getAnnotations(Field field) {
        List<Annotation> list = new ArrayList<>(Arrays.asList(field.getAnnotations()));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains("Column") && list.size() >= 2) {
                list.remove(list.get(i));
            }
        }
        Annotation[] annotation = new Annotation[1];
        list.toArray(annotation);
        return annotation;

    }

    Field[] getFields(Object object) {

        if (object.getClass().getCanonicalName().equals("classes.Pharmacist")) {
            Field[] personFields = Pharmacist.class.getSuperclass().getDeclaredFields();
            Field[] authorFields = Pharmacist.class.getDeclaredFields();
            Field[] fields = new Field[authorFields.length + personFields.length];
            Arrays.setAll(fields, i ->
                    (i < personFields.length ? personFields[i] : authorFields[i - personFields.length]));
            return fields;
        } else return object.getClass().getDeclaredFields();

    }


    @Override
    public void persist(Object object) {
        String className = object.getClass().getSimpleName().toLowerCase();
        System.out.println("tableName = " + className);

        //получаем поля
        Field fields[] = getFields(object);


        StringBuilder sql = new StringBuilder();
        List<Object> values = new ArrayList<>();
        sql.append("INSERT INTO ");
        int k = 0;
        for (Field field : fields) {
            Annotation[] annotations = getAnnotations(field);
            for (int i = 0; i < annotations.length; i++) {

                if (annotations[i].annotationType().equals(Column.class) || annotations[i].annotationType().equals(ManyToOne.class)) {
                    k++;
                    try {

                        String fieldName = field.getName();
                        Object value;
                        Long valueId;
                        String fieldNameForMethod = field.getName().substring(0, 1).toUpperCase() +
                                field.getName().substring(1);
                        if (annotations[i].annotationType().equals(Column.class)) {
                            Method method = object.getClass().getMethod(
                                    "get" + fieldNameForMethod, null);
                            value = method.invoke(object, null);
                            if (k == 1) {
                                sql.append(className);
                                sql.append(" ( ");
                            }
                            sql.append(fieldName).append(", ");

                            values.add(value);
                        } else {
                            Method method = object.getClass().getMethod(
                                    "get" + fieldNameForMethod, null);
                            Object ob = method.invoke(object, null);
                            Method method2 = ob.getClass().getMethod(
                                    "getId", null);
                            valueId = (Long) method2.invoke(ob, null);
                            if (k == 1) {
                                sql.append(className);
                                sql.append(" ( ");
                            }
                            sql.append(fieldName).append("_id").append(", ");

                            values.add(valueId);

                        }


                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(" ) ").append(" VALUES ").append(" ( ");
        for (int i = 0; i < values.size(); i++) {
            sql.append("\'").append(values.get(i)).append("\'").append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" ) ");
        sql.append(" returning id;");
        System.out.println("SQL = " + sql);
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(String.valueOf(sql));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Object id = resultSet.getLong("id");
//        System.out.println("id22" + id);
                Method method = object.getClass().getMethod(
                        "setId", Long.class);
//        System.out.println("method = " + method.getName());
                method.invoke(object, id);

//        System.out.println("атворрр " + object.toString());

            }
        } catch (SQLException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            close();
        }


    }


    //update
    @Override
    public <T> T merge(T object) {

        String className = object.getClass().getSimpleName().toLowerCase();
//    System.out.println("tableName = " + className);

        //получаем поля
        Field fields[] = getFields(object);
//    System.out.println(Arrays.toString(fields));
//    System.out.println();


        StringBuilder sql = new StringBuilder();
        List<Object> values = new ArrayList<>();
        sql.append("UPDATE ").append(className).append(" SET ");
        for (Field field : fields) {
            Annotation[] annotations = getAnnotations(field);
//      System.out.println(Arrays.toString(annotations));
            for (int i = 0; i < annotations.length; i++) {

                if (annotations[i].annotationType().equals(Column.class) || annotations[i].annotationType().equals(ManyToOne.class)) {

                    try {

                        String fieldName = field.getName();
                        Object value;
                        Long valueId;
                        String fieldNameForMethod = field.getName().substring(0, 1).toUpperCase() +
                                field.getName().substring(1);
                        if (annotations[i].annotationType().equals(Column.class)) {
//              System.out.println("fieldName = " + fieldNameForMethod);

                            Method method = object.getClass().getMethod(
                                    "get" + fieldNameForMethod, null);
//              System.out.println("method = " + method.getName());
                            value = method.invoke(object, null);

                            sql.append(fieldName).append(" = ").append("'").append(value).append("'").append(", ");
                            values.add(value);
                        } else {
//              System.out.println("fieldName! = " + fieldNameForMethod);
                            Method method = object.getClass().getMethod(
                                    "get" + fieldNameForMethod, null);
//              System.out.println("method != " + method.getName());
                            Object ob = method.invoke(object, null);
//              System.out.println(ob.toString() + "toString");
                            Method method2 = ob.getClass().getMethod(
                                    "getId", null);
                            valueId = (Long) method2.invoke(ob, null);

//              System.out.println(fieldName + " ATTENTION");
//              System.out.println(valueId);
                            sql.append(fieldName).append("_id").append(" = ").append("'").append(valueId).append("'").append(", ");

                            values.add(valueId);

                        }
//            System.out.println(value);


                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }


        }

        Method method = null;
        Long valueId = null;
        try {
            method = object.getClass().getMethod(
                    "getId", null);
            valueId = (Long) method.invoke(object, null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        sql.deleteCharAt(sql.length() - 2);
        sql.append("WHERE id=").append(valueId).append(" returning id");
        System.out.println("SQL update = "+sql);
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(String.valueOf(sql));
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return object;
    }

    //удаление
    @Override
    public void remove(Object object) {

        String className = object.getClass().getSimpleName().toLowerCase();

        //получаем поля
        Field fields[] = getFields(object);

        Long valueId;
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(className);
        try {
            Method method = object.getClass().getMethod(
                    "getId", null);
            valueId = (Long) method.invoke(object, null);
            sql.append(" WHERE id=").append(valueId).append(" returning id");

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("SQL delete = "+sql);

        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(String.valueOf(sql));
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
    }


    Object getNewObject(Object objectOfClass, String sql, Class classN) {
        Field[] fields = getFields(objectOfClass);
//    System.out.println(Arrays.toString(fields));
//    System.out.println(objectOfClass.getClass());


        try {
            PreparedStatement statement = connection.prepareStatement(String.valueOf(sql));
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int i = 0;
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int j = 0; j < resultSetMetaData.getColumnCount(); j++) {
                    String columnName = resultSetMetaData.getColumnName(j + 1);
//          System.out.println("columnName " + columnName);
                    if (columnName.equalsIgnoreCase("id")) {
                        Long columnValue = resultSet.getLong(columnName.toLowerCase());
                        Method method = classN.getMethod(
                                "setId", Long.class);
                        method.invoke(objectOfClass, columnValue);

                    }

                    //если есть ссылка на др таблицу
                    else if (columnName.contains("_id")) {
                        String[] line = columnName.split("_");
                        String fieldNameForMethod = line[0].substring(0, 1).toUpperCase() +
                                line[0].substring(1);
                        Object columnValue = resultSet.getLong(columnName);

                        String sqlSELECT = "SELECT * FROM " + fieldNameForMethod.toLowerCase() + " WHERE id=" + columnValue;
                        System.out.println("sqlSELECT = " + sqlSELECT);

                        statement = connection.prepareStatement(sqlSELECT);

                        ResultSet resultSet2 = statement.executeQuery();
                        ResultSetMetaData resultSetMetaData2 = resultSet2.getMetaData();

                        Class<?> clazz2 = Class.forName("oodb.lab8.classes." + fieldNameForMethod);
                        Constructor constructor = clazz2.getConstructor();
                        Object objectOfClass2 = constructor.newInstance();

                        objectOfClass2 = getNewObject(objectOfClass2, sqlSELECT, clazz2);

                        Method method = objectOfClass.getClass().getMethod("set" + fieldNameForMethod, objectOfClass2.getClass());
                        method.invoke(objectOfClass, objectOfClass2);

                    } else {
                        Object columnValue = resultSet.getObject(columnName.toLowerCase());
                        String fieldNameForMethod = columnName.substring(0, 1).toUpperCase() +
                                columnName.substring(1);
                        Method method = null;
                        if (columnValue.getClass() == String.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, String.class);
                            method.invoke(objectOfClass, columnValue);
                        } else if (columnValue.getClass() == Double.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, Double.class);
                            method.invoke(objectOfClass, columnValue);
                        } else if (columnValue.getClass() == Long.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, Long.class);
                            method.invoke(objectOfClass, columnValue);
                        } else if (columnValue.getClass() == java.sql.Date.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, String.class);
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                            String columnValueDate = df.format(columnValue);
                            method.invoke(objectOfClass, columnValueDate);
                        } else if (columnValue.getClass() == Integer.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, Integer.class);
                            method.invoke(objectOfClass, columnValue);
                        } else if (columnValue.getClass() == Object.class) {
                            method = objectOfClass.getClass().getMethod(
                                    "set" + fieldNameForMethod, Object.class);
                            method.invoke(objectOfClass, columnValue);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            close();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objectOfClass;
    }


    //поиск по коду id и классу, возвращает экземпляр класса
    @Override
    public <T> T find(Class<T> classN, Object var2) {
        String className = classN.getSimpleName().toLowerCase();

        System.out.println("tableName = " + className);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(className).append(" WHERE id=").append(var2);

        System.out.println("SQL = " + sql);
//    Connection connection = getConnection();
        Object objectOfClass = null;
        try {
            Class<?> clazz = Class.forName(String.valueOf(classN).split(" ")[1].trim());
            Constructor con = clazz.getConstructor();
            objectOfClass = con.newInstance();

            objectOfClass = getNewObject(objectOfClass, String.valueOf(sql), classN);


        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return (T) objectOfClass;
    }


    //
    @Override
    public void refresh(Object object) {
        String className = object.getClass().getSimpleName().toLowerCase();

        System.out.println("tableName = " + className);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(className).append(" WHERE id=").append(object);

        System.out.println("SQL = " + sql);
//    Connection connection = getConnection();
        Object objectOfClass = null;
        try {
            Class<?> clazz = Class.forName(String.valueOf(className).split(" ")[1].trim());
            Constructor con = clazz.getConstructor();
            objectOfClass = con.newInstance();

            objectOfClass = getNewObject(objectOfClass, String.valueOf(sql), object.getClass());


        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        object = objectOfClass;

    }
}
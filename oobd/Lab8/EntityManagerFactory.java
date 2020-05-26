import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class EntityManagerFactory {
    private Properties props;
    private List classes;

    public EntityManagerFactory(Properties properties, String classPath) {
        try {
            this.props = properties;
            LinkedHashMap<String, LinkedHashMap<String, String>> tablesPackage = ScanClasses.getInfoAboutClasses();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EntityManager create() {
        return new EntityManagerImpl(props, classes);

    }

    public void close() {
        new EntityManagerImpl(props, classes).close();
    }


}
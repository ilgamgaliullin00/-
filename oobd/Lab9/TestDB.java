import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestDB {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("lab9");
        emf.close();
    }
}
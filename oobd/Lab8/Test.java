

import classes.Pharmacist;
import classes.Drug;
import classes.Person;
import classes.Viewstitles;

import java.util.Properties;


public class Test {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("url", "jdbc:postgresql://localhost:5432/lab8");
        props.setProperty("username", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("driverName", "org.postgresql.Driver");
        String classPath = "classes";
//    EntityManagerImpl entityManager = new EntityManagerImpl();
        EntityManagerFactory entityManagerFactory = new EntityManagerFactory(props, classPath);
        EntityManager entityManager = entityManagerFactory.create();

        Person pharmacist = new Pharmacist();
        pharmacist.setName("Александр");
        pharmacist.setPatronymic("Петрович");
        pharmacist.setLastname("Волков");
        ((Pharmacist) pharmacist).setInfo("фармацевт");

        Person pharmacist2 = new Pharmacist();
        pharmacist2.setName("Тестирование");
        pharmacist2.setPatronymic("Пройдено");
        pharmacist2.setLastname("Успешно");
        ((Pharmacist) pharmacist2).setInfo("фармацевт");

        entityManager.persist(pharmacist);
        entityManager.persist(pharmacist2);





        Viewstitles sectionsTitles = new Viewstitles();
        sectionsTitles.setSectionstitles("Жаропонижающие");
        entityManager.persist(sectionsTitles);

//
        Drug drug = new Drug();
        drug.setTitle("Работай уже!!!");
        drug.setPharmacist((Pharmacist) pharmacist);
        drug.setViewstitles(sectionsTitles);
        drug.setDateofpublishing("08.05.2020");
        drug.setPrice(23.0);
        entityManager.persist(drug);

        System.out.println("\n");
        System.out.println("Объект класса Pharmacist до обновления - " + pharmacist);
        ((Pharmacist) pharmacist).setInfo("Писатель и поэт");
        entityManager.merge(pharmacist);
        System.out.println("Объект класса Pharmacist после обновления - " + pharmacist);

        System.out.println("\n");
        System.out.println("Объект класса Drug до обновления - " + drug);
        drug.setDateofpublishing("30.05.2015");
        drug.setPharmacist((Pharmacist) pharmacist2);
        entityManager.merge(drug);
        System.out.println("Объект класса Drug после обновления - " + drug);
        System.out.println("\n");

        entityManager.remove(drug);
        entityManager.remove(sectionsTitles);


        System.out.println("\n");
        System.out.println("Объект класса Drug до обновления - " + drug);
        drug.setDateofpublishing("08.03.2020");
        entityManager.merge(drug);
        System.out.println("Объект класса Drug после обновления - " + drug);
        System.out.println("\n");

        System.out.println("\n");
        Object object = entityManager.find(Drug.class, 1);
        System.out.println("Найденный объект класса Drug с id=1 - " + object);

        entityManagerFactory.close();


    }
}

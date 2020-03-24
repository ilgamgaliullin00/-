import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {


        List<Pharmacist> pharmacists = LoadDB.loadPersonList();

 Apteka apteka = new Apteka();
        apteka.setPharmacists(pharmacists);

        SaveDB.savePharmacistList(apteka);


        ArrayList<Pharmacist> docs= Service.sortByGender(pharmacists,Gender.FEMALE);
        docs.forEach(System.out::println);


    }

}
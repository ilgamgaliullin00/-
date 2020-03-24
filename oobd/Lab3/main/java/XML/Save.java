package XML;

import java.util.Date;

public class Save {
    public static void main(String[] args) {


        Apteka apteka = new Apteka("1-ая аптека","Глушко");

        Area area1=new Area("12");
        apteka.addArea(area1);

        Client client1 = new Client("Марат",new Date(),Gender.MALE,area1);
        Pharmacist pharmacist1 = new Pharmacist("Нияз",new Date(),Gender.MALE,area1,"фармацевт");
        Pharmacist pharmacist2 = new Pharmacist("Шаташа",new Date(),Gender.FEMALE,area1,"фармацевт");
        Pharmacist pharmacist3 = new Pharmacist("Марина",new Date(),Gender.FEMALE,area1,"фармацевт");
        Pharmacist pharmacist4 = new Pharmacist("Алина",new Date(),Gender.FEMALE,area1,"фармацевт");
        Pharmacist pharmacist5 = new Pharmacist("Булат",new Date(),Gender.MALE,area1,"фармацевт");

        apteka.addPharmacist(pharmacist1);
        apteka.addPharmacist(pharmacist2);
        apteka.addPharmacist(pharmacist3);
        apteka.addPharmacist(pharmacist4);
        apteka.addPharmacist(pharmacist5);
        apteka.addСlient(client1);

        Service.saveClinicData(apteka);
    }
}
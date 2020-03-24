package XML;

public class Main {
    public static void main(String args[]){
        Apteka apteka = Service.loadBankFromXML();
        System.out.println(Service.sortByGender(apteka.getPharmacists(),Gender.FEMALE));
    }
}
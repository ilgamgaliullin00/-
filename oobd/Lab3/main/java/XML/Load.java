package XML;

public class Load {

    public static void main(String[] args) {

        Apteka apteka = Service.loadBankFromXML();

        System.out.println(apteka);
    }
}
package XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Service {


    //сохранение в xml файл
    public static void saveClinicData(XML.Apteka apteka) {

        try {
            JAXBContext context = JAXBContext.newInstance(XML.Apteka.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(apteka, new File("apteka.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


//выгрузка из xml файла
    public static Apteka loadBankFromXML() {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(XML.Apteka.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Apteka) un.unmarshal(new File("apteka.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    //метод нахождения клиента по имени
    public static Client findClientByName(List<Client> clients, String searchName) {
        XML.Client result = null;

        for (Client client : clients) {
            if(client.getName().equals(searchName)) {
                result = client;
            }
        }

        return result;
    }
    public static Client findClientByArea(List<Client> clients, String searchArea) {
        XML.Client result = null;

        for (Client client : clients) {
            if (client.getArea().toString().equals(searchArea)) {
                result = client;
            }
        }

        return result;
    }

    public static Client findClientByGender(List<Client> clients, String searchGender) {
        XML.Client result = null;

        for (Client client : clients) {

            if (client.getGender().toString().equals(searchGender)) {
                result = client;
            }
        }

        return result;
    }
    public static Client findClientByBirthdate(List<Client> clients, String searchBirthdate) {
        Client result = null;

        for (Client client : clients) {
            if (client.getBirthDate().toString().equals(searchBirthdate)) {
                result = client;
            }
        }

        return result;
    }
    public static Pharmacist findPharmacistByName(List<Pharmacist> pharmacists, String searchName) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getName().equals(searchName)) {
                result = pharmacist;
            }
        }

        return result;
    }
    public static Pharmacist findPharmacistByArea(List<Pharmacist> pharmacists, String searchArea) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getArea().toString().equals(searchArea)) {
                result = pharmacist;
            }
        }

        return result;
    }
    public static Pharmacist findPharmacistBySpecialty(List<Pharmacist> pharmacists, String searchSpecialty) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getSpecialty().equals(searchSpecialty)) {
                result = pharmacist;
            }
        }

        return result;
    }
    public static Pharmacist findPharmacistrByGender(List<Pharmacist> pharmacists, String searchGender) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            System.out.println(pharmacist.getGender());
            if (pharmacist.getGender().toString().equals(searchGender)) {
                result = pharmacist;
            }
        }

        return result;
    }
    public static Pharmacist findPharmacistByBirthdate(List<Pharmacist> pharmacists, String searchBirthdate) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getBirthDate().toString().equals(searchBirthdate)) {
                result = pharmacist;
            }
        }

        return result;
    }

    public static  ArrayList<Pharmacist> sortByArea(List<Pharmacist> pharmacists, String searchArea){
        ArrayList<Pharmacist> result= new ArrayList<>(Collections.nCopies(pharmacists.size(), null));
        int k=0;
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getArea().toString().equals(searchArea)) {
                result.add(pharmacists.size() - k,pharmacist);
                k++;
            }else
                result.add(pharmacist);

        }
        ArrayList<Pharmacist> res=new ArrayList<>();
        for (int i=0;i<result.size();i++){
            try {
                if(!result.get(i).equals(null))
                    res.add(result.get(i));
            }
            catch (NullPointerException e){

            }

        }

        return res;
    }

    public static  ArrayList<Pharmacist> sortByAge(List<Pharmacist> pharmacists, long age){
        ArrayList<Pharmacist> result= new ArrayList<>(Collections.nCopies(pharmacists.size(), null));
        int k=0;
        int c=0;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getBirthDate().getYear()<new Date().getYear()-age) {
                result.add(pharmacists.size() - k,pharmacist);
                k++;
            }else {
                result.add(pharmacist);
                c++;
            }

        }
        ArrayList<Pharmacist> res=new ArrayList<>();
        for (int i=0;i<result.size();i++){
            try {
                if(!result.get(i).equals(null))
                    res.add(result.get(i));
            }
            catch (NullPointerException e){

            }

        }

        return res;
    }

    public static  ArrayList<Pharmacist> sortBySpecialty(List<Pharmacist> pharmacists, String searchSpecialty){
        ArrayList<Pharmacist> result= new ArrayList<>(Collections.nCopies(pharmacists.size(), null));
        int k=0;
        int c=0;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getSpecialty().equals(searchSpecialty)) {
                result.add(pharmacists.size() - k,pharmacist);
                k++;
            }else {
                result.add(pharmacist);
                c++;
            }

        }
        ArrayList<Pharmacist> res=new ArrayList<>();
        for (int i=0;i<result.size();i++){
            try {
                if(!result.get(i).equals(null))
                    res.add(result.get(i));
            }
            catch (NullPointerException e){

            }

        }

        return res;
    }
    public static  ArrayList<Pharmacist> sortByGender(List<Pharmacist> pharmacists, Gender gender){
        ArrayList<Pharmacist> result= new ArrayList<>(Collections.nCopies(pharmacists.size(), null));
        int k=0;
        int c=0;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getGender().equals(gender)) {
                result.add(pharmacists.size() - k,pharmacist);
                k++;
            }else {
                result.add(pharmacist);
                c++;
            }

        }
        ArrayList<Pharmacist> res=new ArrayList<>();
        for (int i=0;i<result.size();i++){
            try {
                if(!result.get(i).equals(null))
                    res.add(result.get(i));
            }
            catch (NullPointerException e){

            }

        }

        return res;
    }
}
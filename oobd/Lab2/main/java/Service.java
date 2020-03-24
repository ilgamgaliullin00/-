import com.google.gson.InstanceCreator;

import javax.print.Doc;
import javax.print.attribute.standard.Copies;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Service {

    public static Pharmacist findPersonByName(List<Pharmacist> pharmacists, String searchName) {
        Pharmacist result = null;

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getName().equals(searchName)) {
                result = pharmacist;
            }
        }

        return result;
    }
    public static Pharmacist findDoctorByArea(List<Pharmacist> pharmacists, String searchArea) {
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
    public static Pharmacist findPharmacistByGender(List<Pharmacist> pharmacists, String searchGender) {
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
        ArrayList<Pharmacist> result= new ArrayList<Pharmacist>(Collections.<Pharmacist>nCopies(pharmacists.size(), null));
        int k=0;
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getArea().toString().equals(searchArea)) {
                result.add(pharmacists.size() - k,pharmacist);
                k++;
            }else
                result.add(pharmacist);

        }
        ArrayList<Pharmacist> res=new ArrayList<Pharmacist>();
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
        ArrayList<Pharmacist> result= new ArrayList<Pharmacist>(Collections.<Pharmacist>nCopies(pharmacists.size(), null));
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
        ArrayList<Pharmacist> res=new ArrayList<Pharmacist>();
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
        ArrayList<Pharmacist> result= new ArrayList<Pharmacist>(Collections.<Pharmacist>nCopies(pharmacists.size(), null));
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
        ArrayList<Pharmacist> res=new ArrayList<Pharmacist>();
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
        ArrayList<Pharmacist> result= new ArrayList<Pharmacist>(Collections.<Pharmacist>nCopies(pharmacists.size(), null));
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
        ArrayList<Pharmacist> res=new ArrayList<Pharmacist>();
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
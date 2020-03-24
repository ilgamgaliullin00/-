package DB;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class Pharmacist extends Person {
    Area area;
    String specialty;

    public Pharmacist() {
    }

    public Pharmacist(Area area, String specialty) {
        this.area = area;
        this.specialty = specialty;
    }

    public String toString() {
        return "Pharmacist{area=" + this.area + ", specialty='" + this.specialty + '\'' + ", name='" + this.name + '\'' + ", birthDate=" + birthDate + ", gender=" + this.gender + '}';
    }

    public Pharmacist(String name, Date birthDate, Gender gender, Area area, String specialty) {
        super(name, birthDate, gender);
        this.area = area;
        this.specialty = specialty;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getSpecialty() {
        return this.specialty;
    }


    @XmlElement(name = "specialty")
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
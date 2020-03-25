package domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@Entity
public class Pharmacists extends Person {


    @ManyToOne
    Area area;
    @Column
    String specialty;
    @Id
    private Long id;

    public Pharmacists() {
    }

    public Pharmacists(Area area, String specialty) {
        this.area = area;
        this.specialty = specialty;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String toString() {
        return "DB.domain.Pharmacists{area=" + this.area + ", specialty='" + this.specialty + '\'' + ", name='" + this.name + '\'' + ", birthDate=" + birthDate + '}';
    }

    public Pharmacists(String name, Date birthDate,  Area area, String specialty) {
        super(name, birthDate);
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
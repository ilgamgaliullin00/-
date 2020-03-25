package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client extends Person {

    @ManyToOne
    Area area;

    @OneToMany
    List<Drug> drug=new ArrayList<>();
    @Id
    private Long id;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client(Area area, List<Drug> drug) {
        this.area = area;
        this.drug = drug;
    }



    public Client(String name, Date birthDate, Area area) {
        super(name, birthDate);
        this.area = area;
    }

    public Client(String name, Date birthDate,  Area area, List<Drug> drug) {
        super(name, birthDate);
        this.area = area;
        this.drug = drug;
    }
    @XmlElementWrapper(name = "drug")
    @XmlElement(name = "drug")
    public List<Drug> getDrug() {
        return this.drug;
    }

    public void setDrug(List<Drug> drug) {
        this.drug = drug;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }


}
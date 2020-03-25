package domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@Entity
public class Area {
    @Column
    String name;
    @Id
    private Long id;

    public Area() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return   name;
    }

    public String getArea() {

        return this.name;
    }
    public Long getId() {
        return id;
    }
    public Area(String area) {
        this.name = area;
    }

    public void setArea(String area) {
        this.name = area;
    }
}
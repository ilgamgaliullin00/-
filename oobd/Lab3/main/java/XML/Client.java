package XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Date;
import java.util.List;

public class Client extends Person {
    Area area;
    List<String> diagnoses;

    public Client() {
    }

    public Client(Area area, List<String> diagnoses) {
        this.area = area;
        this.diagnoses = diagnoses;
    }

    public String toString() {
        return "Client{area=" + this.area + ", diagnoses=" + this.diagnoses + ", name='" + this.name + '\'' + ", birthDate=" + new Date() + ", gender=" + this.gender + '}';
    }

    public Client(String name, Date birthDate, Gender gender, Area area) {
        super(name, birthDate, gender);
        this.area = area;
    }

    public Client(String name, Date birthDate, Gender gender, Area area, List<String> diagnoses) {
        super(name, birthDate, gender);
        this.area = area;
        this.diagnoses = diagnoses;
    }
    @XmlElementWrapper(name = "diagnoses")
    @XmlElement(name = "diagnose")
    public List<String> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void addDiagnose(String diagnose) {
        this.diagnoses.add(diagnose);
    }
}
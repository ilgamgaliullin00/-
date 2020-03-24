package oodb.lab5.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeSet;

@XmlRootElement(name = "apteka")
public class Apteka {
    private String name;
    private TreeSet<Drug> drugs = new TreeSet<>();

    public Apteka() {
    }

    public Apteka(String name, TreeSet<Drug> drugs) {
        this.name = name;
        this.drugs = drugs;
    }

    Apteka(String name) {
        this.name = name;
    }

    void addDrug(Drug drug) {
        drugs.add(drug);
    }

    void removeDrug(Drug drug) {
        drugs.remove(drug);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "drugs")
    @XmlElement(name = "drug")
    public TreeSet<Drug> getDrug() {
        return drugs;
    }

    public void setDrugs(TreeSet<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Аптека {" +
                "навзание = '" + name + '\'' +
                ", список лекарств = " + drugs +
                '}';
    }
}
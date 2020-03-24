import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Сlient extends Person {
    Area area;
    List<String> diagnoses;

    public Сlient() {
    }

    public Сlient(Area area, List<String> diagnoses) {
        this.area = area;
        this.diagnoses = diagnoses;
    }

    public String toString() {
        return "Сlient{area=" + this.area + ", diagnoses=" + this.diagnoses + ", name='" + this.name + '\'' + ", birthDate=" + new Date() + ", gender=" + this.gender + '}';
    }

    public Сlient(String name, Date birthDate, Gender gender, Area area) {
        super(name, birthDate, gender);
        this.area = area;
    }

    public Сlient(String name, Date birthDate, Gender gender, Area area, List<String> diagnoses) {
        super(name, birthDate, gender);
        this.area = area;
        this.diagnoses = diagnoses;
    }

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
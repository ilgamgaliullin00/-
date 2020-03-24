import java.util.ArrayList;
import java.util.List;

public class Apteka {
    String name;
    String address;
    List<Area> areas = new ArrayList();
    List<Pharmacist> pharmacists = new ArrayList();
    protected ArrayList<Сlient> сlients = new ArrayList();

    public Apteka(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Apteka() {
    }

    void addPharmacist(Pharmacist pharmacist) {

        this.pharmacists.add(pharmacist);
    }

    void addСlient(Сlient сlient) {

        this.сlients.add(сlient);
    }

    public void setAreas(ArrayList<Area> areas) {

        this.areas = areas;
    }

    public List<Pharmacist> getPharmacists() {

        return this.pharmacists;
    }

    public void setPharmacists(List<Pharmacist> pharmacist) {

        this.pharmacists = pharmacist;
    }

    protected ArrayList<Сlient> getСlients() {

        return this.сlients;
    }

    protected void setСlients(ArrayList<Сlient> сlients) {

        this.сlients = сlients;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public void setArea(ArrayList<Area> areas) {

        this.areas = areas;
    }

    public String getName() {

        return this.name;
    }

    public String getAddress() {

        return this.address;
    }

    public List<Area> getAreas() {

        return this.areas;
    }

    public void addArea(Area area) {

        this.areas.add(area);
    }

    public String toString() {
        return "Apteka{name='" + this.name + '\'' + ", address='" + this.address + '\'' + ", areas=" + this.areas + ", pharmacists=" + this.pharmacists + '}';
    }
}



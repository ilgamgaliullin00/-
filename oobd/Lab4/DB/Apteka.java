package DB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name = "apteka")
public class Apteka {
    String name;
    String address;
    ArrayList<Area> areas = new ArrayList();
    ArrayList<Pharmacist> pharmacists = new ArrayList();
    protected ArrayList<Client> clients = new ArrayList();

    public Apteka(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Apteka() {
    }

    void addPharmacist(Pharmacist pharmacist) {
        this.pharmacists.add(pharmacist);
    }

    void addClient(Client client) {
        this.clients.add(client);
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    @XmlElementWrapper(name = "pharmacists")
    @XmlElement(name = "pharmacist")
    public ArrayList<Pharmacist> getPharmacists() {
        return this.pharmacists;
    }

    public void setPharmacists(ArrayList<Pharmacist> pharmacists) {
        this.pharmacists = pharmacists;
    }

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    protected ArrayList<Client> getClients() {
        return this.clients;
    }

    protected void setPatients(ArrayList<Client> clients) {
        this.clients = clients;
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

    @XmlElementWrapper(name = "areas")
    @XmlElement(name = "area")
    public ArrayList<Area> getAreas() {
        return this.areas;
    }

    public void addArea(Area area) {
        this.areas.add(area);
    }

    @Override
    public String toString() {
        return "Apteka{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", areas=" + areas +
                ", pharmacists=" + pharmacists +
                ", clients=" + clients +
                '}';
    }
}
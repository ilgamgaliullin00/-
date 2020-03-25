package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Apteka {
    @Column
    String name;
    @Column
    String address;
    @Column
    @OneToMany
    List<Area> areas = new ArrayList();
    @Column
    @OneToMany
    List<Pharmacists> pharmacists = new ArrayList();
    @Column
    @OneToMany
    protected List<Client> clients = new ArrayList();

    @Id
    private Long id;

    public Apteka(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public Apteka() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    void addPharmacist(Pharmacists pharmacist) {
        this.pharmacists.add(pharmacist);
    }

    void addClient(Client client) { this.clients.add(client);
    }

    public void setAreas(ArrayList<Area> areas) {
        this.areas = areas;
    }

    @XmlElementWrapper(name = "pharmacists")
    @XmlElement(name = "pharmacist")
    public List<Pharmacists> getPharmacists() {
        return this.pharmacists;
    }

    public void setPharmacists(ArrayList<Pharmacists> pharmacists) {
        this.pharmacists = pharmacists;
    }

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    protected List<Client> getClients() {
        return this.clients;
    }

    protected void setClients(ArrayList<Client> clients) {
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
    public List<Area> getAreas() {
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
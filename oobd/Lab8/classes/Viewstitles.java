package classes;

import annotation.Column;
import annotation.Entity;
import annotation.Id;
import annotation.OneToMany;

import java.util.List;

@Entity
public class Viewstitles {
    @Id
    Long id;

    @Column
    String viewstitles;


    @OneToMany
    List<Drug> drugs;

    @Override
    public String toString() {
        return "Viewstitles{" +
                "id=" + id +
                ", viewstitles='" + viewstitles + '\'' +
                ", drugs=" + drugs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getViewstitles() {
        return viewstitles;
    }

    public void setSectionstitles(String viewstitles) {
        this.viewstitles = viewstitles;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
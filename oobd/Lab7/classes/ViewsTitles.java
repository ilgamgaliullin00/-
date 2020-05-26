package classes;

import annotation.Column;
import annotation.Entity;
import annotation.Id;
import annotation.OneToMany;

import java.util.List;

@Entity
public class ViewsTitles {
    @Id
    Long id;

    @Column
    String viewstitles;


    @OneToMany
    List<Drug> drugs;



    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
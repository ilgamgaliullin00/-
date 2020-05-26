package classes;

import annotation.Column;
import annotation.Entity;
import annotation.Id;
import annotation.ManyToMany;

import java.util.Collection;
import java.util.TreeSet;

@Entity
public class Apteka {
    @Id
    Long id;
    @Column
    String name;


    @ManyToMany
    Collection<Drug> drug = new TreeSet<>();

    public Apteka() {
    }


//  public Apteka(Long id, String name, TreeSet<Drug> drugs) {
//    this.id = id;
//    this.name = name;
//    this.drugs = drugs;
//  }
//
//  public Apteka(String name, TreeSet<Drug> drugs) {
//    this.name = name;
//    this.drugs = drugs;
//  }
//
//
//  Apteka(String name) {
//    this.name = name;
//  }
//
//  void addBook(Drug drug) {
//    drugs.add(drug);
//  }

//  void removeBook(Drug drug) {
////    drugs.remove(drug);
//  }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//  public TreeSet<Drug> getDrugs() {
//    return (TreeSet<Drug>) drugs;
//  }
//
//  public void setDrugs(TreeSet<Drug> drugs) {
//    this.drugs = drugs;
//  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//  @Override
//  public String toString() {
//    return "Аптека {" +
//            "навзание = '" + name + '\'' +
//            ", список лекарств = " + drugs +
//            '}';
//  }
}
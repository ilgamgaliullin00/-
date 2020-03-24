package lab6.classes;


import lab6.annotation.Column;
import lab6.annotation.Entity;
import lab6.annotation.OneToMany;

import java.util.TreeSet;

@Entity
public class Apteka {
  @Column
  int id;
  @Column
  private String name;
  @Column
  @OneToMany
  private TreeSet<Drug> drugs = new TreeSet<>();

  public Apteka() {
  }

  public Apteka(int id, String name, TreeSet<Drug> drugs) {
    this.id = id;
    this.name = name;
    this.drugs = drugs;
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

  public TreeSet<Drug> getDrugs() {
    return drugs;
  }

  public void setBooks(TreeSet<Drug> drugs) {
    this.drugs = drugs;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Аптека {" +
            "навзание = '" + name + '\'' +
            ", список лекарств = " + drugs +
            '}';
  }
}

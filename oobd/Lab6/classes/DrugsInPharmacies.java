package lab6.classes;


import lab6.annotation.Column;
import lab6.annotation.ManyToOne;

public class DrugsInPharmacies {
  @Column
  int id;

  @Column
  @ManyToOne
  Drug drug;

  @Column
  @ManyToOne
  Apteka apteka;


  public DrugsInPharmacies(int id, Drug drug, Apteka apteka) {
    this.id = id;
    this.drug = drug;
    this.apteka = apteka;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Drug getDrug() {
    return drug;
  }

  public void setDrug(Drug drug) {
    this.drug = drug;
  }

  public Apteka getApteka() {
    return apteka;
  }

  public void setApteka(Apteka apteka) {
    this.apteka = apteka;
  }
}

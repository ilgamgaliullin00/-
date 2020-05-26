package classes;


import annotation.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Drug implements Comparable<Drug> {
    @Id
    Long id;

    @Column
    @ManyToOne
    Pharmacist pharmacist;

    @Column
    @ManyToOne
    Viewstitles viewstitles;

    @Column
    String title;


    @Column
    String dateofpublishing;
    @Column
    Double price;

    @ManyToMany
    Collection<Apteka> apteks;

    public String getDateofpublishing() {
        return dateofpublishing;
    }

    public void setDateofpublishing(String dateofpublishing) {
        this.dateofpublishing = dateofpublishing;
    }

    //пустой конструктор тк ругается при чтении из файла
    //Class.forName( имя  класса).newInstance().
    public Drug() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", pharmacist=" + pharmacist +
                ", viewstitles=" + viewstitles +
                ", title='" + title + '\'' +
                ", dateOfPublishing='" + dateofpublishing + '\'' +
                ", price=" + price +
                ", apteks=" + apteks +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public Viewstitles getViewstitles() {
        return viewstitles;
    }

    public void setViewstitles(Viewstitles viewstitles) {
        this.viewstitles = viewstitles;
    }

    public Collection<Apteka> getLibraries() {
        return apteks;
    }

    public void setApteks(Collection<Apteka> apteks) {
        this.apteks = apteks;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    // переопредлили equals и hashCode, чтобы нельзя было добавить книги со всеми одинковыми параметрами
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Double.compare(drug.price, price) == 0 &&
                viewstitles.equals(drug.viewstitles) &&
                Objects.equals(title, drug.title) &&
                Objects.equals(pharmacist, drug.pharmacist) &&
                Objects.equals(dateofpublishing, drug.dateofpublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewstitles, title, pharmacist, dateofpublishing, price);
    }

    //метод для сравнения в сортировке
    @Override
    public int compareTo(Drug o) {
        return (int) (this.getPrice() - o.getPrice());
    }
}
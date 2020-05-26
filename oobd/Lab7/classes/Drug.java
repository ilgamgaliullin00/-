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
    ViewsTitles viewsTitles;

    @Column
    String title;





    @Column
    String dateOfPublishing;
    @Column
    double price;

    @ManyToMany
    Collection<Apteka> Aptekas;

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




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public String getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(String dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Лекарство {" +
                "секция = " + viewsTitles +
                ", название = '" + title + '\'' +
                ", фармацевт = " + pharmacist +
                ", дата публикации = '" + dateOfPublishing + '\'' +
                ", цена = " + price +
                '}';
    }

    // переопредлили equals и hashCode, чтобы нельзя было добавить книги со всеми одинковыми параметрами
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Double.compare(drug.price, price) == 0 &&
                viewsTitles.equals(drug.viewsTitles) &&
                Objects.equals(title, drug.title) &&
                Objects.equals(pharmacist, drug.pharmacist) &&
                Objects.equals(dateOfPublishing, drug.dateOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewsTitles, title, pharmacist, dateOfPublishing, price);
    }

    //метод для сравнения в сортировке
    @Override
    public int compareTo(Drug o) {
        return (int) (this.getPrice() - o.getPrice());
    }
}
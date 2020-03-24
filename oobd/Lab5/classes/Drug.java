package oodb.lab5.classes;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class Drug implements Comparable<Drug> {
    private Section section;
    private String title;
    private Author author;
    private String dateOfPublishing;
    private double price;

    //пустой конструктор тк ругается при чтении из файла
    //Class.forName( имя  класса).newInstance().
    public Drug() {
    }

    public Drug(Section section, String title, Author author, String dateOfPublishing, double price) {
        this.section = section;
        this.title = title;
        this.author = author;
        this.dateOfPublishing = dateOfPublishing;
        this.price = price;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @XmlElement(name = "author")
    Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
        return "Лекарство{" +
                "секция = " + section +
                ", название = '" + title + '\'' +
                ", автор = " + author +
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
                section == drug.section &&
                Objects.equals(title, drug.title) &&
                Objects.equals(author, drug.author) &&
                Objects.equals(dateOfPublishing, drug.dateOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section, title, author, dateOfPublishing, price);
    }

    //метод для сравнения в сортировке
    @Override
    public int compareTo(Drug o) {
        return (int) (this.getPrice()-o.getPrice());
    }
}
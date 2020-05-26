package classes;


import annotation.Column;
import annotation.Entity;
import annotation.Id;
import annotation.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity(name = "Pharmacist")
public class Pharmacist extends Person {
    @Id
    Long id;

    @Column
    String info;



    @OneToMany
    List<Drug> drug;


    //пустой конструктор тк ругается при чтении из файла
    public Pharmacist() {
    }

    public Pharmacist(String name, String patronymic, String lastName, String info) {
        super(name, patronymic, lastName);
        this.info = info;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public String getFullName() {
        return name + " " + patronymic + " " + lastname;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Фармацевт {" +
                "информация ='" + info + '\'' +
                ", ФИО ='" + lastname + " " + name + " " + patronymic + '}';
    }


    //hashCode и equals, чтобы при доавлении такого же автора нельзя юыло создать книгу с сущ параметрами
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacist pharmacist = (Pharmacist) o;
        return Objects.equals(info, pharmacist.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
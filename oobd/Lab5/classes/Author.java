package oodb.lab5.classes;

import java.util.Objects;

public class Author extends Person {
    private String info;


    //пустой конструктор тк ругается при чтении из файла
    public Author() {
    }

    public Author(String name, String patronymic, String lastName, String info) {
        super(name, patronymic, lastName);
        this.info = info;
    }


    public String getInfo() {
        return info;
    }

    public String getFullName() {
        return name + " " + patronymic + " " + lastName;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Автор {" +
                "информация ='" + info + '\'' +
                ", ФИО ='" + lastName + " " + name + " " + patronymic + '}';
    }


    //hashCode и equals, чтобы при доавлении такого же автора нельзя юыло создать книгу с сущ параметрами
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(info, author.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
package oodb.lab5.classes;

public class Person {
    String name;
    String patronymic;
    String lastName;

    public Person() {
    }

    Person(String name, String patronymic, String lastName) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
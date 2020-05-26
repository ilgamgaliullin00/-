package classes;


import annotation.Column;

abstract class Person {
    @Column
    String name;
    @Column
    String patronymic;
    @Column
    String lastname;


    public Person() {
    }

    Person(String name, String patronymic, String lastName) {
        this.name = name;
        this.patronymic = patronymic;
        this.lastname = lastName;
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
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
}
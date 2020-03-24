/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptekab;

import java.util.List;
import java.util.Scanner;

//  реализация сотрудников
public class Employee extends Human {
    Scanner in = new Scanner(System.in);
    List<Human> listOfEmployee;

    Employee(String name, String number, char gender, double salary) {
        super(name, number, gender, salary);
    }

    //    нанять нового сотрудника
    public void addEmployee() {
        System.out.print("Введите имя нового сотрудника: ");
        String name = in.next();
        System.out.print("Номер телефона: ");
        String number = in.next();
        System.out.print("Введите пол: ");
        char gender = in.next().charAt(0);
        System.out.print("Зарплата: ");
        double salary = in.nextDouble();
        listOfEmployee.add(new Human(name, number, gender, salary));
    }

    //    уволить сотрудника
    public void dismiss() {
        System.out.println("Введите имя сотрудника, которого вы хотите уволить:");
        String name = in.next();
        for (Human list : listOfEmployee) {
            if (list.getName().equals(name)) {
                listOfEmployee.remove(name);
            }
        }
    }

    //    вывод на экран всех сотрудников
    public void printEmployee() {
        System.out.println(listOfEmployee);
    }
}


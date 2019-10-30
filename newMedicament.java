/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptekab;

import java.util.List;
import java.util.Scanner;

//реализация препората
public class newMedicament  extends Medicament {
    
  Scanner in = new Scanner(System.in);
    List<Medicament> listOfMedicament;

    newMedicament(String name, double price, boolean stock) {
        super(name, price, stock);
    }

    //    добавление препрата
    public void addProduct() {
        System.out.print("Введите название нового товара: ");
        String name = in.next();

        System.out.print("Введите цену: ");
        double price = in.nextDouble();

        System.out.print("Наличие: ");
        boolean stock = in.nextBoolean();

        listOfMedicament.add(new Medicament(name, price, stock));
    }

    //    удаление препората
    public void deleteMedicament() {
        System.out.println("Введите название товара, который хотите удалить:");
        String name = in.next();

        for (Medicament list : listOfMedicament) {
            if (list.getName().equals(name)) {
                listOfMedicament.remove(list);
            }
        }
    }

    //  вывод на экран
    public void printMedicament() {
        System.out.println(listOfMedicament);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aptekab;

import java.util.List;
import java.util.Scanner;

    
public class Town extends Apteka {
    Scanner in = new Scanner(System.in);

    private List<Apteka> listOfApteka;

    Town(String name, String address, String hours) {
        super(name, address, hours);
    }


    //    открыть аптеку 
    public void toExtendAptekaB() {
        System.out.print("Введите город, в котором вы хотите открыть магазин: ");
        String city = in.next();
        System.out.print("Введите адрес: ");
        String address = in.next();
        System.out.print("Введите часы работы магазина: ");
        String hours = in.next();

        listOfApteka.add(new Apteka(city, address, hours));
    }
}

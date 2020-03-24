package oodb.lab5.classes;

import java.util.Comparator;
//сравнивание
public class NameComparator implements Comparator<Drug> {
    @Override
    public int compare(Drug o1, Drug o2) {
        return (int) (o1.getPrice()-o2.getPrice());
    }
}
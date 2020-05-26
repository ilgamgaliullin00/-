
import annotation.Entity;
import classes.Pharmacist;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanPackages {

    private static String PATH_FOR_SCAN = "classes";

    List<String> getEntities() {
//     Просканируем пакет PATH_FOR_SCAN для поиска классов (включая вложенные пакеты)
//    System.out.println("STEP 1: scan package " + PATH_FOR_SCAN + ":");
        List<String> entities = new ArrayList<>();
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList != null) {
//      classList.forEach(c -> System.out.println("\t" + c.getCanonicalName()));

            for (int i = 0; i < classList.size(); i++) {
                Field[] fields = classList.get(i).getDeclaredFields();
            }
        }
//    System.out.println("STEP 2: scan class annotations: ");
        for (Class<?> c : classList) {
            Annotation[] annotations = c.getAnnotations();
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(Entity.class)) {
//            System.out.println("\t\t" + c.getSimpleName() + " is entity!");
                        entities.add(c.getSimpleName());
                    }
                }
            }
        }
        return entities;
    }


    List<String> getAllTables() {
//     Просканируем пакет PATH_FOR_SCAN для поиска классов (включая вложенные пакеты)
        System.out.println("STEP 1: scan package " + PATH_FOR_SCAN + ":");
        List<String> tables = new ArrayList<>();
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList != null) {
            classList.forEach(c -> System.out.println("\t" + c.getCanonicalName()));

            for (int i = 0; i < classList.size(); i++) {
                tables.add(classList.get(i).getSimpleName().toLowerCase());
            }
        }
        return tables;
    }


    ArrayList<String> getFields(String className) {
        ArrayList<String> fieldsList = new ArrayList<>();
        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);

        for (Class<?> cl : classList) {
            Field[] fields;
            if (cl.getSimpleName().contains(className)) {
                if (className.equals("Pharmacist")) {
                    Field[] personFields = Pharmacist.class.getSuperclass().getDeclaredFields();
                    Field[] authorFields = Pharmacist.class.getDeclaredFields();
                    fields = new Field[authorFields.length + personFields.length];
                    Arrays.setAll(fields, i ->
                            (i < personFields.length ? personFields[i] : authorFields[i - personFields.length]));
                    for (Field field : fields) {
                        System.out.println("\t\t" + field.getName());
                        fieldsList.add(field.getName());
                    }
                } else  {

                    fields = cl.getDeclaredFields();
//        fieldsList = new ArrayList<String>(Arrays.asList(fields));
                    for (Field field : fields) {
                        System.out.println("\t\t" + field.getName());
                        fieldsList.add(field.getName());
                    }
                }

            }
        }
        return fieldsList;

    }
}
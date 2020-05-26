
import annotation.*;
import classes.Pharmacist;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ScanClasses {
    static List<Node> nodeList = new ArrayList<>();
    static List<Edge> edgeList = new ArrayList<>();
    static File fileGraph = new File("graph.graphml");

    public static String PATH_FOR_SCAN = "classes";

    static Node find(Class className) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).className.equals(className))
                return nodeList.get(i);
        }
        return null;
    }

    static Node find(String className) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).className.toString().equals("class " + className))
                return nodeList.get(i);
        }
        return null;
    }


    static String getNormalType(String type) {
        if (type.contains("List")) {
//      System.out.println("generic List= " + type);
            String[] fieldTypeSplit1 = type.split("<");
            String[] fieldTypeSplit2 = fieldTypeSplit1[fieldTypeSplit1.length - 1].split("\\.");
            String generic = fieldTypeSplit2[fieldTypeSplit2.length - 1].substring(0, fieldTypeSplit2.length);

            String[] fieldTypeSplit3 = fieldTypeSplit1[0].split("\\.");
            String mainType = fieldTypeSplit3[fieldTypeSplit3.length - 1];
            type = mainType + "=" + generic;

//      System.out.println("generic List= " + type);
        } else if (type.contains("Collection")) {
            String[] fieldTypeSplit1 = type.split("<");
            String[] fieldTypeSplit2 = fieldTypeSplit1[fieldTypeSplit1.length - 1].split("\\.");
            String generic = fieldTypeSplit2[fieldTypeSplit2.length - 1];
            generic = generic.substring(0,generic.length()-1);

            String[] fieldTypeSplit3 = fieldTypeSplit1[0].split("\\.");
            String mainType = fieldTypeSplit3[fieldTypeSplit3.length - 1];
            type = mainType + "=" + generic;
//      System.out.println("generic Collection = " + type);

        } else {
//      System.out.println("Тип " + type);
            String[] fieldTypeSplit = type.split("\\.");
            type = fieldTypeSplit[fieldTypeSplit.length - 1]+"="+" ";
//      System.out.println("Тип " + type);
        }
        return type;
    }

    static LinkedHashMap<String, LinkedHashMap<String, String>> getInfoAboutClasses() {
        LinkedHashMap<String, HashMap<String, Annotation>> classesInfo = new LinkedHashMap<String, HashMap<String, Annotation>>();
        /* Просканируем пакет PATH_FOR_SCAN для поиска классов (включая вложенные пакеты)  */
//    System.out.println("STEP 1: scan package " + PATH_FOR_SCAN + ":");

        List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
        if (classList != null) {
//      classList.forEach(c -> System.out.println("\t" + c.getCanonicalName()));

            for (int i = 0; i < classList.size(); i++) {
                Field[] fields = classList.get(i).getDeclaredFields();
                Attribute[] attributes = new Attribute[fields.length];
                for (int j = 0; j < fields.length; j++) {
                    Attribute attribute = new Attribute(fields[j].getName(), fields[j].getType().getSimpleName());
                    attributes[j] = attribute;
                }

                Node node = new Node(classList.get(i), attributes);
                nodeList.add(node);
            }
        }
        for (int i = 0; i < nodeList.size(); i++) {
//      System.out.println(nodeList.get(i));
        }


        HashMap<String, ArrayList<String>> tablesAndFields = new HashMap<String, ArrayList<String>>();

//    System.out.println("STEP 2: scan class fields:");
        for (Class<?> cl : classList) {
            //мпимок полей
            ArrayList<String> fieldsList = new ArrayList<>();
            /* Сканируем поля классов */
//      System.out.println("\tFields of class " + cl.getName());
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
//        System.out.println("\t\t" + field.getName());
//        System.out.println("\t\t" + field.getType());
                fieldsList.add(field.getName());

            }
            tablesAndFields.put(cl.getSimpleName(), fieldsList);

        }

//    System.out.println("STEP 3: scan class methods:");
        for (Class<?> cl : classList) {
            /* Сканируем методы классов */
//      System.out.println("\tMethods of class " + cl.getName());
            Method[] methods = cl.getMethods();
            for (Method method : methods) {
//        System.out.println("\t\t" + method.getName());
            }
        }

        LinkedHashMap<String, LinkedHashMap<String, String>> all = new LinkedHashMap<>();

//    System.out.println("STEP 4: scan class annotations: ");
        for (Class<?> c : classList) {
//      System.out.println("\tAnnotations of class " + c.getName());
            Annotation[] annotations = c.getAnnotations();
            if (annotations != null) {
                for (Annotation a : annotations) {
                    if (a.annotationType().equals(Entity.class)) {
//            System.out.println("\t\t" + c.getSimpleName() + " is entity!");
                    }
                }
            }
        }

//    System.out.println("STEP 5: scan fields annotations:");
        for (Class<?> c : classList) {
            Field[] fields;
//      System.out.println("\tField annotations of class " + c.getName());
            if (c.getCanonicalName().equals("classes.Pharmacist")) {
                Field[] personFields = Pharmacist.class.getSuperclass().getDeclaredFields();
                Field[] authorFields = Pharmacist.class.getDeclaredFields();
                fields = new Field[authorFields.length + personFields.length];
                Arrays.setAll(fields, i ->
                        (i < personFields.length ? personFields[i] : authorFields[i - personFields.length]));
//        System.out.println("Автор = "+Arrays.toString(fields));

            } else fields = c.getDeclaredFields();


            LinkedHashMap<String, String> fieldAndAnnotation = new LinkedHashMap<>();
            for (Field f : fields) {
                String annotationName = "Column";
                Annotation[] annotations = f.getAnnotations();

                for (Annotation a : annotations) {
//          if (a.annotationType().equals(Column.class)) {
//            annotationName = Column.class.getSimpleName();
//            System.out.println(String.format("\t\tField %s %s is attribute!", f.getType().getSimpleName(), f.getName()));
//          }
                    if (a.annotationType().equals(Column.class)) {
                        annotationName = Column.class.getSimpleName();
                    }

                    if (a.annotationType().equals(Id.class)) {
                        annotationName = Id.class.getSimpleName();
                    }

                    if (a.annotationType().equals(OneToOne.class)) {
                        String fieldType = getNormalType(f.getGenericType().toString());
                        annotationName = OneToOne.class.getSimpleName() + "=" + fieldType;
                        String relationClass = f.getGenericType().getTypeName();
//            System.out.println(String.format("\t\tClass %s in relation OneToOne with class %s", c.getSimpleName(), relationClass));
                        Node node1 = find(c);
                        Node node2 = find(relationClass);
                        Edge edge = new Edge(node1, node2, RelationType.OneToOne);
//            System.out.println(edge);

                        edgeList.add(edge);
                    }

                    if (a.annotationType().equals(OneToMany.class)) {
                        String fieldType = getNormalType(f.getGenericType().toString());
//            String fieldGeneric
                        annotationName = OneToMany.class.getSimpleName() + "=" + fieldType;


                        String relationClass = f.getGenericType().getTypeName();
//            System.out.println(String.format("\t\tClass %s in relation OneToMany with class %s", c.getSimpleName(), relationClass.substring(relationClass.indexOf("<") + 1, relationClass.indexOf(">"))));
                        Node node1 = find(c);
                        Node node2 = find(relationClass.substring(relationClass.indexOf("<") + 1, relationClass.indexOf(">")));
                        Edge edge = new Edge(node1, node2, RelationType.OneToMany);
//            System.out.println(edge);

                        edgeList.add(edge);
                    }


                    if (a.annotationType().equals(ManyToOne.class)) {
                        String fieldType = getNormalType(f.getGenericType().toString());
                        annotationName = ManyToOne.class.getSimpleName() + "=" + fieldType;

                        String relationClass = f.getGenericType().getTypeName();
//            System.out.println(String.format("\t\tClass %s in relation ManyToOne with class %s", c.getSimpleName(), relationClass));
                        Node node1 = find(c);
                        Node node2 = find(relationClass);
                        Edge edge = new Edge(node1, node2, RelationType.ManyToOne);
//            System.out.println(edge);
                        edgeList.add(edge);
                    }

                    if (a.annotationType().equals(ManyToMany.class)) {
                        String fieldType = getNormalType(f.getGenericType().toString());
                        annotationName = ManyToMany.class.getSimpleName() + "=" + fieldType;

                    }


                    fieldAndAnnotation.put(f.getName(), annotationName);
                }
            }
            all.put(c.getSimpleName(), fieldAndAnnotation);
        }


//    System.out.println("STEP 6: get superclass:");

        Class superClass = Pharmacist.class.getSuperclass();
//    System.out.println("\tSuper class of Client is " + superClass.getName());


//    for (Node aNodeList : nodeList) {
//      System.out.println(aNodeList);
//
//    }
//
//    for (Edge edge : edgeList) {
//      System.out.println();
//      System.out.println("source " + edge.source.className.getSimpleName());
//      System.out.println("target " + edge.target.className.getSimpleName());
//      System.out.println("relation " + edge.relationType);
//
//    }

        return all;
    }


}
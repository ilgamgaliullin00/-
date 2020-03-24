package lab6;

import lab6.annotation.*;
import lab6.classes.Author;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static List<Node> nodeList = new ArrayList<>();
  static List<Edge> edgeList = new ArrayList<>();
  static File fileGraph = new File("graph.graphml");

  public static String PATH_FOR_SCAN = "lab6.classes";

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


  public static void main(String[] args) {
    /* Просканируем пакет PATH_FOR_SCAN для поиска классов (включая вложенные пакеты)  */
    System.out.println("STEP 1: scan package " + PATH_FOR_SCAN + ":");

    List<Class<?>> classList = PathScan.find(PATH_FOR_SCAN);
    if (classList != null) {
      classList.forEach(c -> System.out.println("\t" + c.getCanonicalName()));

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
      System.out.println(nodeList.get(i));
    }

    System.out.println("STEP 2: scan class fields:");
    for (Class<?> cl : classList) {
      /* Сканируем поля классов */
      System.out.println("\tFields of class " + cl.getName());
      Field[] fields = cl.getDeclaredFields();
      for (Field field : fields) {
        System.out.println("\t\t" + field.getName());
        System.out.println("\t\t" + field.getType());
      }
    }

    System.out.println("STEP 3: scan class methods:");
    for (Class<?> cl : classList) {
      /* Сканируем методы классов */
      System.out.println("\tMethods of class " + cl.getName());
      Method[] methods = cl.getMethods();
      for (Method method : methods) {
        System.out.println("\t\t" + method.getName());
      }
    }

    System.out.println("STEP 4: scan class annotations: ");
    for (Class<?> c : classList) {
      System.out.println("\tAnnotations of class " + c.getName());
      Annotation[] annotations = c.getAnnotations();
      if (annotations != null) {
        for (Annotation a : annotations) {
          if (a.annotationType().equals(Entity.class)) {
            System.out.println("\t\t" + c.getSimpleName() + " is entity!");
          }
        }
      }
    }

    System.out.println("STEP 5: scan fields annotations:");
    for (Class<?> c : classList) {
      Field[] fields;
      System.out.println("\tField annotations of class " + c.getName());
      if (c.getCanonicalName().equals("lab6.classes.Author")) {
        Field[] personFields = Author.class.getSuperclass().getDeclaredFields();
        Field[] authorFields = Author.class.getDeclaredFields();
        fields = new Field[authorFields.length + personFields.length];
        Arrays.setAll(fields, i ->
                (i < personFields.length ? personFields[i] : authorFields[i - personFields.length]));
      } else fields = c.getDeclaredFields();

      for (Field f : fields) {
        Annotation[] annotations = f.getAnnotations();
        for (Annotation a : annotations) {
          if (a.annotationType().equals(Column.class)) {
            System.out.println(String.format("\t\tField %s %s is attribute!", f.getType().getSimpleName(), f.getName()));
          }
          if (a.annotationType().equals(Enumerated.class)) {
            System.out.println(String.format("\t\tField %s %s is enum attribute!", f.getType().getSimpleName(), f.getName()));
          }

          if (a.annotationType().equals(OneToOne.class)) {
            String relationClass = f.getGenericType().getTypeName();
            System.out.println(String.format("\t\tClass %s in relation OneToOne with class %s", c.getSimpleName(), relationClass));
            Node node1 = find(c);
            Node node2 = find(relationClass);
            Edge edge = new Edge(node1, node2, RelationType.OneToOne);
            System.out.println(edge);

            edgeList.add(edge);
          }

          if (a.annotationType().equals(OneToMany.class)) {
            String relationClass = f.getGenericType().getTypeName();
            System.out.println(String.format("\t\tClass %s in relation OneToMany with class %s", c.getSimpleName(), relationClass.substring(relationClass.indexOf("<") + 1, relationClass.indexOf(">"))));
            Node node1 = find(c);
            Node node2 = find(relationClass.substring(relationClass.indexOf("<") + 1, relationClass.indexOf(">")));
            Edge edge = new Edge(node1, node2, RelationType.OneToMany);
            System.out.println(edge);

            edgeList.add(edge);
          }


          if (a.annotationType().equals(ManyToOne.class)) {
            String relationClass = f.getGenericType().getTypeName();
            System.out.println(String.format("\t\tClass %s in relation ManyToOne with class %s", c.getSimpleName(), relationClass));
            Node node1 = find(c);
            Node node2 = find(relationClass);
            Edge edge = new Edge(node1, node2, RelationType.ManyToOne);
            System.out.println(edge);
            edgeList.add(edge);
          }

        }
      }
    }


    System.out.println("STEP 6: get superclass:");

    Class superClass = Author.class.getSuperclass();
    System.out.println("\tSuper class of Client is " + superClass.getName());


    for (Node aNodeList : nodeList) {
      System.out.println(aNodeList);

    }

    for (Edge edge : edgeList) {
      System.out.println();
      System.out.println("source " + edge.source.className.getSimpleName());
      System.out.println("target " + edge.target.className.getSimpleName());
      System.out.println("relation " + edge.relationType);

    }



    //Visual visual = new Visual(nodeList,edgeList);

  }


}





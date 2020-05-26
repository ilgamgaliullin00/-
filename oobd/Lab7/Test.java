import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        ScanDB scanDB = new ScanDB();
        ScanPackages scanPackages = new ScanPackages();

        //таблицы и поля из БД
        LinkedHashMap<String, LinkedHashSet<String>> tablesDB = scanDB.getTablesInfo();
//    System.out.println(tablesDB);

//    классы  пакета и ее поля с типом и отношением
        LinkedHashMap<String, LinkedHashMap<String, String>> tablesPackage = ScanClasses.getInfoAboutClasses();
//    System.out.println(tablesPackage);

        List<String> tablesPackageNames = new ArrayList<>(tablesPackage.keySet());

        LinkedHashMap<String, List<String>> tableAndFieldColumns = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> tableAndFieldId = new LinkedHashMap<>();
        LinkedHashMap<String, List<String>> tableAndFieldRelations = new LinkedHashMap<>();

        for (int i = 0; i < tablesPackageNames.size(); i++) {
            //получаем поля первого класса
            LinkedHashMap<String, String> fields = tablesPackage.get(tablesPackageNames.get(i));

            List<String> fieldsType = new ArrayList<>(fields.values());
            List<String> fieldsName = new ArrayList<>(fields.keySet());

            List<String> columns = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            List<String> relations = new ArrayList<>();


            for (int j = 0; j < fields.size(); j++) {
                if (fieldsType.get(j).equalsIgnoreCase("Column"))
                    columns.add(fieldsName.get(j));
                if (fieldsType.get(j).equalsIgnoreCase("Id"))
                    ids.add(fieldsName.get(j));
                if (fieldsType.get(j).contains("OneToMany"))
                    relations.add(fieldsName.get(j) + "=" + fieldsType.get(j));
                if (fieldsType.get(j).contains("ManyToMany"))
                    relations.add(fieldsName.get(j) + "=" + fieldsType.get(j));
                if (fieldsType.get(j).contains("ManyToOne"))
                    relations.add(fieldsName.get(j) + "=" + fieldsType.get(j));
                if (fieldsType.get(j).contains("OneToOne"))
                    relations.add(fieldsName.get(j) + "=" + fieldsType.get(j));

            }

            tableAndFieldColumns.put(tablesPackageNames.get(i), columns);
            tableAndFieldId.put(tablesPackageNames.get(i), ids);
            tableAndFieldRelations.put(tablesPackageNames.get(i), relations);


        }


        List<String> tablesDBNames = new ArrayList<>(tablesDB.keySet());


        for (int i = 0; i < tablesPackage.size(); i++) {

            for (int j = 0; j < tablesDB.size(); j++) {
                if (tablesPackageNames.get(i).equalsIgnoreCase(tablesDBNames.get(j))) {
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("Таблица(класс) " + tablesPackageNames.get(i));

                    //получаем поля классов
                    List<String> ids = tableAndFieldId.get(tablesPackageNames.get(i));
                    List<String> columns = tableAndFieldColumns.get(tablesPackageNames.get(i));
                    List<String> relations = tableAndFieldRelations.get(tablesPackageNames.get(i));

                    LinkedHashSet<String> tablesDBFields = tablesDB.get(tablesDBNames.get(j));
//          System.out.println(tablesDBFields);
                    for (int k = 0; k < ids.size(); k++) {
                        if (tablesDBFields.contains(ids.get(k)))
                            System.out.println("\t\tПоле " + ids.get(k) + " совпадает");
                    }

                    for (int k = 0; k < columns.size(); k++) {
                        if (tablesDBFields.contains(columns.get(k)))
                            System.out.println("\t\tПоле " + columns.get(k) + " совпадает");
                    }

                    for (int k = 0; k < relations.size(); k++) {


                        String[] relationsArray = relations.get(k).split("=");
//            System.out.println("//////////////" + Arrays.toString(relationsArray));
                        if (relationsArray[relationsArray.length - 3].equalsIgnoreCase("OneToMany")) {
//              System.out.println(relationsArray[relationsArray.length - 1] + "!!!!");
//              System.out.println(relationsArray[0] + "999");
//              System.out.println(relationsArray[1] + "88");
//              System.out.println(relationsArray[2] + "7");
                            LinkedHashMap<String, String> fieldsPackage = tablesPackage.get(tablesPackageNames.get(i));
//              System.out.println(fieldsPackage+"11111");
                            if (fieldsPackage.containsKey(relationsArray[0]) && fieldsPackage.get(relationsArray[0]).contains(relationsArray[2]) &&
                                    fieldsPackage.get(relationsArray[0]).contains(relationsArray[relationsArray.length - 1])) {
                                System.out.println("В классе " + tablesPackageNames.get(i) + " найдено поле " + relationsArray[2] + "<" + relationsArray[relationsArray.length - 1] + ">");

                            } else {
                                System.out.println("Принцип ORM нарушен, так как поле  "+relationsArray[2] + "<" + relationsArray[relationsArray.length - 1] + "> не найдено");
                            }
//

                        } else if (relationsArray[relationsArray.length - 3].equalsIgnoreCase("ManyToMany")) {
//
                            String tableName1 = relationsArray[relationsArray.length - 1].toLowerCase() + "_" + tablesDBNames.get(j);
//              System.out.println("tableName1" + tableName1);
                            String tableName2 = tablesDBNames.get(j) + "_" + relationsArray[relationsArray.length - 1].toLowerCase();
//              System.out.println("tableName2 " + tableName2);

                            String tableName = null;

                            if (tablesDBNames.contains(tableName1)) tableName = tableName1;
                            else if (tablesDBNames.contains(tableName2)) tableName = tableName2;
                            else System.out.println("Доп таблицы не найдено");

                            if (tableName != null && !tableName.isEmpty()) {
                                if (tablesDB.get(tableName)
                                        .contains(relationsArray[relationsArray.length - 1].toLowerCase() + "_id")) {
                                    System.out.println("В таблице " + tableName + " найдено поле " + relationsArray[relationsArray.length - 1].toLowerCase() + "_id");

                                } else {
                                    System.out.println("Принцип ORM нарушен, так как поле  "+relationsArray[relationsArray.length - 1].toLowerCase() + "_id");
                                }
                            }
//
//
//              System.out.println("Я тууууууууут");
                        } else if (relationsArray[relationsArray.length - 3].equalsIgnoreCase("ManyToOne")) {
                            if (tablesDB.get(tablesDBNames.get(j)).contains(relationsArray[0].toLowerCase() + "_id")) {
                                System.out.println("В таблице " + tablesDBNames.get(j) + " найдено поле " + relationsArray[0] + "_id");
                            }
                            else {
                                System.out.println("Принцип ORM нарушен, так как поле  "+relationsArray[0] + "_id");
                            }
//
                        } else if (relationsArray[relationsArray.length - 3].equalsIgnoreCase("OneToOne")) {
                            if (tablesDB.get(tablesDBNames.get(j)).contains(relationsArray[0] + "_id")) {
                                System.out.println("В таблице " + tablesDBNames.get(j) + " найдено поле " + relationsArray[0] + "_id");
                            }
                            else {
                                System.out.println("Принцип ORM нарушен, так как поле  "+relationsArray[0] + "_id");
                            }

                        }
                    }


                }
            }


        }
    }
}


//  public static void main(String[] args) {
//    ScanDB scanDB = new ScanDB();
//    ScanPackages scanPackages = new ScanPackages();
//
//    List<String> entities = scanPackages.getEntities();
//
//    //клаасы  пакета и ее поля с типом и отношением
//    LinkedHashMap<String, LinkedHashMap<Field, String>> tablesFromPackage = ScanClasses.getInfoABoutClasses();
//    System.out.println(tablesFromPackage);
//
//    //таблицы и поля из БД
//    LinkedHashMap<String, LinkedHashSet<String>> tablesFromDB = scanDB.getTablesInfo();
//    System.out.println(tablesFromDB);
//
//
//    LinkedHashMap<Field, String> fieldsAndRelation = null;
//
//    LinkedHashMap<String, LinkedHashMap<String, String>> tablesFromPackageAndFieldsAndType = new LinkedHashMap();
//    for (int i = 0; i < entities.size(); i++) {
////хранение имени поля и его типа если collection то класс generic
//      LinkedHashMap<String, String> fieldsAndTypeListOrCollection = new LinkedHashMap<>();
//
//      if (tablesFromPackage.containsKey(entities.get(i))) {
//        fieldsAndRelation = tablesFromPackage.get(entities.get(i));
//        System.out.println("\nКласс " + entities.get(i) + " и его поля:");
//        System.out.println(fieldsAndRelation);
//
//
//        Set<Field> keySet = fieldsAndRelation.keySet();
//        LinkedHashSet<Field> keyLinkedSet = new LinkedHashSet<Field>(keySet);
//        ArrayList<Field> keyArray = new ArrayList<Field>(keyLinkedSet);
//        System.out.println(keyLinkedSet + "linked");
//
//        Collection valuesSet = fieldsAndRelation.values();
//        ArrayList valuesList = new ArrayList(valuesSet);
//        System.out.println(valuesList + "listSet");
//
//        //редактирование типа поля
//        //выдаем <имя поля, тип поля и та таблица в которой искать c _id>
//        for (int j = 0; j < keyArray.size(); j++) {
//          String fieldName = keyArray.get(j).getName();
//          System.out.println("Имя " + fieldName);
//
//          String fieldType = keyArray.get(j).getType().toString();
////          System.out.println("Тип "+fieldType);
//
//          if (fieldType.contains("List")) {
//            fieldType = keyArray.get(j).getGenericType().toString();
//            String[] fieldTypeSplit1 = fieldType.split("<");
//            String[] fieldTypeSplit2 = fieldTypeSplit1[fieldTypeSplit1.length - 1].split("\\.");
//            fieldType = fieldTypeSplit2[fieldTypeSplit2.length - 1].substring(0, fieldTypeSplit2[fieldTypeSplit2.length - 1].length() - 1);
//
//
//            System.out.println("generic = " + fieldType);
//          } else if (fieldType.contains("Collection")) {
//            fieldType = keyArray.get(j).getGenericType().toString();
//            String[] fieldTypeSplit1 = fieldType.split("<");
//            String[] fieldTypeSplit2 = fieldTypeSplit1[fieldTypeSplit1.length - 1].split("\\.");
//            fieldType = fieldTypeSplit2[fieldTypeSplit2.length - 1].substring(0, fieldTypeSplit2[fieldTypeSplit2.length - 1].length() - 1);
//            System.out.println("generic Collection = " + fieldType);
//
//          } else {
//            String[] fieldTypeSplit = fieldType.split("\\.");
//            fieldType = fieldTypeSplit[fieldTypeSplit.length - 1];
//            System.out.println("Тип " + fieldType);
//          }
//
//          fieldsAndTypeListOrCollection.put(fieldName.toLowerCase(), fieldType);
//
//        }
//
//        tablesFromPackageAndFieldsAndType.put(entities.get(i).toLowerCase(), fieldsAndTypeListOrCollection);
//
//
//      }
//
//
//    }
//
//
////получаем спсиок имен таблиц из пакета и бд
//    List<String> tablesFromPackageNames = new ArrayList<>(tablesFromPackageAndFieldsAndType.keySet());
//    System.out.println("tablesFromPackageNames = " + tablesFromPackageNames.toString());
//    List<String> tablesFromDBNames = new ArrayList<>(tablesFromDB.keySet());
//
//
//    for (int i = 0; i < tablesFromPackageNames.size(); i++) {
//      LinkedHashMap<String, String> fieldsFromPackage = tablesFromPackageAndFieldsAndType.get(tablesFromPackageNames.get(i));
//      LinkedHashMap<String, String> fieldsFromPackageCopy = (LinkedHashMap<String, String>) fieldsFromPackage.clone();
//
//
//      for (int j = 0; j < tablesFromDBNames.size(); j++) {
//
//        //если название класса и таблицы сошлись
//        if (tablesFromPackageNames.get(i).equalsIgnoreCase(tablesFromDBNames.get(j))) {
//          System.out.println("----------------------------------------------------------------------");
//          System.out.println("Таблица " + tablesFromPackageNames.get(i));
//
//          //получаем поля таблицы и делаем ее копию
//          LinkedHashSet<String> fieldsFromDBCopy = (LinkedHashSet<String>) tablesFromDB.get(tablesFromDBNames.get(j)).clone();
//
//          List<String> fieldsFromDBNames = new ArrayList<>(tablesFromDB.get(tablesFromDBNames.get(j)));
//          LinkedHashSet<String> fieldsFromPackageNames = new LinkedHashSet<>(fieldsFromPackage.keySet());
//
//          System.out.println("\tВывод совпадающих полей");
//          for (int k = 0; k < fieldsFromDBNames.size(); k++) {
//            if (fieldsFromPackageNames.contains(fieldsFromDBNames.get(k))) {
//              System.out.println("\t\tПоле " + fieldsFromDBNames.get(k) + " совпадает");
//              fieldsFromDBCopy.remove(fieldsFromDBNames.get(k));
//              fieldsFromPackageCopy.remove(fieldsFromDBNames.get(k));
//            }
//
//
//          }
//          System.out.println("\nПросмотр несвопадающих полей из БД");
//          for (int l = 0; l < fieldsFromDBCopy.size(); l++) {
//            System.out.println(fieldsFromDBCopy);
//          }
//          System.out.println("\nПросмотр несвопадающих полей из Пакета");
//          for (int l = 0; l < fieldsFromPackageCopy.size(); l++) {
//            System.out.println(fieldsFromPackageCopy);
//          }
//
//          //если не пусто
//          if (!fieldsFromPackageCopy.isEmpty()) {
////
//            List<String> fieldName = new ArrayList<>(fieldsFromPackage.keySet());
//            List<String> fieldRelationClassType = new ArrayList<>(fieldsFromPackage.values());
//
//            //если тип правой части есть такое в таблице проверка
//            for (int k = 0; k < fieldsFromPackage.size(); k++) {
//              //спиок имен таблиц
//              List<String> tablesFromDbNames = new ArrayList<>(tablesFromDB.keySet());
//              LinkedHashSet fieldsFromDbNames = new LinkedHashSet<>(tablesFromDB.values());
//
//              for (int l = 0; l < tablesFromPackageNames.size(); l++) {
//                //проверка что в бд есть такая таблица с тем, что List или Coll в другой
//                if (fieldRelationClassType.get(k).toLowerCase().contains(tablesFromDbNames.get(l).toLowerCase())) {
//                  System.out.println("438u92i0234444444");
//                  System.out.println("пол1 = " + tablesFromDbNames.get(l).toLowerCase());
//                  System.out.println(fieldsFromDbNames.size());
//                  List<String> fieldsFromDBList = new ArrayList<>(fieldsFromDbNames);
//                  System.out.println(fieldsFromDBList);
////                  System.out.println(tablesFromDbNames.get(j).toLowerCase());
//                  System.out.println("id = " + tablesFromDbNames.get(j).toLowerCase() + "_id");
//
//
//                  System.out.println(fieldsFromDbNames.contains(tablesFromDbNames.get(j).toLowerCase() + "_id"));
//                  if (fieldsFromDbNames.contains(tablesFromDbNames.get(j).toLowerCase() + "_id")) {
//                    System.out.println("В таблице из Базы данных " + tablesFromDbNames.get(l) + " найдено поле " + tablesFromDbNames.get(j).toLowerCase() + "_id");
//                    System.out.println("Принцип ORM не нарушен");
//                  } else {
//                    System.out.println("Поле " + tablesFromDbNames.get(j).toLowerCase() + "_id не найдено");
//                    System.out.println("Приницп ORM нарушен");
//                  }
////                    System.out.println("\t\tПоле "+fieldsFromDBNames.get(k)+" совпадает");
//                }
//
//
//              }
//
//            }
//          }
//
//
//        }
//      }
//
//    }
//
//
//  }
//}
////
////
//////    -------------------------------------------------------------------
////    //проерка таблиц из БД и сущностей
//////    List<String> tablesFromDB = scanDB.getTables();
////    HashMap<String, LinkedHashSet<String>> tablesFromDB = scanDB.getTablesInfo();
////
////    List<String> entities = scanPackages.getEntities();
////
////    LinkedHashMap<String, LinkedHashMap<Field, String>> tablesFromPackage = ScanClasses.getInfoABoutClasses();
////
////
////    System.out.println(tablesFromPackage);
////
//////для хранения оставшихся полей имя класса и список
////    HashMap<String, List<String>> leftFieldsPackage = new  HashMap<String, List<String>>();
////    HashMap<String, List<String>> leftFieldsDB = new  HashMap<String, List<String>>();
////
//////        сам спсиоr табоиц и ост полей
////    List<HashMap<String, List<String>>> leftFieldsDBList = new ArrayList<>();
////    List<HashMap<String, List<String>>> leftFieldsPackageList = new ArrayList<>();
////
////
////
////    System.out.println(" \n-------------------------------------------------------------------");
//////    List<String> fieldsFromDBList;
////
////    //идем по списку полученных классов
////    for (int i = 0; i < entities.size(); i++) {
////      //выводим очередной класс
////      System.out.println("Выводим класс: " + entities.get(i));
//////выводим поля класса
////      ArrayList<String> fieldsFromPackage = scanPackages.getFields(entities.get(i));
////      System.out.println("full " + fieldsFromPackage);
////
////      System.out.println();
////
//////      System.out.println(tablesFromDB.containsKey(entities.get(i).toLowerCase()));
////
////
////      //получаем только названия таблиц
////      List<String> tableDBNames = new ArrayList<String>(tablesFromDB.keySet());
////
////
////
//////если есть такая таблица в бд, то выводим
////      if (tableDBNames.contains(entities.get(i).toLowerCase())) {
////        System.out.println("\n Выводим поля таблицы: " + entities.get(i).toLowerCase());
////        //индекс элемента
////        // int index =  tableDBNames.indexOf(entities.get(i).toLowerCase());
////
////        //получаем поля таблицы
////        List<String> fieldsFromDB = new ArrayList<>(tablesFromDB.get(entities.get(i).toLowerCase()));
////        System.out.println("full " + fieldsFromDB);
////        //выводим  поля таблицы
////        for (int j = 0; j < fieldsFromDB.size(); j++) {
////          System.out.println("\t\t" + fieldsFromDB.get(j));
////        }
////        System.out.println();
////
////        //сортируем спсики полей
////        Collections.sort(fieldsFromDB);
////        Collections.sort(fieldsFromPackage);
////
////        System.out.println(fieldsFromDB);
////        System.out.println(fieldsFromPackage);
////
////        List<String> fieldsFromDBCopy = new ArrayList<String>(fieldsFromDB);
////        List<String> fieldsFromPackageCopy = new ArrayList<String>(fieldsFromPackage);
////
////        //смотрим какие поля совпадают
////        System.out.println(" \n-------------------------------------------------------------------");
////        System.out.println("Просматриваем поля:");
////
////
////
////
////        for (int k = 0; k < fieldsFromPackage.size(); k++) {
////
////          for (String field : fieldsFromDB) {
////            //если поля совпадают то выводим
////            if (fieldsFromPackage.get(k).toLowerCase().equals(field)) {
////              System.out.println("Поле " + field + " совпадает");
////              fieldsFromDBCopy.remove(field);
////              fieldsFromPackageCopy.remove(field);
////            }
////          }
////          leftFieldsPackage.put(entities.get(i).toLowerCase(),fieldsFromPackageCopy);
////          leftFieldsDB.put(entities.get(i).toLowerCase(),fieldsFromDBCopy);
////
////          leftFieldsDBList.add(leftFieldsDB);
////          leftFieldsPackageList.add(leftFieldsPackage);
////        }
////
////
////
////      }
////
////
////
////    }//выводим поля которые, не совпали
////    System.out.println("Поля, которые не совпали:"+
////            "\n"+leftFieldsDBList +
////            "\n"+leftFieldsPackageList);
////
////
//////        System.out.println("Выводим соответсвующую таблицу из БД: ");
//////        System.out.println("Таблица " + entities.get(о).toLowerCase());
////
////    //начинаем проводить преврки полей класса и таблицы
////
////
////  }
////}
////
//////        System.out.println(entities.get(i).toLowerCase());
//////
//////        HashSet<String> fieldsFromDB = tablesFromDB.get(entities.get(i).toLowerCase());
//////        List<String> fieldsFromDBList = new ArrayList<>(fieldsFromDB);
//////        List<String> fieldsFromDBListCopy = new ArrayList<>(fieldsFromDB);
//////
//////        System.out.println(fieldsFromDB);
//////        LinkedHashMap<Field, String> fieldsFromPackage = tablesFromPackage.get(entities.get(i));
//////        System.out.println("fieldsFromPackage = "+fieldsFromPackage);
//////
//////        List<String> fieldsFromPackageValues = new ArrayList<>(fieldsFromPackage.values());
//////        System.out.println("fieldsFromPackageValues = "+fieldsFromPackageValues);
//////
//////        List<Field> fieldsFromPackageKeys = new ArrayList<>(fieldsFromPackage.keySet());
//////        HashMap<String, String> fieldsFromPackageKeysNamesAndClass = new HashMap<String,String>();
//////
//////        for(int k=0;k<fieldsFromPackageKeys.size();k++){
//////          fieldsFromPackageKeysNamesAndClass.put(fieldsFromPackageKeys.get(k).getName(),fieldsFromPackageKeys.get(k).getGenericType().getTypeName());
//////        }
//////
//////        System.out.println("\nfieldsFromPackageKeysNamesAndClass)"+fieldsFromPackageKeysNamesAndClass);
////
////
//////        System.out.println("fieldsFromPackageKeys = "+fieldsFromPackageKeys);
//////
//////        System.out.println("@@@"+fieldsFromPackage);
//////
////        for (int j = 0; j < fieldsFromDBList.size(); j++) {
////
////          if (fieldsFromPackageKeysN.contains(fieldsFromDBList.get(j))) {
////            System.out.println("\nПоле: " + fieldsFromDBList.get(j) + " совпадает");
////            fieldsFromDBListCopy.remove(fieldsFromDBList.get(j));
//////           fieldsFromPackageValues.remove(fieldsFromDBList.get(j));
////            fieldsFromPackageKeys.remove(fieldsFromDBList.get(j));
////
////          }
//////          System.out.println(fieldsFromPackageKeys + "!!");
////
////          if (fieldsFromDBListCopy.size() != 0) {
////
////            for (int k = 0; k < fieldsFromPackageKeys.size(); k++) {
////              String relation = fieldsFromPackage.get(fieldsFromPackageKeys.get(k));
////                if (relation.equals("OneToMany")) {
////
////
////              }
////            }
////          }
////
////        }
//
////        LinkedHashMap<String,String> fieldsFromDB = tablesFromPackage.get(entities.get(i).toLowerCase());
////        LinkedHashMap<String,String> fieldsFromDBCopy = new LinkedHashMap<>(fieldsFromDB);
//
////        List<String> fieldsFromDBListCopy = new ArrayList<String>(fieldsFromDBList);
//
////идем по полям
//
//
////          Iterator<String> it = fieldsFromDB.iterator();
//
////          for (int j = 0; j < fields.size(); j++) {
////            System.out.println("111");
////            System.out.println(fields.get(j));
////
////
////          for (int k = 0; k < fieldsFromDB.size(); k++) {
////
//////            while (it.hasNext()) {
////              System.out.println("222");
//////              String field = it.next();
////
////              if (fields.get(j).equals(fieldsFromDB.)) {
////                System.out.println("Поле: " + fields.get(j) + " совпадает");
////                fieldsFromDBListCopy.remove(field);
////              }
////            }
////            if (fieldsFromDBListCopy.size() != 0) {
////              System.out.println("\n\t\tНесовпадающие поля:");
////              for (int k = 0; k < fieldsFromDBListCopy.size(); k++) {
////                System.out.println(fieldsFromDBListCopy.get(k));
////              }
////            } else {
////
////              System.out.println("В БД нет соответсвующей таблицы");
////              break;
////            }
////
////
////          }
//
//
////    List<String> tablesFromDBCopy = null;
////    List<String> allTablesCopy = null;
////    boolean isEqual = tablesFromDB.equals(entities);
////    if (isEqual) {
////      System.out.println("Таблицы и сущности совпадают");
////
////    } else {
////      System.out.println("Таблицы и сущности не совпадают\n");
////      if (tablesFromDB.size() > entities.size()) {
////        tablesFromDBCopy = new ArrayList<>(tablesFromDB);
////        tablesFromDBCopy.removeAll(entities);
////        System.out.println("Дополнитльные таблицы из БД: " + tablesFromDBCopy + "\n");
////
//////        allTablesCopy = new ArrayList<>(allTables);
//////        allTablesCopy.removeAll(entities);
//////        System.out.println("Дополнитльные таблицы из пакета: " + allTablesCopy+"\n");
////
////      }
////    }
////
//////    -------------------------------------------------------------------
////    System.out.println(" \n-------------------------------------------------------------------");
////
////    //проверка полей таблиц
////    HashMap<String, List<String>> tablesWithFields = new HashMap<>();
////    List<String> fieldsFromDb;
////    List<String> fields;
////    List<String> fieldsFromDbLast;
////
////
////    for (int i = 0; i < entities.size(); i++) {
////      System.out.println();
////      System.out.println("Поля таблицы - " + entities.get(i));
////      fields = scanPackages.getFields(entities.get(i).toLowerCase());
////      System.out.println("Поля таблицы из БД - " + entities.get(i).toLowerCase());
////      fieldsFromDb = scanDB.getFields(entities.get(i).toLowerCase());
////      fieldsFromDb.forEach(System.out::println);
////    }
////
////    System.out.println(" \n-------------------------------------------------------------------");
////    System.out.println("\n\nТаблицы из БД без соответствия");
////    for (int i = 0; i < tablesFromDBCopy.size(); i++) {
////      System.out.println();
////      System.out.println("Поля таблицы из БД - " + tablesFromDBCopy.get(i).toLowerCase());
////      fieldsFromDbLast = scanDB.getFields(tablesFromDBCopy.get(i).toLowerCase());
////      fieldsFromDbLast.forEach(System.out::println);
////    }
////
////
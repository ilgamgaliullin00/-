package oodb.lab5;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.saveToDB();
        database.update();
        database.searchInDB();
    }
}
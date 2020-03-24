package DB;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Load4mFile {
    public static Apteka load() throws IOException {
        Apteka apteka1 = null;
        String aptekaStr = "";
        File file = new File("bank.json");

        if (file.exists()) {
            aptekaStr = new String(Files.readAllBytes(file.toPath()));
        }

        apteka1 = new Gson().fromJson(aptekaStr, Apteka.class);

        return apteka1;
    }

    /**
     * Пример чтения из файла массива JSON объектов
     */
    public static List<Pharmacist> loadPersonList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("pharmacists.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("Файл pharmacists.json не найден!");
        }

        Gson gson = new Gson();

        Pharmacist[] plst = gson.fromJson(pStr, Pharmacist[].class);

        return Arrays.asList(plst);
    }
}
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class LoadDB {
    public static Apteka load() throws IOException {
        Apteka pharmacy = null;
        String str = "";
        File file = new File("persons.json");

        if (file.exists()) {
            str = new String(Files.readAllBytes(file.toPath()));
        }

        pharmacy = new Gson().fromJson(str, Apteka.class);

        return pharmacy;
    }


    public static List<Pharmacist> loadPersonList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("persons.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("File doesnt exist");
        }

        Gson gson = new Gson();

        System.out.println(pStr);

        Pharmacist[] plst = gson.fromJson(pStr, Pharmacist[].class);

        return Arrays.asList(plst);
    }
}
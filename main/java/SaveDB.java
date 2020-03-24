import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class SaveDB {

    public static void savePharmacistList(Apteka apteka) throws IOException {

        if (apteka != null ) {
            Gson gson = new Gson();

            String personsAsJson = gson.toJson(apteka);
            System.out.println(personsAsJson);

            BufferedWriter bw=new BufferedWriter(new FileWriter(new File("apteka.json")));
            bw.write(personsAsJson);
            bw.close();

        }
    }
}
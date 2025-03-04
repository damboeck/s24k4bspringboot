package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ReadCsv {

    public static final String filename="data/Adressen.csv";
    public static final String out="data/Adressen2.csv";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        Vector<Adresse> adressen = new Vector<Adresse>();
        for (int i=1;i<lines.size();i++) {
            //System.out.println(lines.get(i));
            Adresse adresse=new Adresse(lines.get(i));
            adressen.add(adresse);
        }
        adressen.add(new Adresse("Walter","Führer",
                "Waldstraße","3",3100,"St.Pölten",
                "",new Date()));
        adressen.get(1).setHausnummer("13");
        for (Adresse adresse : adressen) {
            System.out.println(adresse);
        }
        Adresse.saveAdressen(adressen, out);
    }

}

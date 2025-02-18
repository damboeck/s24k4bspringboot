package at.ac.htlstp.et.s24k4b.sb1.dateien;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ReadCsv {

    public static final String filename = "data/Adressen.csv";
    public static final String newfilename = "data/Adressen1.csv";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines((new File(filename)).toPath());
        Vector<Adresse> adressen = new Vector<Adresse>();
        for (int i=1; i<lines.size(); i++) {
            Adresse adresse = new Adresse(lines.get(i));
            adressen.add(adresse);
        }
        adressen.add(new Adresse("Peter","Kraus","Hofgasse","5",1030,"Wien","0353453",null));
        adressen.get(1).setHausnummer("15");
        // Ausgabe
        for (Adresse adresse : adressen) {
            System.out.println(adresse);
        }
        // Speichern in eine CSV-Datei
        Files.write((new File(newfilename)).toPath(),Adresse.toCsv(adressen));

        /*Adresse adresse = new Adresse();
        System.out.println(adresse);
        adresse=new Adresse("Peter","Kraus","Hofgasse","5",1030,"Wien","0353453",new Date());
        System.out.println(adresse);*/
    }
}

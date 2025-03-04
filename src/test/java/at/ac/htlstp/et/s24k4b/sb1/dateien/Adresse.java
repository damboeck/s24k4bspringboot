package at.ac.htlstp.et.s24k4b.sb1.dateien;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Adressinformation für das Einlesen der gegebenen CSV-Datei
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Adresse {

    private String vorname;
    private String name;
    private String strasse;
    private String hausnummer;
    private int    plz;
    private String ort;
    private String telefon;
    private Date   geburtsdatum;

    public Adresse(String csvline) {
        String[] columns = csvline.split(";");
        try {
            vorname       = columns[0].trim();
            name          = columns[1].trim();
            strasse       = columns[2].trim();
            hausnummer    = columns[3].trim();
            try {
                plz = Integer.parseInt(columns[4].trim());
            } catch (NumberFormatException e) {}
            ort           = columns[5].trim();
            telefon       = columns[6].trim();
            String[] cells = columns[7].trim().split("\\.");
            int day   = Integer.parseInt(cells[0]);
            int month = Integer.parseInt(cells[1])-1;
            int year  = Integer.parseInt(cells[2]);
            if (year<1900) year += 100; else year -= 1900;
            geburtsdatum  = new Date(year, month, day);
        } catch (Exception e){}
    }

    public String csvLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(vorname).append(";")
            .append(name).append(";")
            .append(strasse).append(";")
            .append(hausnummer).append(";")
            .append(ort).append(";")
            .append(telefon).append(";");
        if (geburtsdatum != null) {
            sb.append(geburtsdatum.getDate() + "." + (geburtsdatum.getMonth() + 1) + "." + (geburtsdatum.getYear() + 1900));
        }
        return sb.toString();
    }

    public String csvHeader() {
        return "Vorname;Name;Straße;Hausnummer;PLZ;Ort;Telefon;Geb.Datum";
    }

    /**
     * Setzt die Telefonnummer eines Eintrages
     * @param adressen Liste in der gesucht wird
     * @param vorname  Vorname
     * @param name     Name
     * @param telefon  Telefonnummer neu
     * @return         Anzahl der geänderten Einträge
     */
    public static int setTelefon(Vector<Adresse> adressen, String vorname, String name, String telefon){
        //TODO Hausübung
        return 0;
    }

    public static List<String> toCsv(Vector<Adresse> adressen) {
        List<String> result = new ArrayList<>();
        for (Adresse adresse : adressen) {
            result.add(adresse.csvLine());
        }
        return result;
    }

}

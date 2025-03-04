package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Adresse {

    private String vorname   = "";
    private String nachname  = "";
    private String strasse   = "";
    private String hausnummer= "";
    private int    plz       = 0;
    private String ort       = "";
    private String telefon   = "";
    private Date   geburt;

    public Adresse(String line) {
        String[] columns = line.split(";");
        try {
            this.vorname = columns[0].trim();
            this.nachname = columns[1].trim();
            this.strasse = columns[2].trim();
            this.hausnummer = columns[3].trim();
            try {
                this.plz = Integer.parseInt(columns[4].trim());
                this.ort = columns[5].trim();
            } catch (NumberFormatException e) {
                if (columns[4].trim().length()>0) {
                    this.ort = columns[4].trim()+" "+columns[5].trim();
                } else {
                    this.ort = columns[5].trim();
                }
            }
            this.telefon = columns[6].trim();
            String[] dc = columns[7].trim().split("\\.");
            int day = Integer.parseInt(dc[0]);
            int month = Integer.parseInt(dc[1])-1;
            int year = Integer.parseInt(dc[2])-1900;
            this.geburt = new Date(year, month, day);
        } catch (Exception e) {}
    }

    public static void saveAdressen(Vector<Adresse> adressen, String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(header());
        for (Adresse a:adressen) {
            lines.add(a.toLine());
        }
        Files.write(Paths.get(filename),lines);
    }

    public static String header() {
        return "Vorname;Name;Straße;Hausnummer;PLZ;Ort;Telefon;Geb.Datum";
    }

    public String toLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(vorname).append(";").append(nachname).append(";").append(strasse).append(";");
        sb.append(hausnummer).append(";").append(plz).append(";").append(ort).append(";");
        sb.append(telefon).append(";");
        if (geburt != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(geburt);
            sb.append(cal.get(Calendar.DAY_OF_MONTH)).append(".");
            sb.append(cal.get(Calendar.MONTH)+1).append(".");
            sb.append(cal.get(Calendar.YEAR));
        }
        return sb.toString();
    }

}

package at.ac.htlstp.et.s24k4b.sb1.dateien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibelVerweise {

    public final static String bibelfilename="data/bibel02.txt";

    public static void main(String[] args) throws IOException {
        String data = Files.readString(Paths.get(bibelfilename));
        // Entferne alle Zeilenvorschübe
        data = data.replaceAll("[\r\n]"," ");
        data = data.replaceAll("\\s+"," ");
        Pattern p = Pattern.compile("\\(([^\\(\\)]+)\\)");
        Matcher m = p.matcher(data);
        List<Querverweis> verweise = new ArrayList<>();
        while (m.find()) {
            Querverweis last = null;
            for (String s : m.group(1).split(";"))
                try {
                    //System.out.print(s);
                    Querverweis q = new Querverweis(s, last);
                    last = q;
                    //System.out.println(" -> "+q);
                    verweise.add(q);
                } catch (Exception e) {
                    System.out.println("Fehler bei Verweis: " + s);
                }
        }
        System.out.println("Es wurden "+verweise.size()+" Querverweise gefunden!");
        Collections.sort(verweise);
        for (Querverweis q : verweise) {
            System.out.println(q);
        }
    }

}

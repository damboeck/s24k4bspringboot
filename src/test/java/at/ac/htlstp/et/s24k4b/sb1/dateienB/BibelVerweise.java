package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibelVerweise {

    public static final String bibelfilename = "data/bibel02.txt";

    public static void main(String[] args) throws IOException {
        String data = Files.readString(Paths.get(bibelfilename));
        data = data.replaceAll("[\r\n]","");
        // System.out.println(data);
        Pattern p= Pattern.compile("\\(([^\\)\\(]+)\\)");
        Matcher m = p.matcher(data);
        List<Querverweis> querverweisList= new ArrayList<>();
        while (m.find()) {
            Querverweis prev = null;
            for (String rohverweis: m.group(1).split(";")) try {
                Querverweis q = new Querverweis(rohverweis,prev);
                prev = q;
                querverweisList.add(q);
            } catch (Exception e) {
                System.out.println(rohverweis+" ist keine gültiger BibelVerweis");
            }
            //System.out.println(m.group(1));
        }
        System.out.println("\ngefunden:\n");
        Collections.sort(querverweisList);
        for (Querverweis q: querverweisList) {
            System.out.println(q);
        }
    }
}

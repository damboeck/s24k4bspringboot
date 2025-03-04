package at.ac.htlstp.et.s24k4b.sb1.dateien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorteFaust {

    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(BuchstabenFaust.faustFile));
        int ctZeilenMitIst = 0;
        int ctIst = 0;
        Pattern p = Pattern.compile("(^|[\\s,\\.\\!\\?\\(\\)\"])[iI]st($|[\\s,'\\.\\!\\?\\(\\)\"])");
        for (String line : data) {
            if (line.matches("(^|.*[\\s,\\.\\!\\?\\(\\)\"])[iI]st($|[\\s,'\\.\\!\\?\\(\\)\"].*)")) {
                //System.out.println(line);
                ctZeilenMitIst++;
            }
            Matcher m = p.matcher(line);
            while (m.find()) {
                ctIst++;
            }
        }
        System.out.println("Zeilen mit ist:" + ctZeilenMitIst);
        System.out.println("Vorkommen von ist:" + ctIst);

        String s = "Das ist ist ist ist ist ein Scherz!";
        ctIst=0;
        Matcher m = p.matcher(s);
        while (m.find()) {
            ctIst++;
            /*s=s.substring(m.end(),s.length());
            m = p.matcher(s);*/
        }
        System.out.println("ist:"+ctIst);

    }
}

package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorteFaust {

    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(BuchstabenFaust.faustFileName));
        int ctUnd=0;
        String tz = "[\\s,\\.:\"'\\(\\)\\?!]";
        String muster = "(^|"+tz+")[uU]nd("+tz+"|$)";

        Pattern p= Pattern.compile(muster);
        Matcher m;
        for (String line : data) {
            //if (line.matches(muster)) ctUnd++;
            m = p.matcher(line);
            while (m.find()) {
                ctUnd++;
                m = p.matcher(line=line.substring(m.end()));
            }

        }
        System.out.println("Und wurde "+ctUnd+" mal gefunden.");

        String line="na und";
        if (line.matches(muster))
            System.out.println("found");
    }
}

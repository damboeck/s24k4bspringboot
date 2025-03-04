package at.ac.htlstp.et.s24k4b.sb1.dateien;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BuchstabenFaust {

    public static final String faustFile = "data/faust.txt";

    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(faustFile));
        int  ctA=0, ctLZ=0;
        for (String line : data) {
            if (line.trim().length()==0) ctLZ++;
            for (char c : line.toCharArray()) {
                if (c=='a' || c=='A') ctA++;
            }
        }
        System.out.printf("Datei enthalt %d Leerzeilen und %d mal das 'A'\n",ctLZ,ctA);
    }
}

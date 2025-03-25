package at.ac.htlstp.et.s24k4b.sb1.dateien;

import at.ac.htlstp.et.s24k4b.sb1.dateienB.BuchstabenFaust;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WortSucheFaust {

    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(BuchstabenFaust.faustFileName));
        String tz = "[\\s,\\.:\"'\\(\\)\\?!]";
        String muster = "(^|"+tz+")(?<word>[a-zA-ZöäüÖÄÜ]+)("+tz+"|$)";

        HashMap<String,Integer> wordMap = new HashMap<String,Integer>();
        Pattern p= Pattern.compile(muster);
        Matcher m;
        for (String line : data) {
            m = p.matcher(line);
            while (m.find()) {
                String word = m.group("word").toLowerCase();
                m = p.matcher(line=line.substring(m.end()));
                if (wordMap.containsKey(word)) wordMap.put(word, wordMap.get(word)+1);
                else wordMap.put(word, 1);
            }
        }
        String maxWord="";
        int    ctWord=0;
        int    ct=0;
        for (String word : wordMap.keySet()) if (word.length()>2) {
            ct++;
            if (wordMap.get(word) > ctWord) {
                maxWord=word;
                ctWord = wordMap.get(word);
            }
            //System.out.println(word+" : "+wordMap.get(word));
        }
        System.out.println(maxWord+" : "+ctWord);
        System.out.println(ct+" words ");

    }
}

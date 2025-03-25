package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class BuchstabenFaust {

    public static final String faustFileName = "data/faust.txt";

    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(Paths.get(faustFileName));
        int ctA=0;
        HashMap<Character,Integer> counter = new HashMap<Character,Integer>();
        for (String line : data) {
            line = line.toLowerCase();
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++)
                if (chars[i]>='a' && chars[i]<='z') {
                    if (counter.containsKey(chars[i]))
                        counter.put(chars[i], counter.get(chars[i])+1);
                    else counter.put(chars[i], 1);
                }
                //if (chars[i] == 'a' || chars[i] == 'A') ctA++;
        }
        System.out.println("Buchstaben:"+counter);
        char maxChar = 'a';
        int  maxAnz  = 0;
        for (char c : counter.keySet()) {
            if (counter.get(c) > maxAnz) {
                maxChar = c;
                maxAnz  = counter.get(c);
            }
        }
        System.out.println(maxChar+" wurde "+maxAnz+" mal gefunden!");

        //System.out.println("Es wurde "+ctA+" mal das 'A' gefunden!");
    }

}

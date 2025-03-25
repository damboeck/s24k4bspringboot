package at.ac.htlstp.et.s24k4b.sb1.dateienB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Querverweis implements Comparable<Querverweis> {

    private String  buch;
    private int     absatz;
    private String  vers;
    private boolean vergleich = false;

    public Querverweis(String rohverweis, Querverweis prev){
        Pattern p = Pattern.compile("(?<vgl>vgl. )?(?<buch>(\\d ?)?[A-Z][a-zöäü]+)\\s*(?<absatz>\\d+)\\s*,(?<vers>.*)");
        Matcher m = p.matcher(rohverweis);
        if (m.find()) {
            if (m.group("vgl") != null && m.group("vgl").length()>0) vergleich = true;
            buch = m.group("buch");
            absatz = Integer.parseInt(m.group("absatz"));
            vers = m.group("vers");
        } else {
            p = Pattern.compile("(?<vgl>vgl. )?\\s*(?<absatz>\\d+)\\s*,(?<vers>.*)");
            m = p.matcher(rohverweis);
            if (prev != null && prev.buch != null && prev.buch.length() > 0 && m.find()) {
                if (m.group("vgl") != null && m.group("vgl").length() > 0) vergleich = true;
                buch = prev.buch;
                absatz = Integer.parseInt(m.group("absatz"));
                vers = m.group("vers");
            } else {
                p = Pattern.compile("(?<vgl>vgl. )?(?<buch>(\\d ?)?[A-Z][a-zöäü]+)\\s*(?<absatz>\\d+)");
                m = p.matcher(rohverweis);
                if (m.find()) {
                    if (m.group("vgl") != null && m.group("vgl").length()>0) vergleich = true;
                    buch = m.group("buch");
                    absatz = Integer.parseInt(m.group("absatz"));
                    vers = "";
                } else {
                    p = Pattern.compile("(?<vgl>vgl. )?\\s*(?<absatz>\\d+)\\s*");
                    m = p.matcher(rohverweis);
                    if (prev != null && prev.buch != null && prev.buch.length() > 0 && m.find()) {
                        if (m.group("vgl") != null && m.group("vgl").length()>0) vergleich = true;
                        buch = prev.buch;
                        absatz = Integer.parseInt(m.group("absatz"));
                        vers = "";
                    } else throw new RuntimeException("Unkown rohverweis: " + rohverweis);
                }
            }
        }
    }

    @Override
    public String toString() {
        return (vergleich?"vgl. ":"")+buch + " " + absatz + "," + vers;
    }

    @Override
    public int compareTo(Querverweis o) {
        if (this.buch.compareTo(o.buch)!=0) return this.buch.compareTo(o.buch);
        if (Integer.compare(this.absatz,o.absatz)!=0) return Integer.compare(this.absatz,o.absatz);
        return this.vers.compareTo(o.vers);
    }
}

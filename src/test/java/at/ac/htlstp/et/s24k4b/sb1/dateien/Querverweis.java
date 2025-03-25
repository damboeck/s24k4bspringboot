package at.ac.htlstp.et.s24k4b.sb1.dateien;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Querverweis implements Comparable<Querverweis> {

    private String  buch;
    private int     absatz;
    private String  vers;
    private boolean vergleich = false;

    public Querverweis(String rohverweis, Querverweis last) {
        Pattern p = Pattern.compile("(?<vgl>(vgl. ))?(?<buch>(\\d )?[A-Z][a-zöäü]+)\\s*(?<absatz>\\d+)\\s*,(?<vers>.*)");
        Matcher m = p.matcher(rohverweis);
        if (m.find()) {
            if (m.group("vgl") != null && m.group("vgl").length()>0) vergleich = true;
            buch = m.group("buch");
            absatz = Integer.parseInt(m.group("absatz"));
            vers = m.group("vers");
        } else {
            p = Pattern.compile("(?<vgl>(vgl. ))?(?<absatz>\\d+)\\s*,(?<vers>.*)");
            m = p.matcher(rohverweis);
            if (m.find() && last!=null) {
                if (m.group("vgl") != null && m.group("vgl").length()>0) vergleich = true;
                buch = last.getBuch();
                absatz = Integer.parseInt(m.group("absatz"));
                vers = m.group("vers");
            } else throw new RuntimeException("Unkown rohverweis: " + rohverweis);
        }
    }

    @Override
    public String toString() {
        return (vergleich?"vgl. ":"")+buch + " " + absatz + "," + vers;
    }

    @Override
    public int compareTo(Querverweis o) {
        if (!this.buch.equals(o.getBuch()))
            return this.buch.compareTo(o.getBuch());
        if (this.absatz != o.getAbsatz())
            return this.absatz - o.getAbsatz();
        return this.vers.compareTo(o.vers);
    }
}

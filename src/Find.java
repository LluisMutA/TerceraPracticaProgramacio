import java.util.ArrayList;
import java.util.List;

public class Find {
    private String text;

    public Find(String text) {
        this.text = text;
    }

    public static void main(String[] args) {
        Find f = new Find("This computer$ is black$");
        System.out.println(f.match("%This computer$ is black$"));
    }

    public boolean match(String pat) {
        if (pat.isEmpty() && !this.text.isEmpty()) return false;
        Pattern p = new Pattern(pat);
        for (int i = 0; i < this.text.length(); i++) {
            if (tryMatch(p, i)) return true;
        }
        return false;
    }

    private boolean tryMatch(Pattern pat, int pos){
        for (int j = 0; j < pat.components.size(); j++) {
            if (j + pos >= this.text.length()) {
                if (pat.components.get(j).tipo == Component.tipoComponent.EOL){ // Comprovam que sigui un EOL ---> j perque no surti del limit
                    return true;
                }
                return false;
            }

            char c = this.text.charAt(j + pos);
            Component component = pat.components.get(j);

            if (component.tipo == Component.tipoComponent.BOL){
                if (pos != 0){ // Canviam el tipos si no es BOL a Char, corregir pattern
                    component.tipo = Component.tipoComponent.CHAR;
                }
                pos--; // j- pos?
            }
            if (component.tipo == Component.tipoComponent.EOL){
                if (j != this.text.length()-1){  // Si no esta al final tornam fals directe
                    return false;
                }
            }
            if (component.tipo == Component.tipoComponent.CHAR){
                if (c != component.ch) return false;
            }
            if (component.tipo == Component.tipoComponent.SET){
                boolean match = false; // Comprovam si coincideix per donar true i acabar el bucle
                for (int k = 0; k < component.set.length(); k++) {
                    char comprovaChar = component.set.charAt(k);
                    if (comprovaChar == '-' && k != 0 && k != component.set.length() - 1) {
                        char esInici = component.set.charAt(k - 1);
                        char esFinal = component.set.charAt(k + 1);
                        if (c >= esInici && c <= esFinal) {
                            match = true;
                            break;
                        }
                    } else if (c == comprovaChar) {
                        match = true;
                        break;
                    }
                }
                if (!match) return false;
            }
        }
        return true;
    }

    public String capture(String pat) {
        Pattern p = new Pattern(pat);
        for (int i = 0; i < this.text.length(); i++) {
            if (tryMatch(p, i)) {
                int end = i + pat.length() -1; // -1?
                return this.text.substring(i, end);
            }
        }
        return null;
    }
}




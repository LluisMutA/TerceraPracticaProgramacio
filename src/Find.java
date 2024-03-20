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
                if (pat.components.get(j).tipo == Component.tipoComponent.EOL){
                    return true;
                }

                return false;
            }

            char c = this.text.charAt(j + pos);
            Component component = pat.components.get(j);

            if (component.tipo == Component.tipoComponent.BOL){
               if (pos != 0){
                   component.tipo = Component.tipoComponent.CHAR;
               }
                pos--;
            }
            if (component.tipo == Component.tipoComponent.EOL){
                if (j != this.text.length()-1){
                    return false;
                }
            }
            if (component.tipo == Component.tipoComponent.CHAR){
                if (c != component.ch) return false;
            }
        }
        return true;
    }

    public String capture(String pat) {
        return null;
    }
}




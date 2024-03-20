import java.util.ArrayList;
import java.util.List;

public class Pattern {
    List<Component> components = new ArrayList<>();

    Pattern(String patString) {
        for (int i = 0; i < patString.length(); i++) {
            char c = patString.charAt(i);
            if (c == '?') {
                Component component = new Component();
                component.tipo = Component.tipoComponent.QMARK;
                components.add(component);
                continue;
            } else if (c == '%' && i == 0) {
                Component component = new Component();
                component.tipo = Component.tipoComponent.BOL;
                components.add(component);
                continue;
            } else if (c == '$' && i == patString.length()-1) {
                Component component = new Component();
                component.tipo = Component.tipoComponent.EOL;
                components.add(component);
                continue;
            } else if (c == '[') {
                int tancaCorchete = patString.indexOf(']', i);
                String charClass = patString.substring(i + 1, tancaCorchete);
                Component component = new Component();
                component.tipo = Component.tipoComponent.SET;
                component.set = charClass;
                components.add(component);
                i = tancaCorchete;
                continue;
            }else if (c == '@') {
                c = patString.charAt(i+1);
                Component component = new Component();
                component.tipo = Component.tipoComponent.CHAR;
                component.ch = c;
                components.add(component);
                i++;
                continue;
            }else {
                Component component = new Component();
                component.tipo = Component.tipoComponent.CHAR;
                component.ch = c;
                components.add(component);
            }
        }

//        System.out.println(components);
    }
}

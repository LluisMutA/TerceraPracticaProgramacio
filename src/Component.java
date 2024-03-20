public class Component {
    enum tipoComponent {
        BOL, //Beginning Of Line
        EOL, //End Of Line
        CHAR, //Caracter normal
        QMARK, //Question Mark (Cualquier caracter)
        SET, //Set (Clase de carácteres / Ej: a-z)
    }

    tipoComponent tipo;
    char ch;
    String set;

    @Override
    public String toString() {
        return "Component{" +
                "tipo=" + tipo +
                ", ch=" + ch +
                ", set='" + set + '\'' +
                "} \n";
    }
}

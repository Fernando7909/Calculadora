
public class main {
    public static void main(String[] args) {

        Logica logica = new Logica();
        Interfaz interfaz = new Interfaz();
        Calculos calculos = new Calculos(logica, interfaz);
    }
}


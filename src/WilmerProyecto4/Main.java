package WilmerProyecto4;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta1 = new Cuenta("Wilmer", 1000);
        cuenta1.ingresar(500);
        cuenta1.mostrar();
    }
}

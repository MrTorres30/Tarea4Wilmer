package WilmerProyecto4;

public class Cuenta {
    String titular;
    double cantidad;

    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        this.cantidad = cantidad;
    }

    public void ingresar(double monto) {
        cantidad += monto;
    }

    public void mostrar() {
        System.out.println("Titular: " + titular);
        System.out.println("Cantidad: " + cantidad);
    }
}


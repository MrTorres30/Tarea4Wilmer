
package WilmerProyecto4;  // Indica el paquete principal al que pertenece esta clase

//La clase principal donde se inicia la ejecución del programa
public class Main {
	// El método principal que Java ejecuta primero
    public static void main(String[] args) {
    	// Esto crea la ventana de login y la muestra en pantalla
        new vista.LoginVentana().setVisible(true);
    }
}
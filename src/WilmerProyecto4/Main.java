
package WilmerProyecto4;  // Indica el paquete principal al que pertenece esta clase

//Clase principal desde donde inicia la ejecución del programa
public class Main {
	// Método principal que Java ejecuta primero
    public static void main(String[] args) {
    	// Crea la ventana de login y la muestra en pantalla
        new vista.LoginVentana().setVisible(true);
    }
}
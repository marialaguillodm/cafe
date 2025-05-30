package proyecto.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Café.
 * Esta aplicación implementa una API REST para gestionar una cafetería,
 * permitiendo operaciones CRUD sobre cafés, clientes y órdenes.
 * Características principales:
 * - Gestión de productos (cafés)
 * - Gestión de clientes
 * - Gestión de órdenes
 * - Cálculo automático de totales
 * - Validaciones de datos
 * - Almacenamiento en memoria
 * @author Maria
 * @version 1.0
 */
@SpringBootApplication
public class CafeApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 * Configura y lanza el servidor web embebido.
	 *
	 * @param args Argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		SpringApplication.run(CafeApplication.class, args);
	}

}

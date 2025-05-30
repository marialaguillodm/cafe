package proyecto.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador que maneja las rutas de las páginas web de la aplicación.
 * Proporciona endpoints para acceder a las diferentes vistas de la aplicación.
 * Endpoints disponibles:
 * - GET /: Página principal
 * - GET /pages/cafes: Página de gestión de cafés
 * - GET /pages/customers: Página de gestión de clientes
 * - GET /pages/orders: Página de gestión de órdenes
 * @author Maria
 * @version 1.5
 */
@Controller
public class WebController {

    /**
     * Redirige a la página principal de la aplicación.
     * @return Nombre de la vista index
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Redirige a la página de gestión de cafés.
     * @return Nombre de la vista cafes
     */
    @GetMapping("/pages/cafes")
    public String cafes() {
        return "cafes";
    }

    /**
     * Redirige a la página de gestión de clientes.
     * @return Nombre de la vista customers
     */
    @GetMapping("/pages/customers")
    public String customers() {
        return "customers";
    }

    /**
     * Redirige a la página de gestión de órdenes.
     * @return Nombre de la vista orders
     */
    @GetMapping("/pages/orders")
    public String orders() {
        return "orders";
    }
} 
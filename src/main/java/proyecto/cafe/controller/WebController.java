package proyecto.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/pages/cafes")
    public String cafes() {
        return "cafes";
    }

    @GetMapping("/pages/customers")
    public String customers() {
        return "customers";
    }

    @GetMapping("/pages/orders")
    public String orders() {
        return "orders";
    }
} 
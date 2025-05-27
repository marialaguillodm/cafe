package proyecto.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.cafe.entity.Cafe;
import proyecto.cafe.entity.Customer;
import proyecto.cafe.entity.Order;
import proyecto.cafe.entity.OrderItem;
import proyecto.cafe.repository.CafeRepository;
import proyecto.cafe.repository.CustomerRepository;
import proyecto.cafe.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order crearOrder(Order order) {
        // Validar que el cliente existe
        Customer customer = customerRepository.findById(order.getCustomer().getId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Validar que todos los cafés existen y establecer el precio actual
        for (OrderItem item : order.getItems()) {
            Cafe cafe = cafeRepository.findById(item.getCafe().getId())
                .orElseThrow(() -> new IllegalArgumentException("Café no encontrado: " + item.getCafe().getId()));
            item.setCafe(cafe);
            item.setPrecio(cafe.getPrecio());
        }

        // Establecer el cliente y calcular el total
        order.setCustomer(customer);
        order.setCreationDate(LocalDateTime.now());
        order.calculateTotal();

        // Guardar la orden
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getId() == null) {
            throw new IllegalStateException("Error al guardar la orden");
        }

        return savedOrder;
    }

    @Transactional
    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Orden no encontrada");
        }
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        return orderRepository.findByCustomer(customer);
    }
}
# Sistema de Gestión de Cafetería

## Descripción
Sistema de gestión para una cafetería que permite administrar productos (cafés), clientes y órdenes. Desarrollado con Spring Boot y Thymeleaf.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Bootstrap 5.3.0
- MySQL
- Maven

## Características
- Gestión de productos (cafés)
  - Crear, actualizar, eliminar y listar cafés
  - Validación de datos
  - Paginación de resultados

- Gestión de clientes
  - Registro y actualización de clientes
  - Validación de datos
  - Historial de órdenes por cliente

- Gestión de órdenes
  - Creación de órdenes con múltiples items
  - Cálculo automático de totales
  - Validación de stock y precios
  - Historial de órdenes

## Estructura del Proyecto
```
src/main/
├── java/proyecto/cafe/
│   ├── controller/    # Controladores REST y MVC
│   │   ├── CafeController.java
│   │   ├── CustomerController.java
│   │   ├── OrderController.java
│   │   └── WebController.java
│   ├── entity/        # Entidades JPA
│   │   ├── Cafe.java
│   │   ├── Customer.java
│   │   ├── Order.java
│   │   └── OrderItem.java
│   ├── repository/    # Repositorios de datos
│   │   ├── CafeRepository.java
│   │   ├── CustomerRepository.java
│   │   └── OrderRepository.java
│   ├── service/       # Lógica de negocio
│   │   ├── CafeService.java
│   │   ├── CustomerService.java
│   │   └── OrderService.java
│   └── Application.java
└── resources/
    └── templates/     # Plantillas HTML
        ├── index.html
        ├── cafes.html
        ├── customers.html
        └── orders.html
```

## Requisitos
- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior

## Configuración
1. Clonar el repositorio
```bash
git clone https://github.com/marialaguillodm/cafe
```

2. Configurar la base de datos en `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafe_db
spring.datasource.username=root
spring.datasource.password=admin
```

3. Compilar el proyecto
```bash
mvn clean install
```

4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## Uso
1. Acceder a la aplicación en `http://localhost:8080`
2. Navegar por las diferentes secciones:
   - `/pages/cafes` - Gestión de cafés
   - `/pages/customers` - Gestión de clientes
   - `/pages/orders` - Gestión de órdenes

## Autor
Maria

## Versión
1.5

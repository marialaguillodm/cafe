# Sistema de Gestión de Café

Sistema de gestión para una cafetería desarrollado con Spring Boot. Permite administrar productos, clientes y órdenes de manera eficiente.

## Características

- Gestión de productos de café (CRUD)
- Gestión de clientes (CRUD)
- Gestión de órdenes con cálculo automático de totales
- Validación de datos
- Almacenamiento en memoria
- API RESTful

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Maven
- RESTful API

## Estructura del Proyecto

```
src/main/java/proyecto/cafe/
├── CafeApplication.java
├── controller/
│   ├── CafeController.java
│   ├── CustomerController.java
│   └── OrderController.java
├── entity/
│   ├── Cafe.java
│   ├── Customer.java
│   ├── Order.java
│   └── OrderItem.java
├── repository/
│   ├── CafeRepository.java
│   ├── CustomerRepository.java
│   └── OrderRepository.java
└── service/
    ├── CafeService.java
    ├── CustomerService.java
    └── OrderService.java
```

## Endpoints de la API

### Cafés
- `GET /cafe` - Obtener todos los cafés
- `POST /cafe` - Crear un nuevo café
- `PUT /cafe/{id}` - Actualizar un café existente
- `PATCH /cafe/{id}` - Actualizar parcialmente un café
- `DELETE /cafe/{id}` - Eliminar un café

### Clientes
- `POST /customers` - Crear un nuevo cliente
- `PUT /customers/{id}` - Actualizar un cliente existente
- `DELETE /customers/{id}` - Eliminar un cliente

### Órdenes
- `GET /orders` - Obtener todas las órdenes
- `GET /orders/{id}` - Obtener una orden por ID
- `GET /orders/customer/{customerId}` - Obtener órdenes por cliente
- `POST /orders` - Crear una nueva orden

## Modelos de Datos

### Café
```json
{
    "id": 1,
    "nombre": "Espresso",
    "descripcion": "Café concentrado",
    "precio": 2.50
}
```

### Cliente
```json
{
    "id": 1,
    "usuario": "Marina Mariscal"
}
```

### Orden
```json
{
    "id": 1,
    "customerId": 1,
    "items": [
        {
            "cafeId": 1,
            "cantidad": 2
        }
    ],
    "total": 5.00,
    "fechaCreacion": "2024-03-20T10:30:00"
}
```

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/cafe.git
```

2. Navegar al directorio del proyecto:
```bash
cd cafe
```

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Autor

- María Laguillo del Moral

## Versión

1.0.0


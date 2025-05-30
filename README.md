# Sistema de Gestión de Cafetería

## Descripción
Sistema de gestión para una cafetería que permite administrar productos (cafés), clientes y órdenes. Desarrollado con Spring Boot y Thymeleaf.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
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
src/main/java/proyecto/cafe/
├── controller/    # Controladores REST y MVC
├── entity/        # Entidades JPA
├── repository/    # Repositorios de datos
├── service/       # Lógica de negocio
└── Application.java
```

## Requisitos
- Java 17 o superior
- Maven 3.6 o superior
- MySQL 8.0 o superior

## Configuración
1. Clonar el repositorio
```bash
git clone [url-del-repositorio]
```

2. Configurar la base de datos en `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafe_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
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

## API REST
### Cafés
- `GET /api/cafes` - Listar todos los cafés
- `POST /api/cafes` - Crear un nuevo café
- `PUT /api/cafes/{id}` - Actualizar un café
- `DELETE /api/cafes/{id}` - Eliminar un café

### Clientes
- `GET /api/customers` - Listar todos los clientes
- `POST /api/customers` - Crear un nuevo cliente
- `PUT /api/customers/{id}` - Actualizar un cliente
- `DELETE /api/customers/{id}` - Eliminar un cliente

### Órdenes
- `GET /api/orders` - Listar todas las órdenes
- `POST /api/orders` - Crear una nueva orden
- `DELETE /api/orders/{id}` - Eliminar una orden
- `GET /api/orders/customer/{customerId}` - Obtener órdenes por cliente

## Contribución
1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Autor
Maria

## Versión
1.5


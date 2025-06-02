# ☕ Evaluación del proyecto de María

## 🧱 1. Estructura del proyecto y arquitectura por capas
- ✅ Separación clara en capas (Controller, Service, Repository, Entity)
- ✅ Lógica de negocio correctamente ubicada en la capa de servicio
- ✅ No se mezcla acceso a datos ni lógica de presentación
- **Comentario**: Has separado correctamente las responsabilidades por capa. Como sugerencia para un toque más profesional, podrías mover parte de la lógica de negocio a las propias entidades, permitiendo que las entidades no sean “anémicas” y que el servicio actúe como orquestador.

## 🧩 2. Spring Core – Inyección de dependencias
- ✅ Se evita el uso de `new` para crear dependencias
- ✅ Uso de inyección de dependencias (por constructor o con `@Autowired`)
- ✅ Uso adecuado de `@Component`, `@Service`, `@Repository`
- **Comentario**: Muy bien resuelto. Se hace buen uso del ecosistema Spring.

## 🗃️ 3. Persistencia con JPA
- ✅ Entidades bien definidas y anotadas (`@Entity`, `@Id`, `@Column`)
- ✅ Relaciones modeladas correctamente (`@OneToMany`, `@ManyToOne`, etc.)
- ✅ Consultas por nombre de método (`findByTipo`, etc.)
- ✅ Uso de paginación con `Pageable` y `Page` si procede
- ✅ Separación lógica entre repositorio y servicio
- **Comentario**: Buen uso de los repositorios. Se han mantenido simples, y se han usado queries por nombre, aunque hay un uso de `@Query` que podría haberse resuelto también por nombre. Paginación correctamente aplicada al menos en los pedidos.

## 🛢️ 4. Base de datos
- ✅ Configuración correcta en `application.properties`
- ✅ Conexión establecida con MySQL y persistencia de datos funcional mediante JPA/Hibernate
- **Comentario**: El proyecto se conecta correctamente con la base de datos y persiste sin problemas.

## 🌐 5. Spring Web / REST
- ✅ Endpoints REST bien definidos y nombrados
- ✅ Uso correcto de `@GetMapping`, `@PostMapping`, etc.
- ✅ Uso adecuado de `@PathVariable`, `@RequestBody`, `@RequestParam`
- **Comentario**: Se han usado adecuadamente las anotaciones de Spring MVC. Además, se han añadido validaciones a las entradas y se usan correctamente los códigos de estado HTTP, lo cual es una muy buena práctica.

## 🔐 6. Spring Security
*(No aplicable, era opcional y no se ha implementado)*

## 🧪 7. Testing
*(No aplicable, era opcional y no se ha implementado)*

## 🧼 8. Buenas prácticas y limpieza de código
- ✅ Nombres claros y expresivos
- ✅ Código sin duplicación ni clases innecesarias
- 🟠 Validaciones, manejo de errores, uso correcto de `Optional`
- **Comentario**: El código está limpio, sin métodos abandonados ni comentarios sin sentido. Como punto positivo, se han aplicado validaciones a nivel de API. Como sugerencia, intenta evitar nombres en español para las clases y el código en general: es buena práctica acostumbrarse al inglés, especialmente pensando en proyectos en equipo o internacionales. Sería recomendable como punto extra crear excepciones propias para manejar las diferentes situaciones, en lugar de usar las genéricas que proporciona el framework.

## 🎁 9. Extras (no obligatorios, pero suman)
- ❌ Uso de DTOs
- ❌ Swagger / documentación de la API
- 🟠 Buen uso de Git (commits claros, ramas, etc.)
- 🟠 Inclusión de un `README.md` claro con instrucciones de ejecución
- **Comentario**: Se ha usado Git correctamente, pero los mensajes de los commits no son descriptivos. Intenta usar mensajes que expliquen claramente qué se ha hecho en cada cambio. El `README.md` está bien estructurado y describe el proyecto, pero le falta una sección donde se indiquen los pasos necesarios para arrancar el proyecto.

---

## 📊 Comentario general

¡Buen trabajo, María!

Has entregado un proyecto bien estructurado, funcional y con un nivel técnico sólido. La separación en capas está muy bien conseguida, el uso de JPA es correcto, y se nota cuidado en el tratamiento de errores y en los mensajes devueltos por la API. Especial mención al uso de validaciones y códigos HTTP apropiados, lo cual es un signo de madurez en el desarrollo web.

Te dejo algunos consejos para seguir mejorando:

- Intenta adoptar el inglés como idioma principal en el código (nombres de clases, variables, etc.).
- Añade una sección en el `README.md` explicando cómo ejecutar el proyecto (por ejemplo: requisitos, cómo iniciar la base de datos, cómo arrancarlo con Maven o desde un IDE).
- Mejora los mensajes de tus commits en Git para que cuenten una historia clara del desarrollo.
- Si quieres llevar el proyecto un paso más allá, podrías separar los modelos en DTOs, añadir autenticación básica con Spring Security, e incorporar Swagger para documentar la API.
- Y por último, no olvides explorar el mundo de los tests. Tener una suite básica de pruebas automáticas marcará una gran diferencia.

¡Enhorabuena por el trabajo realizado y sigue así!

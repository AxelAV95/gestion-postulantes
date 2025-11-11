Este proyecto es una API REST construida con Spring Boot para gestionar postulantes, vacantes y postulaciones. Sigue una arquitectura en capas inspirada en principios de Clean Architecture / Hexagonal con separación clara entre dominio, casos de uso y adaptadores de infraestructura.

## Contenido

- Descripción general
- Estructura del proyecto
- Arquitectura y diseño
- Modelos y DTOs
- Casos de uso (Use Cases)
- Persistencia y adaptadores
- Seguridad (JWT)
- Manejo de errores
- Endpoints principales y ejemplos
- Cómo ejecutar
- Buenas prácticas y próximas mejoras

---

## Descripción general

La aplicación expone una API para:

- Registrar y autenticar usuarios (JWT).
- Crear, listar, obtener y eliminar postulantes.
- Gestionar vacantes y postulaciones (módulos presentes en la estructura del proyecto).

El proyecto está pensado para ser un backend simple, modular y fácilmente testeable.

## Estructura del proyecto (resumen)

Raíz: src/main/java/com/axelav95/gestion_postulantes

- application/
  - controller/    -> Controladores REST (API layer)
  - dto/           -> Objetos de transferencia (validaciones)
- core/
  - domain/        -> Entidades del dominio (Postulante, Vacante, Postulacion)
  - repository/    -> Interfaces de repositorio (puerto)
- infrastructure/
  - config/        -> Configuraciones como `UseCaseConfig`
  - exception/     -> Manejadores globales de excepción
  - persistence/   -> Adapter/persistencia (entity, jpa, adapter)
  - security/      -> JWT, filtros, configuración de seguridad
- usecase/         -> Casos de uso (lógica de aplicación), organizados por contexto (postulante, vacante, postulacion)

Ejemplo de ruta de controlador: `src/main/java/.../application/controller/PostulanteController.java`

## Arquitectura y diseño

El proyecto aplica una separación clara de responsabilidades:

- Domain (core.domain): Objetos puros del dominio sin dependencias de Spring.
- Use Cases (usecase): Lógica de aplicación (interactúan con interfaces de repositorio definidas en core.repository).
- Application (application.controller + dto): Adaptador de entrada — controladores REST y mapeo DTO <-> entidad.
- Infrastructure: Adaptadores de salida — JPA, entidades de persistencia, seguridad, configuración de beans.

Dependencias y wiring se realizan mediante `UseCaseConfig` (clase de configuración Spring) que expone los casos de uso como `@Bean` y permite inyectar implementaciones de `PostulanteRepository`, etc.

Este enfoque facilita:

- Testear la lógica de negocio aislada (mockear repositorios en tests de los usecases).
- Sustituir la implementación de persistencia sin cambiar casos de uso ni controladores.

## Modelos y DTOs

Dominios principales (clase / campos):

- Postulante
  - id: Long
  - nombre: String
  - email: String
  - telefono: String
  - experiencia: String

- Vacante
  - id: Long
  - titulo: String
  - descripcion: String
  - requisitos: String

- Postulacion
  - id: Long
  - postulanteId: Long
  - vacanteId: Long
  - fechaPostulacion: LocalDate

DTOs (ejemplo: `PostulanteDTO`) incluyen validaciones (Jakarta Validation):
- `@NotBlank`, `@Email`, `@Size` con mensajes personalizados.

Mapping: Los controladores crean instancias de dominio desde DTOs y viceversa cuando devuelven respuestas.

### MapStruct — mapeo automático entre DTOs y entidades

En las versiones recientes del proyecto se incorporó MapStruct para reemplazar el mapeo manual entre DTOs y objetos del dominio.
MapStruct es un generador de código que crea implementaciones de mappers en tiempo de compilación, lo que ofrece:

- Código más limpio: evita escribir transformaciones repetitivas (copiar campos uno por uno).
- Menor probabilidad de errores humanos en conversiones.
- Mejor rendimiento que soluciones basadas en reflexión porque MapStruct genera código estático.

Cómo se usa aquí (resumen):

- Definimos interfaces de mapper en `src/main/java/.../application/mapper`, por ejemplo `PostulanteMapper`.
- Anotamos la interfaz con `@Mapper` (o usamos `@Mapper(componentModel = "spring")` para que Spring pueda inyectarla).
- MapStruct genera la implementación en `target/generated-sources/annotations` durante la compilación.
- En los controladores y casos de uso se inyecta el mapper y se usa para convertir `PostulanteDTO` <-> `Postulante`.

Ejemplo (conceptual):

```java
@Mapper(componentModel = "spring")
public interface PostulanteMapper {
  PostulanteDTO toDTO(Postulante postulante);
  Postulante toDomain(PostulanteDTO dto);
}
```

Consejos:

- Asegúrate de tener la dependencia de MapStruct y el procesador de anotaciones (`mapstruct-processor`) en `pom.xml`.
- Si usas Spring, `componentModel = "spring"` permite inyectar el mapper con `@Autowired`.


## Casos de uso (Use Cases)

Los use cases agrupan casos de negocio por contexto. Ejemplos en `usecase/postulante`:

- `CrearPostulanteUseCase` — Lógica para crear y persistir un postulante.
- `ListarPostulantesUseCase` — Retornar lista de postulantes.
- `BuscarPostulantePorIdUseCase` — Buscar por ID y devolver Optional<Postulante>.
- `EliminarPostulanteUseCase` — Eliminar por ID.

Estos use cases se inyectan en los controladores mediante el `UseCaseConfig`.

## Persistencia y adaptadores

La capa `infrastructure.persistence` contiene:

- `entity/` — Entidades JPA para persistencia.
- `jpa/` — Repositorios Spring Data JPA (interfaces que extienden JpaRepository).
- `adapter/` — Adaptadores que implementan las interfaces en `core.repository` y traducen entre entidades JPA y modelos del dominio.

Esta separación permite usar cualquier mecanismo de almacenamiento cambiando únicamente los adaptadores.

## Seguridad (JWT)

La aplicación incorpora autenticación y autorización basada en JWT:

- `AuthController` proporciona endpoints para `POST /api/auth/register` y `POST /api/auth/login`.
- Se usa `JwtService` para generar tokens y un filtro `JwtAuthenticationFilter` para validar peticiones.
- Roles usados (ejemplo): `ADMIN`, `RECLUTADOR`, `USER`. Las anotaciones `@PreAuthorize` en los controladores controlan el acceso a endpoints.

Ejemplo de flujo:

1. Usuario hace POST a `/api/auth/login` con {"username":"...","password":"..."}.
2. Si credenciales válidas, el servidor devuelve {"token":"<JWT>"}.
3. Cliente incluye header `Authorization: Bearer <JWT>` en peticiones posteriores.

## Manejo de errores

`GlobalExceptionHandler` centraliza el manejo de excepciones:

- `MethodArgumentNotValidException` -> devuelve 400 con detalle de campos.
- `RecursoNoEncontradoException` -> devuelve 404 con mensaje.
- Excepciones genéricas -> 500 con mensaje.

Los controladores y usecases lanzan `RecursoNoEncontradoException` o excepciones específicas según convenga.

## Endpoints principales y ejemplos

Auth:

- POST /api/auth/register
  - Body ejemplo: {"username":"juan","password":"1234","rol":"RECLUTADOR"}
- POST /api/auth/login
  - Body ejemplo: {"username":"juan","password":"1234"}
  - Response ejemplo: {"token":"eyJhbGciOi..."}

Postulantes:

- POST /api/postulantes  (roles: ADMIN, RECLUTADOR)
  - Body (PostulanteDTO):
    {
      "nombre": "Ana Pérez",
      "email": "ana@example.com",
      "telefono": "+5491123456789",
      "experiencia": "3 años en Java"
    }
  - Response: PostulanteDTO con id asignado

- GET /api/postulantes  (autenticado)
  - Response: lista de PostulanteDTO

- GET /api/postulantes/{id}  (autenticado)
  - Response: PostulanteDTO o 404

- DELETE /api/postulantes/{id}  (roles: ADMIN)
  - Response: 204/void

Nota: Las reglas de acceso se controlan con `@PreAuthorize` en los controladores.

## Cómo ejecutar (Windows, cmd)

Desde la raíz del proyecto (donde está `mvnw.cmd`):

```cmd
mvnw.cmd clean package
mvnw.cmd spring-boot:run
```

Para tests:

```cmd
mvnw.cmd test
```

Si usas IDE (IntelliJ/Eclipse) importa como proyecto Maven.

## Pruebas (Tests) — cambios recientes

Se han añadido pruebas unitarias/integración para los controladores REST, en particular `PostulanteControllerTest`.
Esta prueba usa:

- JUnit 5 (Jupiter): anotaciones como `@Test` vienen del paquete `org.junit.jupiter.api`.
- Mockito: para mockear los casos de uso y servicios (`Mockito.when(...)`).
- Spring MockMvc: con `@WebMvcTest` y `MockMvc` para probar los endpoints del controlador sin levantar todo el contexto.
- `@MockitoBean` (Spring) para inyectar mocks en el contexto de prueba.

Detalles concretos de la prueba añadida:

- Archivo: `src/test/java/com/axelav95/gestion_postulantes/application/controller/PostulanteControllerTest.java`
- Comportamiento probado: `debeCrearPostulanteConExito` — envía un POST a `/api/postulantes` con un JSON válido y espera `200 OK` y que el JSON devuelto contenga `nombre: "Ana"`.
- Configuración de prueba: `@WebMvcTest(PostulanteController.class)` y `@AutoConfigureMockMvc(addFilters = false)` (se desactivan filtros de seguridad para la prueba).

Dependencias necesarias

Para resolver las importaciones y ejecutar las pruebas correctamente añade (si no están ya en `pom.xml`):

- JUnit Jupiter (api + engine) — ej. `org.junit.jupiter:junit-jupiter:5.9.3` (scope `test`).
- Mockito Core y la integración con JUnit Jupiter — ej. `org.mockito:mockito-core` y `org.mockito:mockito-junit-jupiter` (scope `test`).
- Alternativa: incluir `spring-boot-starter-test` que ya trae JUnit 5 y Mockito configurados para Spring Boot.

Cómo ejecutar las pruebas (Windows PowerShell)

1) Desde la raíz del proyecto (donde está `mvnw.cmd`):

```powershell
.\mvnw.cmd clean test
```

2) Para ejecutar sólo el test de `PostulanteControllerTest`:

```powershell
.\mvnw.cmd -Dtest=PostulanteControllerTest test
```

Consejos si ves "The import org.junit cannot be resolved" o errores de imports similares:

- Asegúrate de importar las dependencias de test en `pom.xml` y ejecutar `mvnw.cmd test` para que Maven descargue los artefactos necesarios.
- Si usas un IDE, después de añadir las dependencias haz un "Maven -> Reimport" o "Reload project" para que el IDE actualice el classpath.
- Preferible usar JUnit 5 (org.junit.jupiter.*). Si tu código usa `org.junit.*` (JUnit 4), considera migrarlo a JUnit 5 o añadir la dependencia de vintage, pero en este proyecto se usan anotaciones de JUnit 5.

Notas

- Las pruebas de controlador están diseñadas para ser rápidas y aisladas. Los casos de uso (usecases) se mockean para evitar acceder a la base de datos.
- Para pruebas de integración completas que levanten el contexto y la base de datos embebida (H2), considera añadir `@SpringBootTest` y perfiles de prueba.


## Consideraciones de diseño y decisiones

- Clean Architecture parcial: los casos de uso no dependen de frameworks; los adaptadores quedan en `infrastructure`.
- DTOs con validación en la capa de entrada evitan que datos inválidos lleguen a la lógica de negocio.
- Uso de `UseCaseConfig` permite configuración explícita y testing manual de beans.

### Clean Architecture (explicado de forma sencilla)

Este proyecto aplica ideas de Clean Architecture para mantener el código modular y fácil de mantener. Aquí lo explicamos en términos simples:

- Separación por capas (qué hace cada una):
  - Core / Domain (`core/domain`): reglas de negocio y modelos puros (sin dependencias a frameworks). Aquí viven las clases como `Postulante`, `Vacante` y `Postulacion`.
  - Puertos / Interfaces (`core/repository`): contratos que describen lo que la aplicación necesita (por ejemplo `PostulanteRepository`) sin decir cómo se implementa.
  - Casos de uso (`usecase`): la lógica de la aplicación (interactores) que orquesta operaciones usando los puertos. No conocen implementación concreta de la persistencia.
  - Adaptadores de entrada (`application/*`): controladores REST, DTOs y mapeos. Convierten solicitudes externas en llamadas a los casos de uso.
  - Adaptadores de salida (`infrastructure/persistence/adapter`, `infrastructure/jpa`, `infrastructure/entity`): implementaciones concretas (JPA, repositorios) que cumplen los contratos definidos en `core/repository`.

- Regla clave (Dependency Rule): las dependencias apuntan hacia adentro. Las capas externas conocen a las internas, pero nunca al revés. Por ejemplo, `usecase` conoce `core.domain` y `core.repository`, pero `core.domain` no depende de `usecase` ni de Spring.

- Ventajas prácticas para este proyecto:
  - Facilita testing: puedes mockear repositorios al probar casos de uso y controlar el comportamiento sin tocar la base de datos.
  - Reemplazo de infra fácil: cambiar de JPA a otro almacenamiento sólo requiere implementar los adaptadores, no reescribir casos de uso.
  - Código más explícito: los contratos (interfaces) dejan claro qué operaciones ofrece la aplicación.

- Cómo aplicarlo aquí (mapa rápido a carpetas):
  - Entidades y modelos: `src/main/java/.../core/domain`
  - Interfaces/puertos: `src/main/java/.../core/repository`
  - Casos de uso: `src/main/java/.../usecase`
  - Controladores y DTOs: `src/main/java/.../application`
  - Implementaciones de persistencia: `src/main/java/.../infrastructure/persistence` (entity, jpa, adapter)

Si algún colaborador necesita empezar a trabajar en una nueva funcionalidad, un buen patrón es:
1. Definir el caso de uso (métodos/contrato) y añadir tests para él.
2. Añadir o actualizar la interfaz en `core/repository` si es necesario.
3. Implementar la lógica en `usecase` usando las interfaces.
4. Crear/actualizar adaptadores y controladores para exponer la funcionalidad.


Edge cases y validaciones comunes:

- Validar unicidad de email al crear postulantes (no implementado en el DTO).
- Manejo de transacciones al crear postulaciones relacionadas.
- Paginación y filtrado para listados (mejora futura).

## Extensiones y mejoras sugeridas

- Añadir control de versiones de API (ej. /api/v1/...).
- Implementar paginación y filtros en listados.
- Añadir DTOs para Vacante y Postulacion con validaciones y endpoints completos.
- Tests unitarios y de integración para Use Cases y controladores (mock MVC o Spring Boot test).

Autor: Axel A.V

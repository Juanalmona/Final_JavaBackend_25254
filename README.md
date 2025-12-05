Final Java Backend 25254
Proyecto final de Java Backend (Comisión 25254).
Sistema de gestión de productos, pedidos e ítems, con persistencia en archivos y endpoints REST mediante Spring Boot.

Características principales
- CRUD de Productos.
- Gestión de Pedidos: creación, inclusión de ítems, confirmación con validación de stock y cálculo de total.
- Uso de DTOs (PedidoDTO, ItemDTO) para JSON limpio y sin recursiones.
- Persistencia en archivos (ProductoArchivo, PedidoArchivo, ItemArchivo).
- Manejo de excepciones personalizadas (StockInsuficienteException).


Requisitos
- Java 17 o superior (LTS recomendado).
- Maven para dependencias.
- Spring Boot (configurado en pom.xml).
- IDE recomendado: IntelliJ IDEA o Eclipse.

Ejecución
- Clonar el repositorio: git clone https://github.com/Juanalmona/Final_JavaBackend_25254.git cd Final_JavaBackend_25254
- Compilar y ejecutar con Maven: mvn spring-boot:run
- La aplicación quedará disponible en: http://localhost:8080

Endpoints principales
Productos
- GET /productos → listar productos
- POST /productos → crear producto
- PUT /productos/{id} → actualizar producto
- DELETE /productos/{id} → eliminar producto
Pedidos
- POST /pedidos → crear pedido
- GET /pedidos → listar pedidos
- GET /pedidos/{id} → obtener pedido por ID
- PUT /pedidos/{idPedido}/items?idProducto={id}&cantidad={n} → agregar ítem
- PUT /pedidos/{id}/confirmar → confirmar pedido
- GET /pedidos/{id}/total → calcular total

Pruebas con Insomnia/Postman
- Crear un pedido: POST http://localhost:8080/pedidos
- Agregar ítem: PUT http://localhost:8080/pedidos/1/items?idProducto=2&cantidad=3
- Confirmar pedido: PUT http://localhost:8080/pedidos/1/confirmar
- Ver total: GET http://localhost:8080/pedidos/1/total


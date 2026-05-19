# carrito-service

Microservicio de gestión de carrito de compras para el ecommerce Librería/Papelería.

## Endpoints disponibles

| Método | URL                                    | Descripción                        |
|--------|----------------------------------------|------------------------------------|
| GET    | /carrito                               | Listar todos los carritos (admin)  |
| GET    | /carrito/{usuarioId}                   | Ver carrito de un usuario          |
| POST   | /carrito/{usuarioId}/items             | Agregar producto al carrito        |
| PUT    | /carrito/{usuarioId}/items/{itemId}    | Actualizar cantidad de un item     |
| DELETE | /carrito/{usuarioId}/items/{itemId}    | Eliminar un item del carrito       |
| DELETE | /carrito/{usuarioId}                   | Vaciar el carrito completo         |

## Ejemplo: Agregar un producto al carrito

**POST** `/carrito/1/items`

```json
{
  "productoId": 5,
  "nombreProducto": "Cuaderno universitario",
  "precioUnitario": 2990.0,
  "cantidad": 2
}
```

**Respuesta:**
```json
{
  "id": 1,
  "usuarioId": 1,
  "items": [
    {
      "id": 1,
      "productoId": 5,
      "nombreProducto": "Cuaderno universitario",
      "precioUnitario": 2990.0,
      "cantidad": 2,
      "subtotal": 5980.0
    }
  ],
  "total": 5980.0
}
```

## Estructura de tablas en Oracle

```
CARRITOS
  id          NUMBER (PK)
  usuario_id  NUMBER
  total       NUMBER

ITEMS_CARRITO
  id               NUMBER (PK)
  carrito_id       NUMBER (FK → CARRITOS)
  producto_id      NUMBER (referencia al microservicio producto)
  nombre_producto  VARCHAR2
  precio_unitario  NUMBER
  cantidad         NUMBER
```

## Configurar la Wallet de Oracle

1. Copiar archivos de wallet en: `src/main/resources/wallet/`
2. Editar `application.properties` y reemplazar:
   - `<NOMBRE_TNS>` → alias del archivo `tnsnames.ora`
   - `TU_USUARIO_ORACLE` → usuario de BD
   - `TU_PASSWORD_ORACLE` → contraseña de BD

## Puertos del grupo

| Microservicio | Puerto |
|---------------|--------|
| Autenticación | 8080   |
| Usuario       | 8081   |
| Producto      | 8082   |
| Carrito       | 8083   |
| Orden         | 8084   |
| Pago          | 8085   |

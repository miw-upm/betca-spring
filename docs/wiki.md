# Tecnología Spring: Proyecto TPV - Back-end
### Back-end con Tecnologías de Código Abierto (SPRING)
### [Máster en Ingeniería Web por la U.P.M.](http://miw.etsisi.upm.es)
---

# Curso 2019-20. Enunciado de prácticas

## 1. Provider 
> **Autor: ???**  
> **GitHub: ???**
>* Añadir el patrón builder a Provider, similar al ejemplo de Article, y eliminar el constructor con muchos parámetros.
>* Crear un componente que es una lista desplegable de Providers (**id-company**). Solo se mostraran los proveedores activos. 
>* Implementar un componente para la busqueda rápida de proveedores, por nombre, nif, phone... 
>* Permitir la creación, lectura, modificación de proveedores. No se permite borrar, solo dejar inactivo al proveedor.  

[Más información...](./Provider)

## 2. Article
> **Autor: ???**  
> **GitHub: ???**
>* Permitir la creación, lectura, modificación de artículos.
>* Debe permitir la creación de artículos sin `code`, se asignará uno automáticamente a partir del `840000000000x` y hasta el `840000099999x` (EAN13). **OJO** habrá que consultar la BD y controlar el límite. En la actualida existe una versión deficiente, ya que no está creada sobre BD.

[Más información...](./Article)

## 3. Articles Query
> **Autor: ???**  
> **GitHub: ???**
>* En el CRUD de artículos, realizar una búsqueda de aquellos artículos que no estén totalmente definidos.    
>* Crear un componente avanzado para la la búsqueda de artículos, mediante filtros, para localizar un artículo con rapidez y pueda ser utilizado en muchos puntos de la aplicación. Debe incluir un filtro (al menos description, reference, stock, provider, discontinued, retailPrice) . Deberá situarse en el carrito de la compra y en página de CRUD de artículos.

[Más información...](./Articles-Query)

## 4. Articles Family View
> **Autor: ???**  
> **GitHub: ???**
>* Permitir la búsqueda de productos mediante agrupaciones jerárquicas. Un artículo puede pertenecer a varias familias. Aquí se utiliza el **patrón Composite**.  
>* Esta parte sólo hace consulta, ya existe un conjunto de ellos en BD.
>* El primer nivel muestra el contenido de `root`. Si se pulsa sobre uno de tipo `ARTICLE` se añade al carro de la compra. 
Si se pulsa sobre un `SIZES` se muestra dialogo con todas las tallas con su stock que son de tipo `ARTICLE`, al elegir uno se cierra el dialog y se añade al carro de la compra. Si se pulsa sobre un tipo `ARTICLES`, se cambia el contenido de la familia.

[Más información...](./Articles-Family-View)

## 5. Articles Family Creator
> **Autor: ???**  
> **GitHub: ???**
>* Permitir realizar una creación rápida de familias de artículos de un solo proveedor, incluyendo la creación de artículos con asignación automática de código. 
>* Las tallas podrían ser 0..60, se podrá configurar la distancia entre tallas, o "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL", "Especial".

[Más información...](./Articles-Family-Creator)

## 6. Articles Family CRUD (+1)
> **Autor: ???**  
> **GitHub: ???**
>* Permitir realizar un CRUD de las familias.

[Más información...](./Articles-Family-CRUD)

## 7. Ticket
> **Autor: ???**   
> **GitHub: ???**
>* Se realizará una busqueda por `id` de ticket.
>* Facilitar lectura y modificación de tickets. La modificación de tickets se permitirá reducir la cantidad de compras de artículos, pudiéndolo dejar a 0, y además, hacer evolucionar el estado de una compra. El resto de aspectos del ticket deben ser invariantes.
>* Cuando se tenga que devolver dinero se realizará mediante un `Voucher`. Ver práctica de `Voucher`.
>* Cuando un producto pase a estado de commited, se debe cobrar el dinero que falta... y si es el último, cobrar todo lo que falta

[Más información...](./Ticket)

## 8. Ticket Query
> **Autor: ???**   
> **GitHub: ???**
>* Facilitar la busqueda avanzada de tickets: por el móvil, por fecha, por cantidad
>* Busqueda de los tickets pendientes de entregar de algún artículo.  
>* Busqueda de los tickets pendientes de entregar por algun artículo de una Order.
>* Busqueda de los tickets pendientes de entregar por algun artículo de una Tag.

[Más información...](./Ticket-Query)

## 9. Ticket Tracking 
> **Autor: ???**  
> **GitHub: ???**
>* Facilitar el seguimiento de artículos por falta de stock de los clientes. Cuando se cree un ticket con una compra no entregada, se habilitará la referencia del ticket para que el cliente pueda acceder al ticket vía Internet para comprobar su estado. El usuario podrá escanear la referencia del ticket mediante un codigo QR, la cual referencia una URL en la que podrá seguir el estado de los artículos que no se le entregaron por falta de stock.
>* Añadir el envío de un email al cliente, cuando el ticket alcance el estado de `IN_STOCK` en alguna compra, indicando el estado de lo llegado.
>* Cuando todo el ticket tenga el estado de `COMMIT`, ya no se podrá consultar más.
 
[Más información...](./Ticket-tracking)

## 10. Budget
> **Autor: ???**  
> **GitHub: ???**
>* Gestión de presupuestos. Será un carro de la compra realizando un **budge** .
>* Se genera un PDF de presupuesto, similar a un ticket.
>* Permite rellenar un carro de la compra desde un presupuesto.
 
[Más información...](./Budget)

## 11. Gift Ticket
> **Autor: ???**  
**GitHub: ???**
>* Se debe crear un nuevo documento que referencia a un Ticket.
>* Id no predecible, similar a Voucher.
>* Se puede añadir un mensaje personalizado.
>* En el proceso de Check-out se puede activar y se imprime aparte, debe aparecer los datos básicos de la empresa y la fecha de caducida
>* Debe ser posible localizar un tiket a partir del ticket-regalo, en el CRUD tickets

[Más información...](./Gift-Ticket)

## 12. Invoice
> **Autor: ???**  
> **GitHub: ???**
>* En el documento Invoice, calcular baseTax & Tax
>* Implementar la creación de facturas a partir de un ticket, en el proceso de creación del ticket o posteriormente. Se deberá asegurar que el usuario tiene los datos necesarios (DNI, nombre de usuario y dirección completa).
>* Permitir la búsqueda de facturas por móvil, o entre un rango de fechas.
>* Permitir la reimpresión de facturas.
>* Ampliar el servicios de Pdf para crear la factura.
>* Se debe sincronizar con la práctica de `Ticket` ya que si un Ticket con factura existe devolución, se debe crear una segunda factura negativa del mismo `Ticket`.  

[Más información...](./Invoice)

## 13. Cash Movements
> **Autor: ???**  
> **GitHub: ???**
>* Registrar movimientos de caja: ingresos o gastos de proveedores.
>* Integrado con el proceso de cierre de caja.

[Más información...](./Cash-Movements)

## 14. Cashier closures
> **Autor: ???**  
> **GitHub: ???**
>* Realizar un filtro para limitar los cierres de caja que se muestran
>* Muestra los cierres de caja existentes

[Más información...](./Cashier-Closures)

## 15. Offer
> **Autor: ???**   
> **GitHub: ???**
>* Se debe crear el documento, debe tener almenos: (id, description, registrationDate, expiryDate, ArticleList, discount).
>* id not predictable, similar Voucher
>* CRUD ofertas
>* Permite consultar de un artículo si se le aplica alguna oferta en la pantalla de carrito de la compra.
>* Se permite imprimir, código QR con la id de la oferta, descripcción, fecha caducidad.

[Más información...](./Offers)

## 16. Customer Discount
> **Autor: ???**  
> **GitHub: ???**
>* Descuentos a clientes especiales.
>* Se debe crear el documento, debe tener almenos: (id, description, registrationDate, discount, minimumPurchase, user)
>* En el proceso de compra, se debe poder aplicar el descuento a un cliente especial, a traves de su móvil.

[Más información...](./Customer-Discount)

## 17. User 
> **Autor: xxx**  
> **GitHub: xxx**
>* Añadir el patrón builder a User, similar al ejemplo de Article, y eliminar el constructor con muchos parámetros.
>* Añadir la creación, lectura, modificación de usuarios. No se pueden borrar, pero si dejarlos en modo inactivo.  
>* Implementar un componente para la creación rápida (móvil-nombre) (datos para factura), dialogo en el proceso de realizar el `check out`, permitir asociar el ticket a un usuario, realizando la creación rápida si no existe. 

[Más información...](./User)

## 18. Advanced User
> **Autor: ???**  
> **GitHub: ???**
>* Crear un componente de busqueda avanzada de usuarios.
>* Permitir a un usuario cambiar su contraseña a traves de su perfil.
>* Permitir en la página de gestion de usuarios, permitir cambiar el rol de un usuario, cumpliendo una logica de autorizaciones.  
>* Un `admin` puede todo. Un `manager` puede modificar `manager`, `operator` y `customer`. Un `operator` no puede alterar roles.

[Más información...](./Advance-User)

## 19. Staff 
> **Autor: ???**  
> **GitHub: ???**
>* Gestionar a los vendedores (operator & Manager).
>* Gestiona de ficha de entrada y salida por día. Tiene que ser automático con el login-logout, pero se debe tener en cuenta que puede haber varios login-logout al día.
>* Da un informe mensual de días y horas, pudiendo filtrar por empleado

[Más información...](./Staff)

## 20. Messenger
> **Autor: ???**  
> **GitHub: ???**
>* Se debe crear el documento en BD
>* Notas internas entre vendedores. Aparecen como dialogos emergentes en el login

[Más información...](./Messenger)

## 21. Order
> **Autor: ???**  
**GitHub: ???**
>* Gestionar la tramitación de pedidos. Se organizarán por proveedor, es decir, un pedido solo puede tener productos de un proveedor. Deberá tener una lista de artículos con una cantidad pedida
>* Permitir crear un pedido a partir de algún pedido anterior.
>* Gestionar la entrada de pedidos. Se deberá ayudar para la comprobación de entrada y la inclusión en los diferentes stocks.

[Más información...](./Orders)

## 22. Stock Alarm
> **Autor: ???**  
**GitHub: ???**
>* Creardocumento con almenos: (id, description, proveedor?, warning?, critical?, list:(article,warning,critical))
>* Los warning-critical específicos predominan respecto a los genéricos.
>* Una alarma podría ser establecer unos valores mínimos (con nivel WARNING y CRITICAL) de un conjunto de artículos.
>* Ampliar con la creación, lectura, modificación y borrado de alarmas por stock.  
>* Se permite consultar artículos en estado WARNING o CRITICAL

[Más información...](./Stock-Alarm)

## 23. Stock Manager
> **Autor: ???**  
> **GitHub: ???**
>* Ayuda a gestionar el stok.  
>* Permite realizar busquedas variadas. Productos por debajo de un stock. El stock puede ser negativo, indicando que existen reservas del mismo.
>* Productos vendidos en un determinado rango.
>* Ayuda a predecir el stock de futuro. 
>* Dado un producto, te indica que stock va haber a lo largo del tiempo.

[Más información...](./Stock-Manager)

## 24. Voucher
> **Autor: ??? **  
> **GitHub: ???**
>* Realizar la creación y lectura. No se debe permitir el borrado ni cambiar su contenido.   
>* Permitir el consumo de un vale. Se debe asegurar previamente que el vale no ha sido ya consumido.  
>* Realizar una consulta de vales pendientes generados entre dos fechas.  
>* Cuando un voucher se crea, debe devolver el pdf del voucher

[Más información...](./Voucher)

## 25. Delivery Management
> **Autor: ???**  
> **GitHub: ???**
>* Gestión de envíos.   

[Más información...](./Delivery-Management)

## Futuras Ampliaciones 

## Data Protection Act
>* Ley de Protección de Datos. existen tres niveles: 
* Básico Se conceden permisos para registrar los datos única y exclusivamente para realizar correctamente la compra y envío de los productos de la tienda.
* Medio: Además del anterior, el usuario concede permisos para que se realicen envíos informándole de ofertas u oportunidades especiales para adquirir productos en oferta.
* Avanzado: Incluye todo lo del contrato medio, y además el usuario concede permiso para que la empresa ceda sus datos a terceros.

## Statistics
>* Ofrecer un conjunto de estudios estadísticos, mostrando en gráficas, de diferentes estudios sobre las Bases de Datos. También se permitirá obtener los datos de forma numérica.
>* Los estudios estadísticos serán abiertos, como ejemplo, se podría estudiar las ventas de un producto a lo largo de un periodo de tiempo, la evolución de ventas en total a lo largo de un periodo...
>* Importante aplicar el patrón `Facade` para mostrar los datos.

### Filtrado de usuario por uso de tags

Gestión de cuenta para pago al final de mes

### Gestión de IVA trimestral

### Asociación de tickets a vendedores. Dado un vendedor obtener producción

### Gestión de puntos de clientes

### Gestión de envíos

### Gestión de auditoria de stock



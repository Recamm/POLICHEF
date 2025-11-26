INSERT INTO Cliente (dni, nombre, apellido, contrasena, fechaNacimiento, email, telefono, cuentaCreada, puntos) VALUES
(40111222, 'María', 'Gómez', 'asd', '1990-05-12', 'maria.gomez@mail.com', '1133445566', '2023-01-15', 120),
(39222444, 'Juan', 'Pérez', 'asd', '1985-09-20', 'juan.perez@mail.com', '1122334455', '2022-11-10', 80),
(41555666, 'Lucía', 'Martínez', 'asd', '1998-02-03', 'lucia.martinez@mail.com', '1144556677', '2023-02-01', 200),
(37888999, 'Carlos', 'Ramírez', 'asd', '1979-12-15', 'carlos.ramirez@mail.com', '1155667788', '2022-10-22', 40),
(42111222, 'Sofía', 'López', 'asd', '2000-07-25', 'sofia.lopez@mail.com', '1133557799', '2023-03-01', 150),
(40666777, 'Federico', 'Sánchez', 'asd', '1992-11-30', 'federico.s@mail.com', '1199887766', '2022-12-12', 90),
(43333444, 'Martina', 'Fernández', 'asd', '1996-08-17', 'martina.f@mail.com', '1133221100', '2023-01-08', 60),
(39999888, 'Diego', 'Suárez', 'asd', '1988-01-28', 'diego.suarez@mail.com', '1166778899', '2022-10-01', 30),
(42222333, 'Valentina', 'Herrera', 'asd', '1999-04-19', 'valen.herrera@mail.com', '1177889900', '2023-02-20', 110),
(41111000, 'Nicolás', 'Torres', 'asd', '1995-06-14', 'nicolas.torres@mail.com', '1122993388', '2023-03-10', 75);

INSERT INTO Notificacion (titulo, descripcion, dniCliente) VALUES
('Promo 2x1', 'Aprovecha el 2x1 en pastas este viernes', 40111222),
('Nuevo menú', 'Agregamos platos veganos en tu sucursal favorita', 39222444),
('Acumulaste puntos', 'Sumaste 20 puntos en tu última compra', 41555666),
('Recordatorio', 'Tienes una reserva mañana a las 20:00', 37888999),
('Feliz cumpleaños', '¡Te regalamos un postre por tu cumpleaños!', 42111222),
('Encuesta', 'Califica tu última visita y gana puntos', 40666777),
('Promo bebidas', 'Happy hour en bebidas hasta las 21hs', 43333444),
('Reserva confirmada', 'Tu reserva fue aprobada', 39999888),
('Stock nuevo', 'Volvió tu plato favorito al menú', 42222333),
('Información', 'Tu pedido está listo para retirar', 41111000);

INSERT INTO Pais (nombre) VALUES
('Italia'),
('México'),
('Japón'),
('Argentina'),
('España'),
('Perú'),
('Estados Unidos'),
('Tailandia'),
('Francia'),
('India');

INSERT INTO Calificacion (puntaje, descripcion) VALUES
(5, 'Excelente experiencia'),
(4, 'Muy bueno'),
(3, 'Bueno'),
(2, 'Regular'),
(1, 'Muy malo'),
(5, 'Servicio impecable'),
(4, 'Buena atención'),
(3, 'Platos aceptables'),
(2, 'Demora en el servicio'),
(1, 'No volvería');

INSERT INTO Sucursal (nombre, direccion, telefono, abiertaDesde) VALUES
('Sucursal Centro', 'Av. Corrientes 1234', '1144455566', '2018-03-10'),
('Sucursal Norte', 'Av. Cabildo 2200', '1141234567', '2019-07-01'),
('Sucursal Sur', 'Av. San Martín 800', '1132145678', '2020-01-15'),
('Sucursal Oeste', 'Av. Rivadavia 9000', '1123456789', '2017-11-20'),
('Sucursal Palermo', 'Av. Santa Fe 3200', '1135784321', '2018-09-05'),
('Sucursal Belgrano', 'Juramento 2300', '1167891234', '2021-05-01'),
('Sucursal Recoleta', 'Callao 1200', '1123987456', '2016-08-17'),
('Sucursal Tigre', 'Cazón 900', '1165437890', '2022-02-20'),
('Sucursal Quilmes', 'Mitre 700', '1145678901', '2019-12-12'),
('Sucursal Lomas', 'Laprovittola 400', '1156789012', '2020-06-06');

INSERT INTO NocheTematica (titulo, descripcion, fecha, idPais) VALUES
('Noche Italiana', 'Pastas y vinos importados', '2023-04-10', 1),
('Noche Mexicana', 'Tacos, burritos y margaritas', '2023-04-15', 2),
('Noche Japonesa', 'Sushi y ramen tradicional', '2023-04-20', 3),
('Noche Argentina', 'Carnes premium y vinos', '2023-05-01', 4),
('Noche Española', 'Tapas y paellas', '2023-05-10', 5),
('Noche Peruana', 'Ceviche y pisco sour', '2023-05-20', 6),
('Noche Americana', 'Hamburguesas y BBQ', '2023-06-01', 7),
('Noche Tailandesa', 'Curry picante y pad thai', '2023-06-10', 8),
('Noche Francesa', 'Quesos y vinos gourmet', '2023-06-20', 9),
('Noche India', 'Especias exóticas y masala', '2023-07-01', 10);

INSERT INTO Mesa (cantPersonas) VALUES
(2),(4),(6),(8),(10),(2),(4),(6),(8),(5);

INSERT INTO Pedido (dniCliente, idSucursal, cantPersonas) VALUES
(40111222, 1, 3),
(39222444, 2, 3),
(41555666, 3, 3),
(37888999, 4, 3),
(42111222, 5, 2),
(40666777, 6, 5),
(43333444, 7, 9),
(39999888, 8, 2),
(42222333, 9, 1),
(41111000, 10, 6);

INSERT INTO Reserva (fechaQueSeReservo, fechaReserva, idPedido, idCalificacion) VALUES
('2023-03-01','2023-03-10',1,null),
('2023-03-02','2023-03-12',2,null),
('2023-03-05','2023-03-15',3,3),
('2023-03-06','2023-03-18',4,4),
('2023-03-07','2023-03-20',5,5),
('2023-03-10','2023-03-25',6,6),
('2023-03-12','2023-03-28',7,7),
('2023-03-14','2023-04-01',8,8),
('2023-03-15','2023-04-05',9,9),
('2023-03-18','2023-04-10',10,10);

INSERT INTO Repetible (idRepetible, repetible) VALUES
(1, TRUE),
(2, FALSE),
(3, TRUE),
(4, FALSE),
(5, TRUE),
(6, FALSE),
(7, TRUE),
(8, FALSE),
(9, TRUE),
(10, FALSE);

INSERT INTO Ingrediente (nombre, stock, minimoStock) VALUES
('Tomate', 100, 20),
('Queso', 80, 15),
('Harina', 200, 50),
('Carne', 60, 20),
('Lechuga', 50, 10),
('Pollo', 70, 20),
('Cebolla', 120, 30),
('Ajo', 90, 25),
('Papas', 150, 40),
('Arroz', 180, 50);

INSERT INTO StockAlert (idIngrediente) VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10);

INSERT INTO Plato (nombre, valor, descripcion, idNocheTematica) VALUES
('Spaghetti Carbonara', 2500, 'Clásico italiano con panceta', 1),
('Tacos al Pastor', 1800, 'Tacos tradicionales', 2),
('Sushi Roll', 3000, 'Salmón y palta', 3),
('Asado Criollo', 3500, 'Carne argentina a la parrilla', 4),
('Paella Valenciana', 3200, 'Arroz con mariscos', 5),
('Ceviche Clásico', 2800, 'Pescado fresco marinado', 6),
('American Burger', 2200, 'Hamburguesa con cheddar', 7),
('Pad Thai', 2600, 'Fideos de arroz y camarones', 8),
('Quiche Lorraine', 2400, 'Tarta francesa', 9),
('Chicken Masala', 2700, 'Pollo especiado estilo India', 10);

INSERT INTO ingredientesDePlatos (idIngrediente, idPlato) VALUES
(1,1),
(2,1),
(3,1),
(4,4),
(5,2),
(6,10),
(7,3),
(8,9),
(9,7),
(10,5);

INSERT INTO PreferenciaAlimenticia (nombre, descripcion) VALUES
('Vegetariano', 'Sin consumo de carne'),
('Vegano', 'Sin productos animales'),
('Sin TACC', 'Apto celíacos'),
('Sin Lactosa', 'Sin derivados lácteos'),
('Bajo Sodio', 'Reducido en sal'),
('Keto', 'Bajo en carbohidratos'),
('Diabético', 'Bajo en azúcar'),
('Pescetariano', 'Solo pescado como carne'),
('Halal', 'Cumple normas islámicas'),
('Kosher', 'Cumple normas judías');

INSERT INTO PreferenciasAlimenticiasDelPlato (idPreferenciasAlimenticias, idPlato) VALUES
(1,9),
(2,8),
(3,3),
(4,1),
(5,5),
(6,7),
(7,10),
(8,6),
(9,4),
(10,2);

INSERT INTO PreferenciasAlimenticiasDelCliente (idPreferenciasAlimenticias, dniCliente) VALUES
(1,40111222),
(2,39222444),
(3,41555666),
(4,37888999),
(5,42111222),
(6,40666777),
(7,43333444),
(8,39999888),
(9,42222333),
(10,41111000);

INSERT INTO platosDelPedido (idPedido, idPlato, cantidad) VALUES
(1,1,2),
(2,2,3),
(3,3,1),
(4,4,2),
(5,5,1),
(6,6,2),
(7,7,3),
(8,8,1),
(9,9,2),
(10,10,1);
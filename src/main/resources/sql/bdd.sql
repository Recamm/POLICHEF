create database PoliChef;
use PoliChef;

create table Cliente(
dni int not null,
nombre text not null,
apellido text not null,
contrasena text not null,
fechaNacimiento date not null,
email text not null,
telefono text,
cuentaCreada date not null,
puntos int not null default 0,
primary key (dni)
);

create table Notificacion (
id int not null auto_increment,
titulo text not null,
descripcion text not null,
dniCliente int not null,
foreign key (dniCliente) references Cliente(dni),
primary key (id)
);

create table Pais (
id int not null auto_increment,
nombre text not null,
primary key (id)
);

create table Calificacion (
id int not null auto_increment,
puntaje int not null, -- 1 - 5
descripcion text not null,
primary key (id)
);

create table Sucursal (
id int not null auto_increment,
nombre text not null,
direccion text not null,
telefono text not null,
abiertaDesde date,
primary key (id)
);

create table NocheTematica (
id int not null auto_increment,
titulo text not null,
descripcion text not null,
fecha date not null,
idPais int not null,
foreign key (idPais) references Pais(id),
primary key (id)
);

create table Mesa (
id int not null auto_increment,
cantPersonas int not null,
primary key (id)
);

create table Pedido(
id int not null auto_increment,
dniCliente int not null,
idSucursal int not null,
cantPersonas int not null,
foreign key (dniCliente) references Cliente(dni),
foreign key (idSucursal) references Sucursal(id),
primary key (id)
);

create table Reserva(
id int not null auto_increment,
fechaQueSeReservo date not null,
fechaReserva date not null,
idPedido int not null,
idCalificacion int,
foreign key (idCalificacion) references Calificacion(id),
foreign key (idPedido) references Pedido(id),
primary key (id)
);

create table Repetible (
idRepetible int not null,
repetible bool not null,
foreign key (idRepetible) references Reserva(id)
);

create table Ingrediente(
id int not null auto_increment,
nombre text not null,
stock int not null,
minimoStock int not null,
primary key (id)
);

create table StockAlert(
idIngrediente int not null,
foreign key (idIngrediente) references Ingrediente(id)
);

create table Plato(
id int not null auto_increment,
nombre text not null,
valor int not null,
descripcion text not null,
idNocheTematica int not null,
foreign key (idNocheTematica) references NocheTematica(id),
primary key (id)
);

create table ingredientesDePlatos(
id int not null auto_increment,
idIngrediente int not null,
idPlato int not null,
foreign key (idIngrediente) references Ingrediente(id),
foreign key (idPlato) references Plato(id),
primary key (id)
);

create table PreferenciaAlimenticia (
id int not null auto_increment,
nombre text not null,
descripcion text not null,
primary key (id)
);

create table PreferenciasAlimenticiasDelPlato (
id int not null auto_increment,
idPreferenciasAlimenticias int not null,
idPlato int not null,
foreign key (idPreferenciasAlimenticias) references PreferenciaAlimenticia(id),
foreign key (idPlato) references Plato(id),
primary key (id)
);

create table PreferenciasAlimenticiasDelCliente (
id int not null auto_increment,
idPreferenciasAlimenticias int not null,
dniCliente int not null,
foreign key (idPreferenciasAlimenticias) references PreferenciaAlimenticia(id),
foreign key (dniCliente) references Cliente(dni),
primary key (id)
);

create table platosDelPedido (
id int not null auto_increment,
idPedido int not null,
idPlato int not null,
cantidad int not null,
foreign key (idPedido) references Pedido(id),
foreign key (idPlato) references Plato(id),
primary key (id)
);
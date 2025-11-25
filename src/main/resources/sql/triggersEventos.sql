#----A----#
#Trigger que, al realizarse un pedido, chequee que todos los productos incluidos en el mismo tengan stock.#
 
delimiter //
create trigger chequear_stock before insert on platosDelPedido
for each row
begin
    declare faltante int default 0;
 
    set faltante = 
		(select count(*) from ingredientesDePlatos as idp 
		join Ingrediente as i on idp.idIngrediente = i.id 
		where idp.idPlato = new.idPlato and i.stock < i.minimoStock);
 
 
    if faltante > 0 then
        signal sqlstate '45000'
        set message_text = 'No se puede agregar el plato: un ingrediente tiene stock insuficiente.';
    end if;
end//
delimiter ;
 
 
#----B----#
#Trigger que, al realizarse una reserva, chequee que la mesa esté disponible y que sea compatible con la cantidad de personas elegidas.#
 
delimiter //
create trigger disponibilidad_mesa before insert on Reserva
for each row
begin
    declare cantPersonasMesa int default 0;
    declare reservasExistentes int default 0;
    declare idMesaReservas int default 0;
 
    set idMesaReservas = (
        select idMesa from mesasPedidos as mp
        where mp.idPedido = new.idPedido limit 1);
 
    if idMesaReservas is null then
        signal sqlstate '45000'
        set message_text = 'El pedido no tiene una mesa asignada.';
    end if;
 
    set cantPersonasMesa = (
        select cantPersonas from Mesa
        where Mesa.id = idMesaReservas);
 
 
    set reservasExistentes = 
		(select count(*) from Reserva
        join Pedido on Reserva.idPedido = Pedido.id
        join mesasPedidos as mp on mp.idPedido = Pedido.id
        where mp.idMesa = idMesaReservas and Reserva.fechaReserva = new.fechaReserva);
 
    if reservasExistentes > 0 then
        signal sqlstate '45000'
        set message_text = 'La mesa ya está reservada para esa fecha.';
    end if;
 
    if (select cantPersonas from Pedido where id = new.idPedido) > cantPersonasMesa then
        signal sqlstate '45000'
        set message_text = 'La mesa no tiene capacidad suficiente para la cantidad de personas.';
    end if;
end//
delimiter ;
 
 
#----C----#
#Procedimiento que, dado un rango de fechas, liste un top 3 de los platos más pedidos en ese período.#
 
delimiter //
create procedure top3_platos_mas_pedidos (in fechaInicio date, in fechaFin date)
begin
    select Plato.nombre as Nombre, count(pd.idPlato) as Veces_Pedido 
    from platosDelPedido as pd
    join Pedido on pd.idPedido = Pedido.id
    join Reserva on Reserva.idPedido = Pedido.id
    join Plato on Plato.id = pd.idPlato
    where Reserva.fechaReserva > fechaInicio 
    and Reserva.fechaReserva < fechaFin
    group by Plato.id, Plato.nombre order by Veces_Pedido desc limit 3;
end//
delimiter ;
 
drop procedure top3_platos_mas_pedidos;
 
#----D----#
#Procedimiento que liste la cantidad de clientes satisfechos y el total recaudado por mes.#
 
delimiter //
create procedure clientes_satisfechos_y_recaudacion_por_mes()
begin
    select 
        year(Reserva.fechaReserva) as Anio,
        month(Reserva.fechaReserva) as Mes,
        count(distinct Pedido.dniCliente) as Clientes_Satisfechos,
        sum(Plato.valor) as Total_Recaudado
    from Reserva
    join Pedido on Reserva.idPedido = Pedido.id
    join platosDelPedido as pd on pd.idPedido = Pedido.id
    join Plato on Plato.id = pd.idPlato
    join Calificacion on Calificacion.id = Reserva.idCalificacion
    where Calificacion.puntaje >= 4
    group by year(Reserva.fechaReserva), month(Reserva.fechaReserva)
    order by Anio, Mes;
end//
delimiter ;
 
#----E----#
#Evento que, todos los días a las 8:00AM coloque en una tabla de notificaciones, los recordatorios para las personas que tienen reservas para ese mismo día.#
 
delimiter //
create event generar_notificaciones_reservas on schedule every 1 day
starts '2025-11-07 08:00:00' do
begin
    declare dniClienteAux int;
 
    declare fin int default 0;
    declare bucle cursor for
        select Pedido.dniCliente
        from Reserva
        join Pedido on Reserva.idPedido = Pedido.id
        where Reserva.fechaReserva = date(now());
 
    declare continue handler for not found set fin = 1;
 
    open bucle;
    loop_clientes: loop
        fetch bucle into dniClienteAux;
        if fin = 1 then
            leave loop_clientes;
        end if;
 
        insert into Notificacion (titulo, descripcion, dniCliente)
        values ('Recordatorio de Reserva', 'Tiene una reserva para hoy', dniClienteAux);
    end loop loop_clientes;
    close bucle;
end//
delimiter ;
 
 
 
#----F----#
#Evento que, una vez por semana, coloque en una tabla alerta_stock aquellos productos que tienen solo un 20% más que su stock mínimo.#
 
delimiter //
create event generar_alerta_stock on schedule every 1 week
starts timestamp(current_date, '08:00:00') do
begin
    insert into StockAlert (idIngrediente)
    select Ingrediente.id
    from Ingrediente
    where Ingrediente.stock <= Ingrediente.minimoStock * 1.2
    and Ingrediente.id not in (select idIngrediente from StockAlert);
end//
delimiter ;
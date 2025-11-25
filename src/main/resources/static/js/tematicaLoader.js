const API_URL = 'http://localhost:8080/api/eventos/tematicas/semana-actual';

async function cargarTematicaActual() {
    try {
        console.log('Intentando cargar temática desde:', API_URL);
        
        const response = await fetch(API_URL);
        
        console.log('Respuesta recibida:', response.status);
        
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }
        
        const tematica = await response.json();
        
        console.log('Temática recibida:', tematica);
        
        if (tematica && tematica.id) {
            actualizarTematicaEnPagina(tematica);
        } else {
            mostrarMensajeSinTematica();
        }
        
    } catch (error) {
        console.error('Error detallado:', error);
        console.error('Tipo de error:', error.name);
        console.error('Mensaje:', error.message);
        mostrarMensajeError();
    }
}

function actualizarTematicaEnPagina(tematica) {
    const h1Titulo = document.querySelector('.tematica .titleSection h1');
    if (h1Titulo) {
        h1Titulo.textContent = `¡Disfruta de la gastronomía de ${tematica.pais}!`;
    }
    
    const img = document.querySelector('.tematica .imgInfo img');
    if (img) {
        img.src = `../static/img/web/tematica/${tematica.id}.jpg`;
        img.alt = tematica.titulo;
    }
    
    const descripcion = document.querySelector('.tematica .imgInfo .info p');
    if (descripcion) {
        descripcion.textContent = tematica.descripcion;
    }
}

function mostrarMensajeSinTematica() {
    const h1Titulo = document.querySelector('.tematica .titleSection h1');
    if (h1Titulo) {
        h1Titulo.textContent = 'Próximamente nuevas temáticas';
    }
    
    const descripcion = document.querySelector('.tematica .imgInfo .info p');
    if (descripcion) {
        descripcion.textContent = 'Esta semana no tenemos una temática especial, pero pronto habrá novedades. ¡Estate atento!';
    }
}

function mostrarMensajeError() {
    const descripcion = document.querySelector('.tematica .imgInfo .info p');
    if (descripcion) {
        descripcion.textContent = 'No pudimos cargar la información de la temática actual. Por favor, intenta más tarde.';
    }
}

document.addEventListener('DOMContentLoaded', cargarTematicaActual);
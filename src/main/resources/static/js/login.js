const API_URL = 'http://localhost:8080/api';

async function iniciarSesion(event) {
    event.preventDefault(); 
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;

    if (!email || !password) {
        mostrarMensaje('Por favor, completa todos los campos', 'error');
        return;
    }

    if (!validarEmail(email)) {
        mostrarMensaje('Por favor, ingresa un email válido', 'error');
        return;
    }

    const loginData = {
        email: email,
        password: password
    };

    try {
        mostrarCargando(true);

        const response = await fetch(`${API_URL}/usuarios/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json();

        if (response.ok && data.success) {
            mostrarMensaje('¡Bienvenido! Redirigiendo...', 'success');
            localStorage.setItem('userLoggedIn', 'true');
            localStorage.setItem('userDni', data.dni);
            localStorage.setItem('userName', data.nombre);
            localStorage.setItem('userLastName', data.apellido);
            localStorage.setItem('userEmail', email);
            
            setTimeout(() => {
                window.location.href = '../templates/index.html';
            }, 1500);
        } else {
            mostrarMensaje(data.message || 'Credenciales incorrectas', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarMensaje('Error de conexión con el servidor', 'error');
    } finally {
        mostrarCargando(false);
    }
}

function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

function mostrarMensaje(mensaje, tipo) {
    const mensajeAnterior = document.querySelector('.mensaje-alerta');
    if (mensajeAnterior) {
        mensajeAnterior.remove();
    }

    const div = document.createElement('div');
    div.className = `mensaje-alerta mensaje-${tipo}`;
    div.textContent = mensaje;
    
    const inputs = document.querySelector('.inputs');
    inputs.parentNode.insertBefore(div, inputs);

    setTimeout(() => {
        div.remove();
    }, 5000);
}

function mostrarCargando(mostrar) {
    const boton = document.querySelector('.button');
    if (mostrar) {
        boton.disabled = true;
        boton.textContent = 'Ingresando...';
    } else {
        boton.disabled = false;
        boton.textContent = 'Ingresar';
    }
}

function verificarSesion() {
    const userLoggedIn = localStorage.getItem('userLoggedIn');
    if (userLoggedIn === 'true') {
        window.location.href = '../templates/index.html';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    verificarSesion();
    
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', iniciarSesion);
    }
    
    const forgotPasswordLink = document.querySelector('.forgotPassword');
    if (forgotPasswordLink) {
        forgotPasswordLink.addEventListener('click', function(e) {
            e.preventDefault();
            alert('Funcionalidad próximamente. Por favor, contacta al administrador.');
        });
    }
});
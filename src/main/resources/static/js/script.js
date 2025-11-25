const API_URL = 'http://localhost:8080/api';

async function registrarUsuario(event) {
    event.preventDefault();

    const nombre = document.getElementById('nombre').value.trim();
    const apellido = document.getElementById('apellido').value.trim();
    const dni = document.getElementById('dni').value.trim();
    const email = document.getElementById('email').value.trim();
    const telefono = document.getElementById('telefono').value.trim();
    const fechaNacimiento = document.getElementById('fechaNacimiento').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const politicas = document.getElementById('politcs').checked;

    if (!nombre || !apellido || !dni || !email || !fechaNacimiento || !password || !confirmPassword) {
        mostrarMensaje('Por favor, completa todos los campos obligatorios', 'error');
        return;
    }

    if (!politicas) {
        mostrarMensaje('Debes aceptar los términos y condiciones', 'error');
        return;
    }

    if (password !== confirmPassword) {
        mostrarMensaje('Las contraseñas no coinciden', 'error');
        return;
    }

    if (dni.length < 7 || dni.length > 8) {
        mostrarMensaje('El DNI debe tener entre 7 y 8 dígitos', 'error');
        return;
    }

    if (!validarEmail(email)) {
        mostrarMensaje('Por favor, ingresa un email válido', 'error');
        return;
    }

    const usuarioData = {
        dni: parseInt(dni),
        nombre: nombre,
        apellido: apellido,
        contrasena: password,
        fechaNacimiento: fechaNacimiento,
        email: email,
        telefono: telefono || null,
        preferenciasAlimenticias: []
    };

    try {
        mostrarCargando(true);

        const response = await fetch(`${API_URL}/usuarios`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(usuarioData)
        });

        const data = await response.json();

        if (response.ok) {
            mostrarMensaje('¡Registro exitoso! Redirigiendo...', 'success');
            
            localStorage.setItem('userDni', dni);
            
            setTimeout(() => {
                window.location.href = 'login.html';
            }, 2000);
        } else {
            mostrarMensaje(data.message || 'Error al registrar usuario', 'error');
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
    
    const form = document.querySelector('form');
    form.parentNode.insertBefore(div, form);

    setTimeout(() => {
        div.remove();
    }, 5000);
}

function mostrarCargando(mostrar) {
    const boton = document.querySelector('.button');
    if (mostrar) {
        boton.disabled = true;
        boton.textContent = 'Registrando...';
    } else {
        boton.disabled = false;
        boton.textContent = 'Registrarse';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', registrarUsuario);
    }
});
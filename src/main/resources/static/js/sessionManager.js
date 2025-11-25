const SessionManager = {
    isLoggedIn: function() {
        return localStorage.getItem('userLoggedIn') === 'true';
    },
    
    getUserData: function() {
        if (!this.isLoggedIn()) {
            return null;
        }
        
        return {
            dni: localStorage.getItem('userDni'),
            nombre: localStorage.getItem('userName'),
            apellido: localStorage.getItem('userLastName'),
            email: localStorage.getItem('userEmail')
        };
    },
    
    logout: function() {
        localStorage.removeItem('userLoggedIn');
        localStorage.removeItem('userDni');
        localStorage.removeItem('userName');
        localStorage.removeItem('userLastName');
        localStorage.removeItem('userEmail');
        
        window.location.href = '../templates/login.html';
    },
    
    protectPage: function() {
        if (!this.isLoggedIn()) {
            window.location.href = '../templates/login.html';
        }
    },
    
    displayUserInHeader: function() {
        const userData = this.getUserData();
        if (userData) {
            const userDisplay = document.querySelector('.user-name');
            if (userDisplay) {
                userDisplay.textContent = `${userData.nombre} ${userData.apellido}`;
            }
            
            const loginBtn = document.querySelector('.login-btn');
            const logoutBtn = document.querySelector('.logout-btn');
            
            if (loginBtn) loginBtn.style.display = 'none';
            if (logoutBtn) {
                logoutBtn.style.display = 'block';
                logoutBtn.addEventListener('click', () => this.logout());
            }
        }
    }
};

function requireLogin() {
    SessionManager.protectPage();
}

document.addEventListener('DOMContentLoaded', function() {
    if (SessionManager.isLoggedIn()) {
        SessionManager.displayUserInHeader();
    }
});
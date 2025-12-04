// ======================== THEME MANAGEMENT WITH COOKIES ========================

// Set cookie
function setCookie(name, value, days = 365) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

// Get cookie
function getCookie(name) {
    const nameEQ = name + "=";
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i].trim();
        if (cookie.indexOf(nameEQ) === 0) {
            return cookie.substring(nameEQ.length);
        }
    }
    return null;
}

// Initialize theme on page load
function initializeTheme() {
    // Check if there's a saved theme preference
    const savedTheme = getCookie('theme-preference');
    
    // If no preference, detect system preference
    if (!savedTheme) {
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        applyTheme(prefersDark ? 'dark' : 'light');
    } else {
        applyTheme(savedTheme);
    }
}

// Apply theme
function applyTheme(theme) {
    const themeLink = document.getElementById('theme-stylesheet');
    
    if (theme === 'dark') {
        themeLink.href = '/css/dark.css';
        setCookie('theme-preference', 'dark');
        updateThemeToggleButton('light'); // Show light icon to switch to light
    } else {
        themeLink.href = '/css/index.css';
        setCookie('theme-preference', 'light');
        updateThemeToggleButton('dark'); // Show dark icon to switch to dark
    }
}

// Update theme toggle button icon
function updateThemeToggleButton(nextTheme) {
    const btn = document.querySelector('.theme-toggle-btn');
    if (btn) {
        if (nextTheme === 'dark') {
            btn.innerHTML = '<i class="bi bi-moon-stars-fill"></i>';
            btn.title = 'Ativar Modo Escuro';
        } else {
            btn.innerHTML = '<i class="bi bi-sun-fill"></i>';
            btn.title = 'Ativar Modo Claro';
        }
    }
}

// Toggle theme
function toggleTheme() {
    const currentTheme = getCookie('theme-preference') || 'light';
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
    applyTheme(newTheme);
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', initializeTheme);

// Listen for system theme changes
window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
    const savedTheme = getCookie('theme-preference');
    if (!savedTheme) {
        applyTheme(e.matches ? 'dark' : 'light');
    }
});

const API_URL = 'http://localhost:8080/leucine';

document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('login-form');
  const signupForm = document.getElementById('signup-form');

  if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const username = loginForm.username.value;
      const password = loginForm.password.value;
      const role = loginForm.role.value;

      try {
        const response = await fetch(`${API_URL}/auth/authenticate`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ username, password, role })
        });

        const data = await response.json();

        if (response.ok) {
          localStorage.setItem('token', data.token);
          localStorage.setItem('role', role);
          redirectToDashboard(role);
        } else {
          document.getElementById('error-message').textContent = data.message || 'Login failed';
        }
      } catch (error) {
        document.getElementById('error-message').textContent = 'An error occurred';
      }
    });
  }

  if (signupForm) {
    signupForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const user = {
        username: signupForm.username.value,
        password: signupForm.password.value,
        role: signupForm.role.value,
        name: signupForm.name.value,
        email: signupForm.email.value,
        phone: signupForm.phone.value,
      };

      try {
        const response = await fetch(`${API_URL}/auth/register`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(user)
        });

        const data = await response.json();

        if (response.ok) {
          window.location.href = 'index.html';
        } else {
          document.getElementById('error-message').textContent = data.message || 'Sign up failed';
        }
      } catch (error) {
        document.getElementById('error-message').textContent = 'An error occurred';
      }
    });
  }

  const logoutButton = document.getElementById('logout-button');
  if (logoutButton) {
    logoutButton.addEventListener('click', () => {
      localStorage.removeItem('token');
      localStorage.removeItem('role');
      window.location.href = 'index.html';
    });
  }
});

function redirectToDashboard(role) {
  switch (role) {
    case 'STUDENT':
      window.location.href = 'student.html';
      break;
    case 'FACULTYMEMBER':
      window.location.href = 'faculty.html';
      break;
    case 'ADMINISTRATOR':
      window.location.href = 'admin.html';
      break;
    default:
      window.location.href = 'index.html';
  }
}

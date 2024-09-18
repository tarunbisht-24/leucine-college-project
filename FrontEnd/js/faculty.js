document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      window.location.href = 'index.html';
    }
  
    fetchClassList();
    setupUpdateProfileForm();
  
    async function fetchClassList() {
      try {
        const response = await fetch(`${API_URL}/faculty/classlist`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
  
        if (response.ok) {
          const students = await response.json();
          const classListItems = document.getElementById('class-list-items');
          classListItems.innerHTML = students.map(student => `<li>${student.name} - ${student.email} - ${student.phone}</li>`).join('');
        } else {
          console.error('Failed to fetch class list');
        }
      } catch (error) {
        console.error('An error occurred', error);
      }
    }
  
    function setupUpdateProfileForm() {
      const updateProfileForm = document.getElementById('update-profile-form');
      updateProfileForm.addEventListener('submit', async (e) => {
        e.preventDefault();
  
        const profileData = {
          officeHours: updateProfileForm['office-hours'].value,
          email: updateProfileForm['contact-email'].value,
          phone: updateProfileForm['contact-phone'].value,
        };
  
        try {
          const response = await fetch(`${API_URL}/faculty/profile`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(profileData)
          });
  
          if (response.ok) {
            alert('Profile updated successfully');
          } else {
            console.error('Failed to update profile');
          }
        } catch (error) {
          console.error('An error occurred', error);
        }
      });
    }
  });
  
document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      window.location.href = 'index.html';
    }
  
    fetchStudentProfile();
    setupSearchStudents();
    fetchFacultyAdvisors();
  
    async function fetchStudentProfile() {
      try {
        const response = await fetch(`${API_URL}/students/profile`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
  
        if (response.ok) {
          const data = await response.json();
          document.getElementById('personal-info').textContent = `Name: ${data.name}, Email: ${data.email}, Phone: ${data.phone}`;
          document.getElementById('academic-info').textContent = `Courses: ${data.courses}, Grades: ${data.grades}, Attendance: ${data.attendance}`;
        } else {
          console.error('Failed to fetch profile data');
        }
      } catch (error) {
        console.error('An error occurred', error);
      }
    }
  
    async function setupSearchStudents() {
      const searchBar = document.getElementById('search-bar');
      searchBar.addEventListener('input', async (e) => {
        const query = e.target.value;
        try {
          const response = await fetch(`${API_URL}/students/search?query=${query}`, {
            headers: {
              'Authorization': `Bearer ${token}`
            }
          });
  
          if (response.ok) {
            const students = await response.json();
            const searchResults = document.getElementById('search-results');
            searchResults.innerHTML = students.map(student => `<li>${student.name} - ${student.department} - ${student.year}</li>`).join('');
          } else {
            console.error('Failed to search students');
          }
        } catch (error) {
          console.error('An error occurred', error);
        }
      });
    }
  
    async function fetchFacultyAdvisors() {
      try {
        const response = await fetch(`${API_URL}/faculty/advisors`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
  
        if (response.ok) {
          const advisors = await response.json();
          const advisorList = document.getElementById('advisor-list');
          advisorList.innerHTML = advisors.map(advisor => `<li>${advisor.name} - ${advisor.email} - ${advisor.phone}</li>`).join('');
        } else {
          console.error('Failed to fetch faculty advisors');
        }
      } catch (error) {
        console.error('An error occurred', error);
      }
    }
  });
  
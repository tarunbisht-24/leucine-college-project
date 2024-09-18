document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
      window.location.href = 'index.html';
    }
  
    setupRecordForm();
    fetchRecords();
    setupCharts();
  
    function setupRecordForm() {
      const recordForm = document.getElementById('record-form');
      recordForm.addEventListener('submit', async (e) => {
        e.preventDefault();
  
        const recordData = {
          username: recordForm['record-username'].value,
          password: recordForm['record-password'].value,
          role: recordForm['record-role'].value,
          name: recordForm['record-name'].value,
          email: recordForm['record-email'].value,
          phone: recordForm['record-phone'].value,
        };
  
        try {
          const response = await fetch(`${API_URL}/admin/records`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(recordData)
          });
  
          if (response.ok) {
            fetchRecords();
            alert('Record added/updated successfully');
          } else {
            console.error('Failed to add/update record');
          }
        } catch (error) {
          console.error('An error occurred', error);
        }
      });
    }
  
    async function fetchRecords() {
      try {
        const response = await fetch(`${API_URL}/admin/records`, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
  
        if (response.ok) {
          const records = await response.json();
          const recordList = document.getElementById('record-list');
          recordList.innerHTML = records.map(record => `<li>${record.name} - ${record.email} - ${record.phone}</li>`).join('');
        } else {
          console.error('Failed to fetch records');
        }
      } catch (error) {
        console.error('An error occurred', error);
      }
    }
  
    function setupCharts() {
      const enrollmentTrendsChart = new Chart(document.getElementById('enrollment-trends-chart'), {
        type: 'line',
        data: {
          labels: ['January', 'February', 'March', 'April', 'May'],
          datasets: [{
            label: 'Student Enrollment Trends',
            data: [10, 20, 30, 40, 50],
            borderColor: 'rgba(75, 192, 192, 1)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
          }]
        },
        options: {
          responsive: true,
        }
      });
  
      const courseLoadChart = new Chart(document.getElementById('course-load-chart'), {
        type: 'bar',
        data: {
          labels: ['Course 1', 'Course 2', 'Course 3'],
          datasets: [{
            label: 'Faculty Course Loads',
            data: [10, 20, 30],
            backgroundColor: 'rgba(153, 102, 255, 0.2)',
            borderColor: 'rgba(153, 102, 255, 1)',
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
        }
      });
    }
  });
  
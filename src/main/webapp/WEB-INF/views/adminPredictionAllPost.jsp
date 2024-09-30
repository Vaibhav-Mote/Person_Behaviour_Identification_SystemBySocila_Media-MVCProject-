<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta charset="ISO-8859-1">
  <title>Admin Prediction</title>
  <link rel="stylesheet" href="resources/CSS/adminIndex.css">
  <link rel="stylesheet" href="../contrast-bootstrap-pro/css/bootstrap.min.css" />
  <link rel="stylesheet" href="../contrast-bootstrap-pro/css/cdb.css" />
  <script src="../contrast-bootstrap-pro/js/cdb.js"></script>
  <script src="../contrast-bootstrap-pro/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/9d1d9a82d2.js" crossorigin="anonymous"></script>
  <style>
    .chart-container {
      width: 50%;
      height: 50%;
      margin: auto;
    }
  </style>
</head>
<body>
<div class="maindiv">
    <div class="leftMenudiv">
        <%@ include file="adminMenus.jsp" %>
    </div>
    <div class="rightMenuDiv">
        <div class="card chart-container" style="margin-top:70px;height:450px;width:80%">
            <canvas id="chart" style="width:100%"></canvas>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
<script>
  // Retrieve the behavior label and its value from JSP model
  const p1 = '${p1}'; 
  const p2 = '${p2}'; 
  const p3 = '${p3}'; 
  const p4 = '${p4}'; 
  const p5 = '${p5}'; 

  // Setup the chart
  const ctx = document.getElementById("chart").getContext('2d');

  // Define labels and data array
  const labels = ["Openness to Experience", "Conscientiousness", "Extraversion", "Agreeableness", "Neuroticism"];
  const data = [p1, p2, p3, p4, p5]; // Initialize data array with zeros

  
  const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: 'Human Behaviour',
        backgroundColor: 'rgba(161, 198, 247, 1)',
        borderColor: 'rgb(47, 128, 237)',
        data: data // Use the updated data array
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loan EMI</title>
</head>
<body>
<h1>Loan EMI Calculation</h1>

<% if (request.getAttribute("message") != null) { %>
    <p><b><%= request.getAttribute("message") %></b></p>
<% } %>

<form action="loan" method="post">
    Customer ID: <input type="number" name="customerId" required><br>
    Principal Amount: <input type="number" step="0.01" name="principal" required><br>
    Annual Interest Rate (%): <input type="number" step="0.01" name="rate" required><br>
    Months: <input type="number" name="months" required><br>
    <button type="submit">Calculate EMI</button>
</form>

<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>

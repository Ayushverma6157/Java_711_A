<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<h1>Customer Details</h1>

<% if (request.getAttribute("message") != null) { %>
    <p><b><%= request.getAttribute("message") %></b></p>
<% } %>

<form action="customer" method="post">
    Customer ID: <input type="number" name="customerId" required>
    <button type="submit">Search</button>
</form>

<% if (request.getAttribute("name") != null) { %>
    <h2>Result</h2>
    <p>Customer ID: <%= request.getAttribute("customerId") %></p>
    <p>Name: <%= request.getAttribute("name") %></p>
    <p>Email: <%= request.getAttribute("email") %></p>
    <p>Phone: <%= request.getAttribute("phone") %></p>
    <p>Account ID: <%= request.getAttribute("accountId") %></p>
    <p>Balance: <%= request.getAttribute("balance") %></p>
<% } %>

<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>

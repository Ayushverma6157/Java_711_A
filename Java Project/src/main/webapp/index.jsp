<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mini Banking System</title>
</head>
<body>
<h1>Mini Banking System</h1>

<% if (request.getAttribute("message") != null) { %>
    <p><b><%= request.getAttribute("message") %></b></p>
<% } %>

<h2>Create Savings Account</h2>
<form action="account" method="post">
    <input type="hidden" name="action" value="create">
    Name: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    Phone: <input type="text" name="phone" required><br>
    Opening Balance: <input type="number" step="0.01" name="balance" required><br>
    <button type="submit">Create Account</button>
</form>

<h2>Deposit / Withdraw</h2>
<form action="account" method="post">
    Account ID: <input type="number" name="accountId" required><br>
    Amount: <input type="number" step="0.01" name="amount" required><br>
    <button type="submit" name="action" value="deposit">Deposit</button>
    <button type="submit" name="action" value="withdraw">Withdraw</button>
</form>

<h2>Calculate Savings Interest</h2>
<form action="account" method="post">
    <input type="hidden" name="action" value="interest">
    Account ID: <input type="number" name="accountId" required><br>
    <button type="submit">Calculate Interest</button>
</form>

<p>
    <a href="customer.jsp">View Customer Details</a> |
    <a href="loan.jsp">Calculate Loan EMI</a>
</p>
</body>
</html>

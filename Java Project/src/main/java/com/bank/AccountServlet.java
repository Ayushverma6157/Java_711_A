package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection con = DBConnection.getConnection()) {
            if ("create".equals(action)) {
                String message = createAccount(request, con);
                request.setAttribute("message", message);
            } else if ("deposit".equals(action) || "withdraw".equals(action)) {
                updateBalance(request, con, action);
                request.setAttribute("message", "Transaction completed successfully.");
            } else if ("interest".equals(action)) {
                double interest = calculateInterest(request, con);
                request.setAttribute("message", "Interest amount: " + String.format("%.2f", interest));
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private String createAccount(HttpServletRequest request, Connection con) throws Exception {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        double balance = Double.parseDouble(request.getParameter("balance"));

        String customerSql = "INSERT INTO Customer(name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int customerId = rs.getInt(1);
                    SavingsAccount account = new SavingsAccount(customerId, balance, 4.0);
                    String accountSql = "INSERT INTO Account(customer_id, account_type, balance) VALUES (?, ?, ?)";
                    try (PreparedStatement accountPs = con.prepareStatement(accountSql, Statement.RETURN_GENERATED_KEYS)) {
                        accountPs.setInt(1, account.getCustomerId());
                        accountPs.setString(2, account.getAccountType());
                        accountPs.setDouble(3, account.getBalance());
                        accountPs.executeUpdate();

                        try (ResultSet accountRs = accountPs.getGeneratedKeys()) {
                            if (accountRs.next()) {
                                int accountId = accountRs.getInt(1);
                                return "Account created. Customer ID: " + customerId + ", Account ID: " + accountId;
                            }
                        }
                    }
                }
            }
        }
        return "Account created successfully.";
    }

    private void updateBalance(HttpServletRequest request, Connection con, String action) throws Exception {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        double balance = getBalance(con, accountId);
        Account account = new Account();
        account.setAccountId(accountId);
        account.setBalance(balance);

        if ("deposit".equals(action)) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }

        String sql = "UPDATE Account SET balance = ? WHERE account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    private double calculateInterest(HttpServletRequest request, Connection con) throws Exception {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        double balance = getBalance(con, accountId);
        SavingsAccount account = new SavingsAccount(0, balance, 4.0);
        return account.calculateInterest();
    }

    private double getBalance(Connection con, int accountId) throws Exception {
        String sql = "SELECT balance FROM Account WHERE account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                }
            }
        }
        throw new Exception("Account not found.");
    }
}

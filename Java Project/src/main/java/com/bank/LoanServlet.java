package com.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loan")
public class LoanServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            double principal = Double.parseDouble(request.getParameter("principal"));
            double rate = Double.parseDouble(request.getParameter("rate"));
            int months = Integer.parseInt(request.getParameter("months"));

            Loan loan = new Loan(customerId, principal, rate, months);
            double emi = loan.calculateEMI();

            String sql = "INSERT INTO Loan(customer_id, principal, annual_rate, months, emi) VALUES (?, ?, ?, ?, ?)";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, loan.getCustomerId());
                ps.setDouble(2, loan.getPrincipal());
                ps.setDouble(3, loan.getAnnualRate());
                ps.setInt(4, loan.getMonths());
                ps.setDouble(5, emi);
                ps.executeUpdate();
            }

            request.setAttribute("message", "Monthly EMI: " + String.format("%.2f", emi));
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("loan.jsp").forward(request, response);
    }
}

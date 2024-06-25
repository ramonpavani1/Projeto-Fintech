package fintech.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saldo")
public class SaldoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Statement statement = createStatement(null);

            ResultSet resultado = statement.executeQuery("SELECT (SELECT SUM(valor) FROM entrada) - (SELECT SUM(valor) FROM gasto)");

            int saldo = 0;

            if (resultado.next()) {
                saldo = resultado.getInt(1);
            }
            
            request.setAttribute("saldo", saldo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
            
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
}
package fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fintech.bean.UsuarioModel;
import fintech.bo.EmailBO;
import fintech.dao.UsuarioDAO;
import fintech.exception.EmailException;
import fintech.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;
    private EmailBO bo;

    public LoginServlet() {
        dao = DAOFactory.getUsuarioDAO();
        bo = new EmailBO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioModel usuario = new UsuarioModel(email, senha);

        try {
            if (dao.validarUsuario(usuario)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", email);
                String mensagem = "Login realizado com sucesso!";
                try {
                    bo.enviarEmail(email, "Login realizado", mensagem);
                } catch (EmailException e) {
                    System.err.println("Erro ao enviar email de confirmação: " + e.getMessage());
                }
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("erro", "Usuário e/ou senha inválidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao fazer login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
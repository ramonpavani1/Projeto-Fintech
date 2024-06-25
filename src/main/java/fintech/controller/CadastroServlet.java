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

@WebServlet("/registro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;
    private EmailBO bo;

    public CadastroServlet() {
        dao = DAOFactory.getUsuarioDAO();
        bo = new EmailBO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioModel usuario = new UsuarioModel();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try {
            if (dao.usuarioExiste(email)) {
                request.setAttribute("erro", "Usuário já cadastrado!");
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                return;
            }

            dao.cadastrarUsuario(usuario);
            String mensagem = "Seu Cadastro foi realizado";
            bo.enviarEmail(email, "Cadastro Realizado", mensagem);

            response.sendRedirect("login.jsp"); 
        } catch (EmailException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao enviar email de confirmação.");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar usuário.");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("cadastro.jsp").forward(request, response);
    }
}
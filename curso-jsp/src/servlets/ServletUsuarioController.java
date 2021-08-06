package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
   
    public ServletUsuarioController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.deletarUser(idUser);
				
				//listarUsers - Inicio
				List<ModelLogin> modelLogins = daoUsuarioRepository.ConsultaUsersList();
				request.setAttribute("modelLogins", modelLogins);
				// listarUsers - Fim
				
				request.setAttribute("msg", "Ecluído com Sucesso");
				
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.deletarUser(idUser);
				
				response.getWriter().write("Excluído com Sucesso");
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.ConsultaUsuarioList(nomeBusca);
				
				// Json - converter uma lista em json
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				//Json - converter uma lista em json - end
				
				response.getWriter().write(json);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioId(id);
				
				//listarUsers - Inicio
				List<ModelLogin> modelLogins = daoUsuarioRepository.ConsultaUsersList();
				request.setAttribute("modelLogins", modelLogins);
				// listarUsers - Fim
				
				request.setAttribute("msg", "Usuário em Edição");
				request.setAttribute("modelLogin", modelLogin);
				RequestDispatcher redirecionar= request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
				
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsers")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.ConsultaUsersList();
				
				request.setAttribute("msg", "Usuários carregados!");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
				
				
			}else {
			
			//listarUsers - Inicio
			List<ModelLogin> modelLogins = daoUsuarioRepository.ConsultaUsersList();
			request.setAttribute("modelLogins", modelLogins);
			// listarUsers - Fim	
				
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
			}
			
		}catch (Exception e) {
		
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
			
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
		if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			request.setAttribute("msg", "Login já Existe! Tente Outro!");
		}else {
			
			if(modelLogin.isNovo()) {
				request.setAttribute("msg", "Cadastro Realizado Com Sucesso");
				
			}else {
				request.setAttribute("msg", "Cadastro Atualizado Com Sucesso");			
			}
			
			// salvar Banco de Dados - Inicio
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);	
			// salvar Banco de Dados - Fim
						
		}
		
		//listarUsers - Inicio
		List<ModelLogin> modelLogins = daoUsuarioRepository.ConsultaUsersList();
		request.setAttribute("modelLogins", modelLogins);
		// listarUsers - Fim
		
		
		RequestDispatcher redirecionar= request.getRequestDispatcher("principal/usuario.jsp");
		request.setAttribute("modelLogin", modelLogin);
		redirecionar.forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}

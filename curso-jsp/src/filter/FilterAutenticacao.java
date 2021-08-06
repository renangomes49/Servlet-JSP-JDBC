package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = {"/principal/*"}) // Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	private static Connection connection;
	
    public FilterAutenticacao() {
        
    }
    
	/*Intercepta as requisições e respostas do sistema*/
	/*Tudo o que foi feito no sistema será feito por aqui*/
	/*Exemplo: Validação de autenticação*/
	/*Commit e rollback de transações no banco de dados*/
	/*Validar e fazer redirecionamento de páginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;		
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath(); // URL que está sendo acessada
			
			/*Verificar se está logado, senão redirecionar para a tela de login*/
			if(usuarioLogado == null && 
					!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { // não está logado
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor, realize o login!");
				redirecionar.forward(request, response);
				return; /* Para a execução e redireciona para o login*/
				
			}else {

				chain.doFilter(request, response);
				
			}
		
			connection.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}		
		
	}

	/*Inicia os processos ou recursos quando o servidor disponibiliza o projeto*/
	/*Exemplo de processo: Iniciar a conexao com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {

		connection = SingleConnectionBanco.getConnection();
	
	}
	
    /*Encerra os processos quando o servidor é parado*/
    /*Exemplo: matar processo de conexao com o banco*/
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}

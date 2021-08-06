package connection;
	
import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "980704";
	private static Connection connection = null;
	
	
	/*retornar a conex�o com o Banco*/
	public static Connection getConnection() {
		return connection;
	}
	
	static { /*qunado chamar a classe diretamente, tamb�m haver� uma conex�o*/
		conectar();
	}
	
	public SingleConnectionBanco() { /*quando tiver uma instancia vai conectar*/
		conectar();
	}
	
	/*conex�o com o Banco de Dados*/
	private static void conectar() {
		
		
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carregar o driver de conex�o do Banco de Dados*/
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /*para n�o efetuar altera��es no banco sem o nosso comando*/
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}
	
	
}

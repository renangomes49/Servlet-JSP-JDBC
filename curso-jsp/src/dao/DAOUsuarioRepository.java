package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.ant.StartTask;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin modelLogin) throws Exception{
		
		if(modelLogin.isNovo()) {
		
			String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			
			statement.execute();
		
		}else {
			
			String sql = "update model_login set login = ?, senha = ?, nome = ?, email = ? where id = " + modelLogin.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			
			statement.executeUpdate();
		}
		
			connection.commit();
		
		return this.consultarUsuario(modelLogin.getLogin());
	}
	
	public List<ModelLogin> ConsultaUsuarioList(String nome)throws Exception{
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?); ";
		
		PreparedStatement statement = connection.prepareStatement(sql);		
		
		statement.setString(1, "%" + nome + "%");
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setId(resultSet.getLong("id"));
			
			lista.add(modelLogin);
		}
		
		
		
		return lista;
	}
	
	
public List<ModelLogin> ConsultaUsersList()throws Exception{
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login";
		
		PreparedStatement statement = connection.prepareStatement(sql);		

		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setId(resultSet.getLong("id"));
			
			lista.add(modelLogin);
		}
		
		
		
		return lista;
	}
	
	public ModelLogin consultarUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, login);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
		}
		
		
		return modelLogin;
	}
	
	public ModelLogin consultarUsuarioId(String id) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(id));
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
		}
		
		
		return modelLogin;
	}
	
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return resultSet.getBoolean("existe");
		}
		return false;
	}
	
	public void deletarUser(String idUser) throws Exception{
		
		String sql = "DELETE FROM model_login WHERE id = ?; ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idUser));
		
		statement.executeUpdate(); 
		
		connection.commit();
		
	}
}















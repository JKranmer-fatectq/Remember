package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;

public class UsuarioDAO {
	private Conexao c;
	private Connection con;
	
	public UsuarioDAO() {
		c = new Conexao();
		con = c.getConexao();
	}
	
	public String salvar(Usuario u) {
		String sql = "";
		
		if(u.getCodigo() > 0) {
			sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE codigo = ?";
		}else {
			sql = "INSERT INTO usuario(nome, email, senha) VALUES(?,?,?)"; 
		}
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getNome());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getSenha());
			if(u.getCodigo() > 0)
				ps.setInt(4, u.getCodigo());
			ps.executeUpdate();
			ps.close();
			return "Usuario gravado!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro: "+ e.getMessage();
		}
	}
	
	public List<Usuario> listarTodos(){
		List<Usuario> listaUsuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("codigo"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				listaUsuarios.add(u);
			}
			ps.close();
			return listaUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Usuario> listarNome(String nome) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuario WHERE nome like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setCodigo(rs.getInt("codigo"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				listaUsuarios.add(u);
			}
			ps.close();
			return listaUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String excluir(int codigo) {
		String sql = " DELETE FROM usuario WHERE codigo = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.executeUpdate();
			ps.close();
			return "Usuário excluido";
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}

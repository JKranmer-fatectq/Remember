package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private String url = "jdbc:mysql://localhost/dbusuario";
	private String usuario = "root";
	private String senha = "";
	private String driver = "com.mysql.jdbc.Driver";
	private Connection conexao;
	
	public Conexao() {
			try {
				Class.forName(driver);
				conexao = DriverManager.getConnection(url, usuario, senha);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}

		public Connection getConexao() {
			return conexao;
		}
		
	}

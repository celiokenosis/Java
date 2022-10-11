package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		/* Inserção no Banco */
		try {
			/* O id está sendo gerado automaticamente pela sequencia no banco */
			String sql = "insert into userposjava (nome,email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());

			/* Insere os dados no banco */
			insert.execute();

			/* Salva os dados no banco */
			connection.commit();

		} catch (SQLException e) {
			/* Faz rollback quando der pau */
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {
			String sql = "insert into telefoneuser(numero, tipo, usuariopessoa) values (?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();

		} catch (Exception e) {

			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/* Consulta ao Banco - Gerar Lista */
	public List<Userposjava> listar() throws Exception {

		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();/* Executa a query no banco */

		while (resultado.next()) {

			Userposjava userposjava = new Userposjava();

			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			/* Adiciona na lista */
			list.add(userposjava);
		}

		return list;
	}

	/* Consulta ao Banco - Busca por ID */
	public Userposjava buscar(Long id) throws Exception {

		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();/* Executa a query no banco */

		while (resultado.next()) {/* retorna apenas 1 ou nenhum */

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {

		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

		String sql = "select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();/* Executa a query no banco */
			
			while(resultado.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultado.getString("email"));
				userFone.setNome(resultado.getString("nome"));
				userFone.setNumero(resultado.getString("numero"));

				/*adiciona na lista*/
				beanUserFones.add(userFone);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanUserFones;
	}

	/* Atualiza dados no banco */
	public void atualizar(Userposjava userposjava) {

		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/* Deletar dados no banco */
	public void deletar(Long id) {
		try {

			String sql = "delete from userposjava where id = " + id;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/* Atualizar dados no banco */
	@Test
	public void initAtualizar() {

		try {

			UserPosDAO dao = new UserPosDAO();
			Userposjava objetoBanco = dao.buscar(5L);

			objetoBanco.setNome("Nome mudado com metodo atualizar");

			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*Apaga os dados da tabela pai e filha*/
	public void deleteFonesPorUser(Long idUser) {
		try {
			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;
			
			PreparedStatement preparedstatement = connection.prepareStatement(sqlFone);
			preparedstatement.executeUpdate();
			connection.commit();
			
			preparedstatement = connection.prepareStatement(sqlUser);
			preparedstatement.executeUpdate();
			connection.commit();
			
		}catch (Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

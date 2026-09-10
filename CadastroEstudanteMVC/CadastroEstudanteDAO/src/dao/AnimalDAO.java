package dao;
import modelo.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AnimalDAO {
	public List<Animal> listar() throws SQLException {
		String sql = "SELECT id, nome, curso, nota FROM estudante ORDER BY nota DESC";
		List<Animal> lista = new ArrayList<>();
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

		while (rs.next()) {
		lista.add(new Animal(
		rs.getInt("id"),
		rs.getString("nome"),
		rs.getString("especie"),
		rs.getDouble("tamanho"),
		rs.getDouble("peso"),
		rs.getString("nascimento"),
		rs.getString("dieta")
				));
		}
		}
		return lista;
		}public List<Animal> buscarPorNome(String trecho) throws SQLException {
			String sql = "SELECT id, nome, curso, nota FROM estudante "
					+ "WHERE nome LIKE ? ORDER BY nota DESC";
					List<Animal> lista = new ArrayList<>();
					try (Connection con = Conexao.abrir();
					PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, "%" + trecho + "%");
					try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
					lista.add(new Animal(
							rs.getInt("id"),
							rs.getString("nome"),
							rs.getString("especie"),
							rs.getDouble("tamanho"),
							rs.getDouble("peso"),
							rs.getString("nascimento"),
							rs.getString("dieta")
							));
					}
					}
					}
					return lista;
					}public void inserir(Animal e) throws SQLException {
						String sql = "INSERT INTO estudante (nome, curso, nota) VALUES (?, ?, ?)";
						try (Connection con = Conexao.abrir();
						PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
						ps.setString(1, e.getNome());
						ps.setString(2, e.getEspecie());
						ps.setDouble(3, e.getTamanho());
						ps.setDouble(4, e.getPeso());
						ps.setString(5, e.getNascimento());
						ps.setString(6, e.getDieta());
						ps.executeUpdate();

						try (ResultSet chaves = ps.getGeneratedKeys()) {
						if (chaves.next()) {
						e.setId(chaves.getInt(1));
						}
						}
						}
						}public boolean alterar(Animal e) throws SQLException {
							String sql = "UPDATE animal SET nome = ?, especie = ?, tamanho = ?, peso = ?, nascimento = ?, dieta = ? WHERE id = ?";
							try (Connection con = Conexao.abrir();
							PreparedStatement ps = con.prepareStatement(sql)) {
							ps.setString(1, e.getNome());
							ps.setString(2, e.getEspecie());
							ps.setDouble(3, e.getTamanho());
							ps.setDouble(4, e.getPeso());
							ps.setString(5, e.getNascimento());
							ps.setString(6, e.getDieta());
							ps.setInt(7, e.getId()); 

							return ps.executeUpdate() > 0;

							}
							}
							public boolean excluir(int id) throws SQLException {
							String sql = "DELETE FROM animal WHERE id = ?";
							try (Connection con = Conexao.abrir();
							PreparedStatement ps = con.prepareStatement(sql)) {
							ps.setInt(1, id);

							return ps.executeUpdate() > 0;

							}
							}
							}

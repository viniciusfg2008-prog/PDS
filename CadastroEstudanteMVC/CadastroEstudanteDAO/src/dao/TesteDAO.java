package dao;
import modelo.Animal;
import java.sql.SQLException;
import java.util.List;
public class TesteDAO {
public static void main(String[] args) throws SQLException {
AnimalDAO dao = new AnimalDAO();
System.out.println("== 1. listar() ==");
for (Animal e : dao.listar()) System.out.println(" " + e);
System.out.println("== 2. inserir() ==");
Animal novo = new Animal("Diego Alves", "ADS", 8.0);
System.out.println(" antes : " + novo);
dao.inserir(novo);
System.out.println(" depois : " + novo);
System.out.println("== 3. alterar() ==");
novo.setNota(9.5);
System.out.println(" alterou alguma linha: " + dao.alterar(novo));
System.out.println("== 4. buscarPorNome(\"li\") ==");
List<Animal> achados = dao.buscarPorNome("li");
System.out.println(" encontrados: " + achados.size());
for (Animal e : achados) System.out.println(" " + e);
System.out.println("== 5. excluir() ==");
System.out.println(" excluiu o id " + novo.getId() + ": " + dao.excluir(novo.getId()));
System.out.println(" total no banco agora: " + dao.listar().size());
}
}
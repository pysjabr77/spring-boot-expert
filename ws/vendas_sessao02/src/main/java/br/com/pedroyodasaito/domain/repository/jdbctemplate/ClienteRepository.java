package br.com.pedroyodasaito.domain.repository.jdbctemplate;

import br.com.pedroyodasaito.domain.entity.jpa.ClienteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ClienteRepository {

    private static String INSERIR = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String SELECT_POR_NOME = SELECT_ALL + " WHERE NOME LIKE ?";
    private static String SELECT_POR_ID = SELECT_ALL + " WHERE ID = ?";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClienteJPA salvar(ClienteJPA clienteJPA) {
        int id = jdbcTemplate.update(INSERIR, clienteJPA.getNome());
        clienteJPA.setId(id);
        return clienteJPA;
    }

    public List<ClienteJPA> obter(Integer id) {
        return jdbcTemplate.query(SELECT_POR_ID, new Object[] {id} ,(ResultSet resultSet, int i) ->
                new ClienteJPA(resultSet.getInt("id"), resultSet.getString("nome")));
    }

    public List<ClienteJPA> listar() {
        return jdbcTemplate.query(SELECT_ALL, (ResultSet resultSet, int i) ->
                new ClienteJPA(resultSet.getInt("id"), resultSet.getString("nome")));
    }

    public List<ClienteJPA> listarPorNome(String nome) {
        return jdbcTemplate.query(SELECT_POR_NOME, new Object[] {"%"+nome+"%"},(ResultSet resultSet, int i) ->
                new ClienteJPA(resultSet.getInt("id"), resultSet.getString("nome")));
    }

    public ClienteJPA atualizar(ClienteJPA clienteJPA) {
        int id = jdbcTemplate.update(UPDATE, clienteJPA.getNome(), clienteJPA.getId());
        clienteJPA.setId(id);
        return clienteJPA;
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

}

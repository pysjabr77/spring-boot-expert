package br.com.pedroyodasaito.domain.repository.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteJPAInterfaceRepository extends JpaRepository<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository, Integer> {

    //==================================================================================================================
    //Query Methods

    //Select c from Cliente where c.nome like :nome
    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> findByNomeLike(String nome);

    br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository findOneByNome(String nome);

    boolean existsByNome(String nome);

    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> findByNomeOrId(String nome, Integer id);

    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> findByNomeLikeOrId(String nome, Integer id);

    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> findByNomeLikeOrIdOrderById(String nome, Integer id);

    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> findByNomeLikeOrIdOrderByNome(String nome, Integer id);

    //==================================================================================================================

    //==================================================================================================================
    //@Query

    //Com JPQL
    @Query(value = "SELECT c from ClienteJPAInterfaceRepository c where c.nome like :nome")
    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> buscarPorNome(@Param("nome") String nome);

    @Modifying
    @Query(value = "delete from ClienteJPAInterfaceRepository c where c.nome = :nome")
    void deletarPorNome(@Param("nome") String nome);

    //----------------------------------------------------------------------------------

    //Com Sql nativa
    @Query(value = "SELECT * from cliente c where c.nome like '%:nome%'", nativeQuery = true)
    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> buscarPorNomeNativo1(@Param("nome") String nome);

    //Com Sql nativa
    @Query(value = "SELECT * from cliente c where c.nome like :nome", nativeQuery = true)
    List<br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository> buscarPorNomeNativo2(@Param("nome") String nome);

    //==================================================================================================================



    @Query("select c from ClienteJPAInterfaceRepository c left join fetch c.pedidos where c.id = :id")
    br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository findClienteFetchPedidos(@Param("id") Integer id);

}

package com.checkapp.dao; //chamado pelo padrão do spring boot de repositorio

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkapp.entidade.Inspecao;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InspecaoRepositorio extends JpaRepository<Inspecao, Long>{
       
//    @Query("select i from Inspecao i Where i.nome like %:nome%")
//    List<Inspecao> findByNome(String nome);
    
    @Query("select i from Inspecao i join fetch i.empreendimento Where i.nome like %:nome%")
    List<Inspecao> findByNome(String nome);
    
//    @Query("select i from Inspecao i join fetch i.avaliacao Where i.resposta = :resposta")
//    List<Inspecao> findByResposta(String resposta);
    
    @Query("select distinct(i) from Inspecao i join fetch i.avaliacoes")
    List<Inspecao> pesquisarAvaliacaoPorInspecao();
    
//    @Query("select distinct(c) from Categoria c join fetch c.itens")
//    List<Categoria>pesquisarCategoriaPorItem();
    
//    @Query("select i from Inspecao i join fetch i.avaliacao a join fetch a.item Where a.nome like %:nome%")
//    List<Inspecao> pesquisarItens(String nome);
    
//    @Query("select new list(inspecao, avaliacao, item, mate.nome) "
//            + "from Inspecao as inspecao "
//            + "inner join inspecao.mate as mate
    
//    select new list(mother, offspr, mate.name)
//    from DomesticCat as mother
//    inner join mother.mate as mate
//    left outer join mother.kittens as offspr    
    
//    @Query("select i from Inspecao i "
//            + "join fetch i.inspecao_categoria "
//            + "join fetch i.categoria c Where c.nome like %:nome%")
//    List<Inspecao> findByNomeCategoria(String nome);
// 
//    @Query("select i from Inspecao i join fetch i.lugar l Where i.nome like %:nome%")
//    List<Inspecao> findByNome(String nome);
//    
    
//    @Query("select i from Inspecao i join fetch i.inspecao_categoria ic Where ic.nome like %:nome%")
//    List<Inspecao> findByCategoria(String nome);
//    
    //@Query("select i from Inspecao i join fetch i.inspecao_categoria ic join fetch ic.categoria c Where c.nome like %:nome%")
    
}

package com.checkapp.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "inspecao")
public class Inspecao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataEhora;
    
    private String observacao;
    
    private String nome;

    @ManyToOne()
    @JoinColumn(name="id_empreendimento")
    private Empreendimento empreendimento;
    
    @ManyToOne()
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
//    @ManyToMany
//    @JoinTable(name = "inspecao_categoria",
//            joinColumns = @JoinColumn(name = "inspecao_codigo"),
//              addColumn = @Addcolumn(name= "resposta"),
//            inverseJoinColumns = @JoinColumn(name = "categoria_codigo"))
//    private Set<Categoria> categorias = new HashSet<>();
    
    //descomentar depois
    
    @OneToMany(mappedBy = "inspecao") 
    private List<Avaliacao> avaliacoes;
    
//    @ManyToMany
//    @JoinTable(name = "inspecao_item",
//            joinColumns = @JoinColumn(name = "inspecao_codigo"),
//            inverseJoinColumns = @JoinColumn(name = "item_codigo"))
//    @Column(name = "resposta")
//    private Set<Item> itens = new HashSet<>();
    
    
//    @OneToOne 
//    @JoinColumn(name="id_usuario")
//    private Usuario usuario;

    public Inspecao() {
    }

    public Inspecao(Date dataEhora, String observacao, String nome) {
        this.dataEhora = dataEhora;
        this.observacao = observacao;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEhora() {
        return dataEhora;
    }

    public void setDataEhora(Date dataEhora) {
        this.dataEhora = dataEhora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
//    public Set<Item> getItens() {
//        return itens;
//    }
//
//    public void setItens(Set<Item> itens) {
//        this.itens = itens;
//    }
   
//    public List<Avaliacao> getAvaliacoes() {
//        return avaliacoes;
//    }
//
//    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//        this.avaliacoes = avaliacoes;
//    }
    
    
//    public Set<Categoria> getCategorias() {
//        return categorias;
//    }
//
//    public void setCategorias(Set<Categoria> categorias) {
//        this.categorias = categorias;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   

//   public Avaliacao getAvaliacao() {
//        return avaliacao;
//    }
//
//   public void setAvaliacao(Avaliacao avaliacao) {
//        this.avaliacao = avaliacao;
//    }
//
//    public Usuario getUsuario() {
//       return usuario;
//    }
//
//   public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//   }
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Inspecao other = (Inspecao) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}

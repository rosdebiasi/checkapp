package com.checkapp.entidade;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
//    private boolean sim;
//    private boolean nao;
//    private boolean naoSeAplica;
    
//    private Integer numero;
 
    //desmarcar depois- para teste inicial
    
    @ManyToOne 
    @JoinColumn (name = "id_categoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn (name = "id_avaliacao")
    private Avaliacao avaliacao;
    
    //antigo - sugestão professor - para forçar, mas deu problema no salvar item
    //private Avaliacao avaliacao = new Avaliacao();

    public Item() {
    }

    public Item(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
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
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
 
    //    public Integer getNumero() {
//        return numero;
//    }
//
//    public void setNumero(Integer numero) {
//        this.numero = numero;
//    }
//    
//    public boolean isSim() {
//        return sim;
//    }
//
//    public void setSim(boolean sim) {
//        this.sim = sim;
//    }
//
//    public boolean isNao() {
//        return nao;
//    }
//
//    public void setNao(boolean nao) {
//        this.nao = nao;
//    }
//
//    public boolean isNaoSeAplica() {
//        return naoSeAplica;
//    }
//
//    public void setNaoSeAplica(boolean naoSeAplica) {
//        this.naoSeAplica = naoSeAplica;
//    }
    

//    public String getObservacao() {
//        return observacao;
//    }
//
//    public void setObservacao(String observacao) {
//        this.observacao = observacao;
//    }

//    public Local getLocal() {
//        return local;
//    }
//
//    public void setLocal(Local local) {
//        this.local = local;
//    }

}

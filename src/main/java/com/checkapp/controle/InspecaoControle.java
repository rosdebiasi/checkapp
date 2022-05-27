/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkapp.controle;

//import com.checkapp.dao.AvaliacaoRepositorio;
import com.checkapp.dao.CategoriaRepositorio;
import com.checkapp.dao.InspecaoRepositorio;
import com.checkapp.dao.ItemRepositorio;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Empreendimento;
import java.util.List;
import java.util.Optional;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import com.checkapp.entidade.Categoria;
import com.checkapp.entidade.Inspecao;
import com.checkapp.entidade.Item;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import com.checkapp.dao.EmpreendimentoRepositorio;
//import com.checkapp.entidade.Avaliacao;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

/**
 *
 * @author JavaRevolutions
 */

@Component(value = "inspecaoC")
@Scope("view")
public class InspecaoControle implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.logger(getClass());

    //private Avaliacao avaliacao;
    private Inspecao inspecao;
    private DataModel<Inspecao> modelInspecoes;
    private int aba;

    private Item item;
    private Empreendimento lugar;
    private Categoria categoria;

    private List<Categoria> categorias;

    //private List<Avaliacao> avaliacoes;

    private List<Item> itens;

    private List<Item> temp_itens;

    private List<String> avaliac;

    private List<SelectItem> comboLugar;

    private List<Categoria> listaDeCategoria;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private EmpreendimentoRepositorio lugarRepositorio;

    @Autowired
    private InspecaoRepositorio inspecaoRepositorio;

    //@Autowired
    //private AvaliacaoRepositorio avaliacaoRepositorio;

    //para pesquisar no banco antes de carregar a tela- como um construtor de uma classe de Entidade, mas tem em todas as classes
    @PostConstruct
    public void iniciar() {
        carregarComboBoxLugar();
        listaDeCategoria = categoriaRepositorio.pesquisarCategoriaPorItem();
        //para teste
//        Avaliacao avaliacao;
//         for (Categoria categoria1 : listaDeCategoria) {
//             System.out.println("id: " + categoria1.getId());
//             for (Item iten : categoria1.getItens()) {
//                 //System.out.println("nome: " + iten.getNome());
//                avaliacao = new Avaliacao();
//                 iten.setAvaliacao(avaliacao);
//                 System.out.println("resposta: " + iten.getAvaliacao().getResposta());
//             }
//        }
    }

    public List<Inspecao> pesquisarTodo() {
        return inspecaoRepositorio.findAll();
    }

    public void pesquisarPorNome() {
        List<Inspecao> inspecoes = inspecaoRepositorio.findByNome(inspecao.getNome());
        modelInspecoes = new ListDataModel<>(inspecoes);
        inspecao.setNome(null);
    }

    //adicionado --será que funciona???
    public void pesquisarPorId() {
        Optional<Inspecao> inspecoes = inspecaoRepositorio.findById(inspecao.getId());
    }

    private void carregarComboBoxLugar() {
        List<Empreendimento> lugares = lugarRepositorio.findAll();
        comboLugar = new ArrayList<>();
        for (Empreendimento lug : lugares) {
            comboLugar.add(new SelectItem(lug.getId(), lug.getNome()));
        }
    }

    private void carregarListaItem() {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        listaDeCategoria = new ArrayList<>();
        for (Categoria it : categorias) {
            listaDeCategoria.add(it);
        }
    }

//    public List<Item> getListaItemCategoria(String nome) {
//        List<Item> itens = itemRepositorio.findByNomeCategoria(nome);
//        for (Item iten : itens) {
//            System.out.println("X X X X X " + iten.getAvaliacao().getResposta());
//        }
//        System.out.println("");
//        return itens;
//    }

    public List<String> getAvaliac() {
        return avaliac;
    }

    public void setAvaliac(List<String> avaliac) {
        this.avaliac = avaliac;
    }
    
    public void trocarDeTela(){
        try {
            String url = "http://localhost:8080/CheckApp/novo_2.jr";
        } catch (Exception e) {
             System.out.println("Erro ao trocar de tela " + e.getMessage());
        }
    }

    public void salvar() {
        
        //logger.info("método - salvar()");
        try {
            inspecao.setEmpreendimento(lugar);
            //inspecao.setDataEhora(LocalDate.now());
//            listaDeCategoria = categoriaRepositorio.pesquisarCategoriaPorItem();
            //avaliacao.setItens(temp_itens); 
//            inspecao.setAvaliacoes(avaliacoes); 
            
            inspecaoRepositorio.save(inspecao);
            Mensagem.mensagemSucesso(inspecao.getNome());
            inspecao = null;
            lugar = null;
            temp_itens = null;
            //categorias = null;
            modelInspecoes = null;
            aba = 0;
        } catch (Exception e) {
            System.out.println("Error ao salvar " + e.getMessage());
            Mensagem.mensagemErro(inspecao.getNome());
        }
    }

//    public void excluir() {
//        try {
//            inspecao = modelInspecoes.getRowData();
//            inspecaoRepositorio.delete(inspecao);
//            Mensagem.mensagemSucessoExcluir(inspecao.getNome());
//            inspecao = null;
//            lugar = null;
//            categorias = null;
//            modelInspecoes = null;
//        } catch (Exception e) {
//            Mensagem.mensagemErroExcluir(inspecao.getNome());
//        }
//    }

//    public void prepararAlterar() {
//        inspecao = modelInspecoes.getRowData();
//        lugar = inspecao.getEmpreendimento();
//        //categorias = (List<Categoria>) inspecao.getCategorias();
//        modelInspecoes = null;
//        aba = 1;
//    }

//    public void adicionarItem(long id) {
//        try {
//            System.out.println(id);
//            Item temp_item = itemRepositorio.getById(id);
//            temp_itens.add(temp_item);
//
//        } catch (Exception e) {
//            Mensagem.mensagemErro("não foi possivel salvar");
//        }
//    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
            if (comboLugar == null) {
                carregarComboBoxLugar();
            }
            if (listaDeCategoria == null) {
                carregarListaItem();
            }
        }
    }
    public void onTabClose(TabCloseEvent event) {
    }

//    getters e setters
//    public Avaliacao getAvaliacao() {
//        return avaliacao;
//    }
//
//    public void setAvaliacao(Avaliacao avaliacao) {
//        this.avaliacao = avaliacao;
//    }
//
//    public List<Avaliacao> getAvaliacoes() {
//        return avaliacoes;
//    }
//
//    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//        this.avaliacoes = avaliacoes;
//    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Categoria> getListaDeCategoria() {
        return listaDeCategoria;
    }

    public void setListaDeCategoria(List<Categoria> listaDeCategoria) {
        this.listaDeCategoria = listaDeCategoria;
    }

    public List<SelectItem> getComboLugar() {
        return comboLugar;
    }

    public void setComboLugar(List<SelectItem> comboLugar) {
        this.comboLugar = comboLugar;
    }

    public Inspecao getInspecao() {
        if (inspecao == null) {
            inspecao = new Inspecao();
        }
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public DataModel<Inspecao> getModelInspecoes() {
        return modelInspecoes;
    }

    public void setModelInspecoes(DataModel<Inspecao> modelInspecoes) {
        this.modelInspecoes = modelInspecoes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public InspecaoRepositorio getInspecaoRepositorio() {
        return inspecaoRepositorio;
    }

    public void setInspecaoRepositorio(InspecaoRepositorio inspecaoRepositorio) {
        this.inspecaoRepositorio = inspecaoRepositorio;
    }

    public Empreendimento getLugar() {
        if (lugar == null) {
            lugar = new Empreendimento();
        }
        return lugar;
    }

    public void setLugar(Empreendimento lugar) {
        this.lugar = lugar;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
    
    

//comentados    
//    public List<SelectItem> getComboItem() {
//        return comboItem;
//    }
//
//    public void setComboItem(List<SelectItem> comboItem) {
//        this.comboItem = comboItem;
//    }
//    private void carregarListaItem2(){
//        List<Item> itens = itemRepositorio.findAll();
//        comboItem = new ArrayList<>();
//        itens.add(item);
//        for (Item it : itens) {
//            comboItem.getClass();
//        }
//    }
//    @PostConstruct
//    public void init(){
//        List<Avaliacao> avaliacoes = null;
//        avaliacoes.toString().compareTo("sim");
//        avaliacoes.toString().compareTo("nao");
//        avaliacoes.toString().compareTo("N/A");
//        avaliac = new ArrayList<String>();
//        avaliac.add("Sim");
//        avaliac.add("Não");
//        avaliac.add("N/A");
//    }
    
//    private void carregarComboBoxItem() {  
//        inspecao.getAvaliacoes();
//        Avaliacao ava = new Avaliacao();
//        ava.getItens();
//        List<Item> itens = itemRepositorio.findAll();
//        comboItem = new ArrayList<>();
//        for (Item iti : itens) {
//            comboItem.add(new SelectItem(iti.getId(), iti.getNome()));
//        }
//    }
//}

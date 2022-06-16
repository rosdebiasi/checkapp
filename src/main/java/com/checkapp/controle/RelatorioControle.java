package com.checkapp.controle;

import com.checkapp.dao.InspecaoRepositorio;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkapp.entidade.Empreendimento;
import java.util.List;
import javax.faces.model.DataModel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import com.checkapp.entidade.Inspecao;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import com.checkapp.dao.EmpreendimentoRepositorio;
import javax.annotation.PostConstruct;
import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.data.domain.Sort;

@Component(value = "relatorioC")
@Scope("view")
public class RelatorioControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Inspecao inspecaoSelecionada;
    private List<Inspecao> inspecoes;

    public EmpreendimentoRepositorio getLugarRepositorio() {
        return lugarRepositorio;
    }

    public void setLugarRepositorio(EmpreendimentoRepositorio lugarRepositorio) {
        this.lugarRepositorio = lugarRepositorio;
    }
    private DataModel<Inspecao> modelInspecoes;
    private int aba;

    private Empreendimento empreendimento;

    private List<SelectItem> comboEmpreendimentos;

    @Autowired
    private EmpreendimentoRepositorio lugarRepositorio;

    @Autowired
    private InspecaoRepositorio inspecaoRepositorio;

    @PostConstruct
    public void iniciar() {
        aba = 0;
        inspecaoSelecionada = new Inspecao();
        //inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
        empreendimento = new Empreendimento();
        carregarComboBoxEmpreendimentos();
    }

    public List<Inspecao> pesquisarTodo() {
        return inspecaoRepositorio.findAll();
    }

    public void pesquisarPorEmpreeendimento() {
        aba = 0;
        //carregarComboBoxEmpreendimentos();
        //inspecaoSelecionada = new Inspecao();
        inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "empreendimento.nome"));
             
        empreendimento = new Empreendimento();
        empreendimento.getNome();
    }
    
    public void pesquisarPorFaixaDeData(){
        aba=0;
        inspecoes = inspecaoRepositorio.pesquisarInspecaoPorFaixaDeData();
        inspecaoSelecionada.setDataInicioPesquisa(inspecaoSelecionada.getDataInicioPesquisa());
        inspecaoSelecionada.setDataFinalPesquisa(inspecaoSelecionada.getDataFinalPesquisa());
        inspecaoSelecionada.getDataEhora();
        
//        empreendimento = new Empreendimento();
//        empreendimento.getNome();
    }

    public void pesquisarPorNome() {
//        List<Inspecao> inspecoes = inspecaoRepositorio.findByNome(inspecao.getNome());
//        modelInspecoes = new ListDataModel<>(inspecoes);
//        inspecao.setNome(null);
    }

    private void carregarComboBoxEmpreendimentos() {
        List<Empreendimento> lugares = lugarRepositorio.findAll();
        comboEmpreendimentos = new ArrayList<>();
        for (Empreendimento lug : lugares) {
            comboEmpreendimentos.add(new SelectItem(lug.getId(), lug.getNome()));
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
            if (comboEmpreendimentos == null) {
                carregarComboBoxEmpreendimentos();
            }
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }

    //não vai funcionar - gerar um lancamento dao - talvez outra classe para relatório 
    public void gerarRelatorio() {
//        inspecao = modelInspecoes.getRowData();
//        empreendimento = inspecao.getEmpreendimento();
//
//        for (List<Avaliacao> avaliacaoPorCategoria : listaAvaliacoesPorCategoria.values()) {
//            for (Avaliacao avaliacao : avaliacaoPorCategoria) {
//                avaliacao.getInspecao();
//                avaliacaoRepositorio.getById(serialVersionUID);
//            }
//        }
    }

    public List<SelectItem> getComboEmpreendimentos() {
        return comboEmpreendimentos;
    }

    public void setComboEmpreendimentos(List<SelectItem> comboEmpreendimentos) {
        this.comboEmpreendimentos = comboEmpreendimentos;
    }

    public Inspecao getInspecaoSelecionada() {
        return inspecaoSelecionada;
    }

    public void setInspecaoSelecionada(Inspecao inspecao) {
        this.inspecaoSelecionada = inspecao;
    }

    public List<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(List<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }

    public DataModel<Inspecao> getModelInspecoes() {
        return modelInspecoes;
    }

    public void setModelInspecoes(DataModel<Inspecao> modelInspecoes) {
        this.modelInspecoes = modelInspecoes;
    }

    public InspecaoRepositorio getInspecaoRepositorio() {
        return inspecaoRepositorio;
    }

    public void setInspecaoRepositorio(InspecaoRepositorio inspecaoRepositorio) {
        this.inspecaoRepositorio = inspecaoRepositorio;
    }

    public Empreendimento getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreencimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }
}

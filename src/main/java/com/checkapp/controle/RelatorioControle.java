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
import com.checkapp.dao.EmpreendimentoRepositorio;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

@Component(value = "relatorioC")
@Scope("request")
public class RelatorioControle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Inspecao inspecaoSelecionada;
    private List<Inspecao> inspecoes;

    private DataModel<Inspecao> modelInspecoes;
    private int aba;

    private Long pesquisaEmpreendimentoId;
    private Date pesquisaDataInicial;
    private Date pesquisaDataFinal;

    private List<Empreendimento> lugares;

    @Autowired
    private EmpreendimentoRepositorio lugarRepositorio;

    @Autowired
    private InspecaoRepositorio inspecaoRepositorio;

    private long numeroInspecoes;

    @PostConstruct
    public void iniciar() {
        aba = 0;
        inspecaoSelecionada = new Inspecao();
        pesquisaDataInicial = new Date();
        pesquisaDataFinal = new Date();
        inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
        lugares = lugarRepositorio.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        numeroInspecoes = inspecaoRepositorio.count();
    }


    public void pesquisarPorEmpreeendimento() {
        if (pesquisaEmpreendimentoId == -1) {
            this.inspecoes = inspecaoRepositorio.findAll(Sort.by(Sort.Direction.DESC, "dataEhora"));
        } else {
            this.inspecoes = inspecaoRepositorio.findByEmpreendimento(pesquisaEmpreendimentoId);
        }
    }

    public void pesquisarPorFaixaDeData() {
        if ((pesquisaDataInicial).after(pesquisaDataFinal)) {
            Mensagem.mensagemErroPesquisaData(" a data inicial deve ser menor que a data final");
        } else {
//            TemporalAmount temporal;
//            pesquisaDataInicial.toInstant().;
            inspecoes = inspecaoRepositorio.pesquisarInspecaoPorFaixaDeData(pesquisaDataInicial, pesquisaDataFinal);
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
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

    public List<Empreendimento> getLugares() {
        return lugares;
    }

    public void setLugares(List<Empreendimento> lugares) {
        this.lugares = lugares;
    }

    public Long getPesquisaEmpreendimentoId() {
        return pesquisaEmpreendimentoId;
    }

    public void setPesquisaEmpreendimentoId(Long pesquisaEmpreendimentoId) {
        this.pesquisaEmpreendimentoId = pesquisaEmpreendimentoId;
    }

    public Date getPesquisaDataInicial() {
        return pesquisaDataInicial;
    }

    public void setPesquisaDataInicial(Date pesquisaDataInicial) {
        this.pesquisaDataInicial = pesquisaDataInicial;
    }

    public Date getPesquisaDataFinal() {
        return pesquisaDataFinal;
    }

    public void setPesquisaDataFinal(Date pesquisaDataFinal) {
        this.pesquisaDataFinal = pesquisaDataFinal;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    public long getNumeroInspecoes() {
        return numeroInspecoes;
    }

    public void setNumeroInspecoes(long numeroInspecoes) {
        this.numeroInspecoes = numeroInspecoes;
    }

}

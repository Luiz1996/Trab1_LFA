/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.model;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luiz Flávio
 */
public class AutomatoModel {
    //nesta classe temos o POJO do objeto autômato(atributos private, get/set, contrutor vazio, etc...)
    private int qtdePalavras;
    private List<String> palavrasAutomato = new ArrayList();
    private int qtdeEstados;
    private List<String> estadosAutomato = new ArrayList();
    private String estadoInicial;
    private List<String> estadosFinais = new ArrayList();
    private List<TransicaoModel> transicoesAutomato = new ArrayList();
    
    public AutomatoModel(){}

    public int getQtdePalavras() {
        return qtdePalavras;
    }

    public void setQtdePalavras(int qtdePalavras) {
        this.qtdePalavras = qtdePalavras;
    }

    public List<String> getPalavrasAutomato() {
        return palavrasAutomato;
    }

    public void setPalavrasAutomato(List<String> palavrasAutomato) {
        this.palavrasAutomato = palavrasAutomato;
    }

    public int getQtdeEstados() {
        return qtdeEstados;
    }

    public void setQtdeEstados(int qtdeEstados) {
        this.qtdeEstados = qtdeEstados;
    }

    public List<String> getEstadosAutomato() {
        return estadosAutomato;
    }

    public void setEstadosAutomato(List<String> estadosAutomato) {
        this.estadosAutomato = estadosAutomato;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public List<String> getEstadosFinais() {
        return estadosFinais;
    }

    public void setEstadosFinais(List<String> estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    public List<TransicaoModel> getTransicoesAutomato() {
        return transicoesAutomato;
    }

    public void setTransicoesAutomato(List<TransicaoModel> transicoesAutomato) {
        this.transicoesAutomato = transicoesAutomato;
    }
}

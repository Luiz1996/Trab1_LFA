/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.model;

/**
 *
 * @author Luiz Flávio
 */
public class TransicaoModel {
    //nesta classe temos o POJO do objeto transição(atributos private, construtor vazio, get/set, etc...)
    private String estadoSaida;
    private String simboloTransicao;
    private String estadoChegada;
    
    public TransicaoModel(){}

    public TransicaoModel(String estadoSaida, String palavraTransicao, String estadoChegada) {
        this.estadoSaida = estadoSaida;
        this.simboloTransicao = palavraTransicao;
        this.estadoChegada = estadoChegada;
    }

    public String getEstadoSaida() {
        return estadoSaida;
    }

    public void setEstadoSaida(String estadoSaida) {
        this.estadoSaida = estadoSaida;
    }

    public String getSimboloTransicao() {
        return simboloTransicao;
    }

    public void setSimboloTransicao(String simboloTransicao) {
        this.simboloTransicao = simboloTransicao;
    }

    public String getEstadoChegada() {
        return estadoChegada;
    }

    public void setEstadoChegada(String estadoChegada) {
        this.estadoChegada = estadoChegada;
    }
}

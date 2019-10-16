/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.controller;

import br.uem.din.lfa.model.AutomatoModel;
import br.uem.din.lfa.view.ConsoleView;

/**
 *
 * @author Luiz Flávio
 */
public class MainController {
    //A classe MainController tem como único objeto controlar o fluxo do programa de acordo com a opção escolhida no menu
    //Esse controle de fluxo é feito usando a estrutura switch/case
    public static void main(String[] args) {
        AutomatoModel autM = new AutomatoModel();
        AutomatoController autC = new AutomatoController();
        autM.setEstadoInicial("".trim());

        int opcao = ConsoleView.showMenu();
        while (opcao != 0) {
            ConsoleView.limparConsole();
            switch (opcao) {
                case 1:
                    autM.setPalavrasAutomato(autC.inserirAlfabeto(autM));
                    break;
                case 2:        
                    autM.setEstadosAutomato(autC.inserirEstados(autM));
                    break;
                case 3:
                    autC.inserirEstadoInicial(autM);
                    break;
                case 4:
                    autM.setEstadosFinais(autC.inserirEstadoFinal(autM));
                    break;
                case 5:
                    autM.setTransicoesAutomato(autC.inserirTransacoes(autM));
                    break;
                case 6:
                    autC.imprimirAutomatoCompleto(autM);
                    break;
                case 7:
                    autM = new AutomatoModel();
                    autM.setEstadoInicial("".trim());
                    System.out.println("Autômato resetado com sucesso.");
                    break;
                case 8:
                    System.out.println(autC.validarPalavraNoAutomato(autM));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            System.gc();
            opcao = ConsoleView.showMenu(); 
        }
    }
}

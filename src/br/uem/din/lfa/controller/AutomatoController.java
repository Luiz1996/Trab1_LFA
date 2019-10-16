/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uem.din.lfa.controller;

import br.uem.din.lfa.model.AutomatoModel;
import br.uem.din.lfa.model.TransicaoModel;
import br.uem.din.lfa.view.ConsoleView;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Luiz Flávio
 */
public class AutomatoController {
    Scanner in = new Scanner(System.in);

    //método responsável por preencher o alfabeto do autômato
    public List<String> inserirAlfabeto(AutomatoModel autM) {
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de símbolos: ");
        int qtdeSimbolos = 0;
        
        //uso de try/catch para tratativas de exceções
        try {
            qtdeSimbolos = in.nextInt(); 
        } catch (InputMismatchException e) {
            qtdeSimbolos = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        } 
        autM.setQtdePalavras(qtdeSimbolos);
        
        //limpando tela
        ConsoleView.limparConsole();
        
        //valida se a quantidade de simbolos é válido
        if (autM.getQtdePalavras() <= 0) {
            System.out.println("Quantidade inválida de símbolos.\n\nInserção de símbolos abortada!");
        } else {
            String strPalavraTemp;
            List<String> palavrasTemp = new ArrayList();

            for (int i = 1; i <= autM.getQtdePalavras(); i++) {
                System.out.print("Informe o " + i + "º símbolo: ");
                strPalavraTemp = in.next();

                //limpa tela
                ConsoleView.limparConsole();

                if (strPalavraTemp.trim().length() == 1) { //valida se o simbolo tem tamanho exatamente = 1, apesar de ser String... Funciona como char
                    if (!simboloRepetido(strPalavraTemp.trim(), palavrasTemp)) {//valida se o simbolo está sendo inserido de forma repetida
                        palavrasTemp.add(strPalavraTemp.trim());
                    } else {
                        System.out.println("O símbolos '" + strPalavraTemp.trim() + "' já foi inserido no alfabeto.\n");
                        i--;
                    }
                } else {
                    System.out.println("O tamanho de cada símbolos deve ser exatamente 1.\n");
                    i--;
                }
            }
            System.out.println("O(s) símbolo(s) foi/foram inserido(s) com sucesso.");
            return palavrasTemp;
        }
        return new ArrayList<>();
    }
    
    //método responsável por inserir os estados desejados
    public List<String> inserirEstados(AutomatoModel autM) {
        in = new Scanner(System.in);
        System.out.print("Informe a quantidade de estados: ");
        int qtdeEstados = 0;
        
        //uso de try/catch para tratativas de exceções
        try {
            qtdeEstados = in.nextInt(); 
        } catch (InputMismatchException e) {
            qtdeEstados = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
        } 
        autM.setQtdeEstados(qtdeEstados);
        
        //limpando a tela
        ConsoleView.limparConsole();

        if (autM.getQtdeEstados() <= 0) { //valida se a quantidade de estados informado é inválido
            System.out.println("Quantidade inválida de estados.\n\nInserção de estados abortada!");
        } else {
            String strEstadoTemp;
            List<String> estadosTemp = new ArrayList();

            for (int i = 1; i <= autM.getQtdeEstados(); i++) {
                System.out.print("Informe o " + i + "º estado: ");
                strEstadoTemp = in.next();

                //limpa tela
                ConsoleView.limparConsole();

                if (strEstadoTemp.trim().length() == 1) { //valida se o estado tem tamanho exatamente = 1, apesar de ser String... Funciona como char
                    if (!estadoRepetido(strEstadoTemp.trim(), estadosTemp)) { //valida se está sendo inserido um estado repetido
                        estadosTemp.add(strEstadoTemp.trim());
                    } else {
                        System.out.println("O estado '" + strEstadoTemp.trim() + "' já foi inserido no conjunto de estados.\n");
                        i--;
                    }
                } else {
                    System.out.println("O tamanho de cada estado deve ser exatamente 1.\n");
                    i--;
                }
            }
            System.out.println("Os estados foram inseridas com sucesso.");
            return estadosTemp;
        }
        return new ArrayList<>();
    }

    //método para inserção de estado inicial
    public void inserirEstadoInicial(AutomatoModel autM) {
        //valida se os estados já foram cadastrados
        if (autM.getEstadosAutomato().size() > 0) {
            String strEstadoInicial;
            System.out.print("Insira o estado inicial: ");
            strEstadoInicial = in.next();

            //limpa tela
            ConsoleView.limparConsole();

            if (estadoRepetido(strEstadoInicial.trim(), autM.getEstadosAutomato())) { //valida se o estado inicial informado existe no conjunto de estados cadastrados
                autM.setEstadoInicial(strEstadoInicial.trim()); //seta estado inicial
                System.out.println("Estado inicial inserido com sucesso.");
            } else {
                System.out.println("O estado informado não faz parte do conjunto de estados.\n\n Inserção de estado inicial abortada!");
            }
        } else {
            System.out.println("Nenhum estado foi cadastro neste autômato.");
        }
    }

    //Método responsável por cadastrar os estados finais do autômato
    public List<String> inserirEstadoFinal(AutomatoModel autM) {
        //valida se autômato possui estados cadastrados
        if (autM.getEstadosAutomato().size() > 0) {
            in = new Scanner(System.in);
            System.out.print("Insira a quantidade de estados finais: ");
            int qtdeEstadosFinais = 0;

            //uso de try/catch para tratativas de exceções
            try {
                qtdeEstadosFinais = in.nextInt();
            } catch (InputMismatchException e) {
                qtdeEstadosFinais = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
            }

            //limpa tela
            ConsoleView.limparConsole();

            //valida se a quantidade de estados finais é condizente
            if (qtdeEstadosFinais <= 0 || qtdeEstadosFinais > autM.getEstadosAutomato().size()) {
                System.out.println("A quantidade de estados finais informados é inválido.\n\nInserção de estados finais abortada!");
                return new ArrayList<>();
            } else {
                String strEstadoTemp;
                List<String> estadosFinais = new ArrayList<>();

                for (int i = 1; i <= qtdeEstadosFinais; i++) {
                    System.out.print("Informe o " + i + "º estado final: ");
                    strEstadoTemp = in.next();

                    //limpa tela
                    ConsoleView.limparConsole();

                    if (estadoRepetido(strEstadoTemp.trim(), autM.getEstadosAutomato())) {//valida se o estado final informado existe no conjunto de estados
                        if (!estadoRepetido(strEstadoTemp.trim(), estadosFinais)) { //valida se o estado final informado não foi inserido de maneira repetida
                            estadosFinais.add(strEstadoTemp.trim());
                        } else {
                            System.out.println("O estado '" + strEstadoTemp.trim() + "' já foi informado no conjunto de estados finais.\n");
                            i--;
                        }
                    } else {
                        System.out.println("O estado '" + strEstadoTemp.trim() + "' informado não existe no conjunto de estados.\n");
                        i--;
                    }
                }
                System.out.println("Inserção de estado(s) final(is) foi realizado com sucesso.");
                return estadosFinais;
            }
        }else{
            System.out.println("Nenhum estado foi cadastro neste autômato.");
            return new ArrayList();
        }
    }

    //método responsável por inserir as transições do autômato
    public List<TransicaoModel> inserirTransacoes(AutomatoModel autM) {
        if (autM.getEstadosAutomato().size() > 0 && autM.getPalavrasAutomato().size() > 0) {
            in = new Scanner(System.in);
            System.out.print("Quantidade de transição(ões) na linguagem: ");
            int qtdeTransicoes = 0;

            //uso de try/catch para tratativas de exceções
            try {
                qtdeTransicoes = in.nextInt();
            } catch (InputMismatchException e) {
                qtdeTransicoes = -1; //situação inválida (usuário digitou qualquer coisa, exceto um valor inteiro)
            }
            
            //limpa tela
            ConsoleView.limparConsole();

            //valida se a quantidade de transições informada é permitida
            if (qtdeTransicoes <= 0 || qtdeTransicoes > (autM.getEstadosAutomato().size() * autM.getPalavrasAutomato().size())) {
                System.out.println("A quantidade de transições informada é inválida.\n\nInserção de transições abortada!");
            } else {
                List<TransicaoModel> transicoesTemp = new ArrayList<>();
                TransicaoModel transicao = new TransicaoModel();

                for (int i = 1; i <= qtdeTransicoes; i++) {
                    System.out.println("*** Obtendo dados da " + i + "ª transicao ***");
                    System.out.print("Estado de saída: ");
                    transicao.setEstadoSaida(in.next().trim());
                    System.out.print("Símbolo de transição: ");
                    transicao.setSimboloTransicao(in.next().trim());
                    System.out.print("Estado de chegada: ");
                    transicao.setEstadoChegada(in.next().trim());

                    //limpa tela após cada iteração
                    ConsoleView.limparConsole();

                    //valida se o estado de saida existe no conjunto de estados
                    if (!estadoRepetido(transicao.getEstadoSaida().trim(), autM.getEstadosAutomato())) {
                        System.out.println("O estado de saída '" + transicao.getEstadoSaida().trim() + "' informado não existe no conjunto de estados.");
                        i--;
                    } else {
                        //valida se o símbolo de transição existe no alfabeto
                        if (!simboloRepetido(transicao.getSimboloTransicao().trim(), autM.getPalavrasAutomato())) {
                            System.out.println("O símbolo de transição '" + transicao.getSimboloTransicao().trim() + "' informada não existe no conjunto de palavras.");
                            i--;
                        } else {
                            //valida se o estado de chegada existe no conjunto de estados
                            if (!estadoRepetido(transicao.getEstadoChegada().trim(), autM.getEstadosAutomato())) {
                                System.out.println("O estado de chegada '" + transicao.getEstadoChegada().trim() + "' informado não existe no conjunto de estados.");
                                i--;
                            } else {
                                //valida se a transição informada já foi cadastrada antes
                                if (transicaoRepetida(transicao, transicoesTemp)) {
                                    System.out.println("Esta transição já faz parte do conjunto de transições.");
                                    i--;
                                } else {
                                    //se não der nenhum problema a nova transição é inserida
                                    transicoesTemp.add(transicao);
                                }
                            }
                        }
                    }
                    transicao = new TransicaoModel();
                }
                System.out.println("Transição(ões) cadastrada(s) com sucesso.");
                return transicoesTemp;
            }
        } else {
            System.out.println("Conjunto de estado(s) ou símbolo(s) ainda não foi/foram informado(s).\n\nInserção de transição(ões) abortada!");
        }
        return new ArrayList<>();
    }

    //método responsável por listar a descrição formal do autômato
    public void imprimirAutomatoCompleto(AutomatoModel autM) {
        System.out.println(" -------------- DESCRIÇÃO FORMAL - AFD -------------- ");
        System.out.println("Σ: " + autM.getPalavrasAutomato().toString());
        System.out.println("E: " + autM.getEstadosAutomato().toString());
        System.out.println("i: " + autM.getEstadoInicial());
        System.out.println("F: " + autM.getEstadosFinais().toString());
        System.out.println("δ: ");
        if (autM.getTransicoesAutomato().size() <= 0) {
            System.out.println("\t*** Nenhuma transição foi cadastrada ***");
        } else {
            for (int i = 0; i < autM.getTransicoesAutomato().size(); i++) {
                System.out.println("\t(" + autM.getTransicoesAutomato().get(i).getEstadoSaida() + ") --> "
                        + autM.getTransicoesAutomato().get(i).getSimboloTransicao() + " --> ("
                        + autM.getTransicoesAutomato().get(i).getEstadoChegada() + ")");
            }
        }
    }

    //método auxiliar que valida se o símbolo informado existe no alfabeto
    private boolean simboloRepetido(String strPalavraTemp, List<String> palavrasTemp) {
        return palavrasTemp.stream().anyMatch((palavrasTemp1) -> (palavrasTemp1.equals(strPalavraTemp.trim())));
    }

    //método auxiliar que valida se o estado informado existe no conjunto de estados
    private boolean estadoRepetido(String strEstadoTemp, List<String> estadosTemp) {
        return estadosTemp.stream().anyMatch((estadosTemp1) -> (estadosTemp1.equals(strEstadoTemp.trim())));
    }

    //método auxiliar que valida se a transição informada já existe no conjunto de transições
    private boolean transicaoRepetida(TransicaoModel transicao, List<TransicaoModel> transicoesTemp) {
        return transicoesTemp.stream().anyMatch((transicoesTemp1) -> (transicoesTemp1.getEstadoSaida().trim().equals(transicao.getEstadoSaida().trim()) && transicoesTemp1.getSimboloTransicao().trim().equals(transicao.getSimboloTransicao().trim())));
    }

    //método auxiliar que valida se todas as informações da descrição formal foram devidamente informadas
    public boolean validaAutomatoTotalmentePreenchido(AutomatoModel autM) {
        return !autM.getEstadoInicial().trim().equals("")
                && autM.getPalavrasAutomato().size() > 0
                && autM.getEstadosAutomato().size() > 0
                && autM.getEstadosFinais().size() > 0
                && autM.getTransicoesAutomato().size() > 0;
    }

    //método responsável por validar uma palavra na linguagem do autômato
    //se a linguagem for aceita, então é apresentado o caminho percorrido no autômato, caso contrário retorna NÃO PERTENCE
    public String validarPalavraNoAutomato(AutomatoModel autM) {
        //valida se toda a descrição formal do autômato foi devidamente informado
        if (validaAutomatoTotalmentePreenchido(autM)) {
            System.out.print("Use *(asterisco) para representar a palavra vazia(Lâmbida).\n\nInforme a palavra a ser validada pelo autômato: ");
            String strPalavra = in.next();

            //limpa tela
            ConsoleView.limparConsole();

            TransicaoModel transicao = new TransicaoModel();
            
            //neste caso validamos a palavra vazia(lâmbida)
            if (strPalavra.trim().equals("*")) {
                transicao.setEstadoSaida(autM.getEstadoInicial().trim());
                if (validaEstadoFinal(autM, transicao)) {
                    return "A palavra 'lâmbida' PERTENCE à linguagem!";
                } else {
                    return "A palavra 'lâmbida' NÃO PERTENCE à linguagem!";
                }
            } else {
                //aqui validamos as demais palavras informadas
                StringBuilder caminhoPercorrido = new StringBuilder("".trim());
                transicao.setEstadoSaida(autM.getEstadoInicial());

                //percorrendo palavra e buscando transições para cada estados de saida e símbolo
                for (int i = 0; i < strPalavra.length(); i++) {
                    char caracterSimbolo = strPalavra.charAt(i);
                    transicao.setSimboloTransicao(Character.toString(caracterSimbolo));

                    //se não existir transição pro estado atuale pro simbolo atual, então a palavra não pertence
                    if (!existeTransicao(transicao, autM.getTransicoesAutomato(), caminhoPercorrido)) {
                        return "A palavra '" + strPalavra + "' NÃO PERTENCE à linguagem!";
                    }
                }

                //após percorrer toda a palavra, analisar se chegou a algum estado final
                if (validaEstadoFinal(autM, transicao)) {
                    return "Caminho:\n\n" + caminhoPercorrido + "\n\nA palavra '" + strPalavra + "' PERTENCE à linguagem!";
                } else {
                    return "A palavra '" + strPalavra + "' NÃO PERTENCE à linguagem!";
                }
            }
        } else {
            return "O autômato ainda não foi completamente preenchido!\n\nValidação de palavra abortada!";
        }
    }

    //valida se existe a transição ao validar uma palavra no autômato
    private boolean existeTransicao(TransicaoModel transicao, List<TransicaoModel> transicoesTemp, StringBuilder caminhoPercorrido) {
        for (TransicaoModel transicoesTemp1 : transicoesTemp) {
            if (transicoesTemp1.getEstadoSaida().trim().equals(transicao.getEstadoSaida().trim()) && transicoesTemp1.getSimboloTransicao().trim().equals(transicao.getSimboloTransicao().trim())) {
                caminhoPercorrido = caminhoPercorrido.append("(").append(transicao.getEstadoSaida().trim()).append(") --> ").append(transicao.getSimboloTransicao().trim()).append(" --> (").append(transicoesTemp1.getEstadoChegada().trim()).append(")\n");
                
               //setando no estado de saida o estado atual(estado de chegada)
                transicao.setEstadoSaida(transicoesTemp1.getEstadoChegada().trim());
                return true;
            }
        }
        return false;
    }
    
    //valida se o estado pode ou não ser final
    private boolean validaEstadoFinal(AutomatoModel autM, TransicaoModel transicao){
        for(int  i = 0; i < autM.getEstadosFinais().size(); i++){
            if(transicao.getEstadoSaida().trim().equals(autM.getEstadosFinais().get(i).trim())){
                return true;
            }
        }
        return false;
    }
}

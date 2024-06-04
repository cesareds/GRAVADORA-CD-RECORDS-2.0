package org.example;


import org.bson.types.ObjectId;
import org.example.system.Gravadora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gravadora CD Records Worldwide INC.");
        int opcao;
        do{
            menu();
            opcao = scanner.nextInt();
            switch (opcao){
                case 1:
                    inserirCriador();
                    break;
                case 2:
                    inserirDisco();
                    break;
                case 3:
                    inserirInstrumento();
                    break;
                case 4:
                    inserirMusica();
                    break;
                case 5:
                    inserirProdutor();
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    break;
                case 7:
                    mostrarCriadores();
                    break;
                case 8:
                    mostrarDiscos();
                    break;
                case 9:
                    mostrarInstrumentos();
                    break;
                case 10:
                    mostrarMusicas();
                    break;
                case 11:
                    mostrarProdutores();
                    break;
                case 12:
                    produzirDisco();
                    break;
                case 13:
                    tocar();
                    break;
                case 14:
                    participar();
                    break;
                case 15:
                    lancar();
                    break;
                case 16:
                    integrar();
                    break;
                case 17:
                    incluir();
                    break;
                case 18:
                    mostrarProducoes();
                    break;
                case 19:
                    mostrarTocadas();
                    break;
                case 20:
                    mostrarParticipacoes();
                    break;
                case 21:
                    mostrarLancamentos();
                    break;
                case 22:
                    mostrarIntegracoes();
                    break;
                case 23:
                    mostrarInclusoes();
                    break;
                default:
                    System.out.println("ESCOLHA UMA OPÇÃO VÁLIDA!!!!!!");
                    break;
            }
        }while(opcao !=6);
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner scannerS = new Scanner(System.in);
    private static final Gravadora gravadora = new Gravadora();
    public static void menu(){
        System.out.println("INSERIR CRIADOR.......01");
        System.out.println("INSERIR DISCO.........02");
        System.out.println("INSERIR INSTRUMENTO...03");
        System.out.println("INSERIR MUSICA........04");
        System.out.println("INSERIR PRODUTOR......05");
        System.out.println("SAIR..................06");
        System.out.println("MOSTRAR CRIADORES.....07");
        System.out.println("MOSTRAR DISCOS........08");
        System.out.println("MOSTRAR INSTRUMENTOS..09");
        System.out.println("MOSTRAR MÚSICAS.......10");
        System.out.println("MOSTRAR PRODUTORES....11");
        System.out.println("PRODUZIR DISCO........12");
        System.out.println("TOCAR.................13");
        System.out.println("PARTICIPAÇÃO..........14");
        System.out.println("LANÇAMENTO............15");
        System.out.println("INTEGRAR..............16");
        System.out.println("INCLUIR...............17");
        System.out.println("MOSTRAR PRODUCOES.....18");
        System.out.println("MOSTRAR TOCADAS.......19");
        System.out.println("MOSTRAR PARTICIPACOES.20");
        System.out.println("MOSTRAR LANCAMENTOS...21");
        System.out.println("MOSTRAR INTEGRACOES...22");
        System.out.println("MOSTRAR INCLUSOES.....23");
    }
    public static void inserirCriador(){
        System.out.println("QUAL O TIPO DE CRIADOR? (BANDA: 0 | MUSICO: 1)");
        int tipo = scanner.nextInt();
        System.out.print("NOME:\t");
        String nome = scannerS.nextLine();
        System.out.print("\nGÊNERO:\t");
        String genero = scannerS.nextLine();
        System.out.print("\nDESCRIÇÃO:\t");
        String descricao = scannerS.nextLine();
        if(tipo==1){
            System.out.print("\nCEP:\t");
            String cep = scannerS.nextLine();
            System.out.print("\nRUA:\t");
            String rua = scannerS.nextLine();
            System.out.print("\nCIDADE:\t");
            String cidade = scannerS.nextLine();
            System.out.print("\nESTADO:\t");
            String estado = scannerS.nextLine();
            System.out.print("\nTELEFONE:\t");
            String telefone = scannerS.nextLine();
            gravadora.musicoADD(nome, descricao, genero, cep, rua, cidade, estado, telefone);
        }else{
            System.out.print("\nDATA DE FORMAÇÃO DA BANDA:\t");
            String dataDeFormacao = scannerS.nextLine();
            gravadora.bandaADD(nome, descricao, genero, dataDeFormacao);
        }
    }
    public static void inserirDisco(){
        System.out.print("\nDATA DE LANÇAMENTO DO DISCO (dd/mm/yyyy):\t");
        String dataLancamento = scannerS.nextLine();
        System.out.print("\nPREÇO:\t");
        float preco = scanner.nextFloat();
        System.out.print("\nPLATINAS:\t");
        int platinas = scanner.nextInt();
        System.out.print("\nTÍTULO:\t");
        String titulo = scannerS.nextLine();
        System.out.print("\nFORMATO(vinil|cd|vhs):\t");
        String formato = scannerS.nextLine();
        System.out.print("\nDESCRIÇÃO:\t");
        String descricao = scannerS.nextLine();
        System.out.print("\nGÊNERO:\t");
        String genero = scannerS.nextLine();
        gravadora.discoADD(dataLancamento,preco,platinas,titulo,formato,descricao,genero);
    }
    public static void inserirInstrumento(){
        System.out.print("\nMARCA:\t");
        String marca = scannerS.nextLine();
        System.out.print("\nTIPO:\t");
        String tipo = scannerS.nextLine();
        System.out.print("\nNOME:\t");
        String nome = scannerS.nextLine();
        gravadora.instrumentoADD(marca, tipo, nome);
    }
    public static void inserirMusica(){
        System.out.print("\nDURAÇÃO:\t");
        float duracao = scanner.nextFloat();
        System.out.print("\nFAIXA:\t");
        int faixa = scanner.nextInt();
        System.out.print("\nAUTORES:\t");
        String autores = scannerS.nextLine();
        System.out.print("\nTÍTULO:\t");
        String titulo = scannerS.nextLine();
        System.out.print("\nLETRA:\t");
        String letra = scannerS.nextLine();
        gravadora.musicaADD(duracao, faixa, autores, titulo, letra);
    }
    public static void inserirProdutor(){
        System.out.print("\nNOME:\t");
        String nome = scannerS.nextLine();
        System.out.println("\nBIOGRAFIA:\t");
        String biografia = scannerS.nextLine();
        gravadora.produtorADD(nome, biografia);
    }
    public static void mostrarCriadores(){gravadora.mostrarMusicos();gravadora.mostrarBandas();}
    public static void mostrarDiscos(){gravadora.mostrarDiscos();}
    public static void mostrarInstrumentos(){gravadora.mostrarInstrumentos();}
    public static void mostrarMusicas(){gravadora.mostrarMusicas();}
    public static void mostrarProdutores(){gravadora.mostrarProdutores();}
    public static void mostrarProducoes(){gravadora.mostrarProducoes();}
    public static void mostrarTocadas(){gravadora.mostrarTocadas();}
    public static void mostrarParticipacoes(){gravadora.mostrarParticipacoes();}
    public static void mostrarLancamentos(){gravadora.mostrarLancamentos();}
    public static void mostrarIntegracoes(){gravadora.mostrarIntegracoes();}
    public static void mostrarInclusoes(){gravadora.mostrarInclusoes();}


    public static void produzirDisco() {
        System.out.print("\nIDDISCO:\t");
        String idDisco = scannerS.nextLine();
        ObjectId objectIdDisco =  new ObjectId(idDisco);
        System.out.println("\nIDPRODUTOR:\t");
        String idProdutor = scannerS.nextLine();
        ObjectId objectIdProdutor = new ObjectId(idProdutor);
        gravadora.produzir(objectIdDisco, objectIdProdutor);
    }
    public static void tocar(){
        System.out.print("\nIDINSTRUMENTO:\t");
        String idInstrumento = scannerS.nextLine();
        ObjectId objectIdInstrumento = new ObjectId(idInstrumento);
        System.out.println("\nIDMUSICO:\t");
        String idMusico = scannerS.nextLine();
        ObjectId objectIdMusico = new ObjectId(idMusico);
        gravadora.tocar(objectIdInstrumento, objectIdMusico);
    }
    public static void participar(){
        System.out.print("\nIDMUSICA:\t");
        String idMusica = scannerS.nextLine();
        ObjectId objectIdMusica = new ObjectId(idMusica);
        System.out.println("\nIDCRIADOR (MUSICO OU BANDA!!!):\t");
        String idCriador = scannerS.nextLine();
        ObjectId objectIdCriador = new ObjectId(idCriador);
        gravadora.participar(objectIdMusica, objectIdCriador);
    }
    public static void lancar(){
        System.out.print("\nIDDISCO:\t");
        String idDisco = scannerS.nextLine();
        ObjectId objectIdDisco = new ObjectId(idDisco);
        System.out.println("\nIDCRIADOR (MUSICO OU BANDA!!!):\t");
        String idCriador = scannerS.nextLine();
        ObjectId objectIdCriador = new ObjectId(idCriador);
        gravadora.lancar(objectIdDisco, objectIdCriador);
    }
    public static void integrar(){
        System.out.print("\nIDMUSICO:\t");
        String idMusico = scannerS.nextLine();
        ObjectId objectIdMusico = new ObjectId(idMusico);
        System.out.println("\nIDBANDA:\t");
        String idBanda = scannerS.nextLine();
        ObjectId objectIdBanda = new ObjectId(idBanda);
        gravadora.integrar(objectIdMusico, objectIdBanda);
    }
    public static void incluir(){
        System.out.print("\nIDMUSICA:\t");
        String idMusica = scannerS.nextLine();
        ObjectId objectIdMusica = new ObjectId(idMusica);
        System.out.println("\nIDDISCO:\t");
        String idDisco = scannerS.nextLine();
        ObjectId objectIdDisco = new ObjectId(idDisco);
        gravadora.incluir(objectIdMusica, objectIdDisco);
    }
}

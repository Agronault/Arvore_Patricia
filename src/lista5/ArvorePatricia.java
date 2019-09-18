/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista5;

/**
 *
 * @author aluno
 */
public class ArvorePatricia {

    private NoArvorePatricia raiz;
    private final int numeroDeBitsNaChave;
    private int nosVisitadosPesquisa;
    private int caracteresDistintos;

    public ArvorePatricia(int nbitsChave) {
        this.numeroDeBitsNaChave = nbitsChave;
    }

    // patNo nos slides
    private static abstract class NoArvorePatricia {
    }
    // PatNoInt nos slides

    private static class NoInternoArvorePatricia extends NoArvorePatricia {
// indice que remete a posicao do bit relativo a um conjunto de letras em um no interno
// index nos slides

        int indice;
// Variaveis que indicam quem sao os nos filhos de um no interno .
        NoArvorePatricia filhoEsquerda, filhoDireita;
    }
// PatNoExt nos slides

    private static class NoExternoArvorePatricia extends NoArvorePatricia {
// Atributos de um no externo da arvore :
// um caractere que aparece no minimo uma vez

        char chave;
// quantidade de vezes que a letra aparece
        int quantidade;
    }

    private int testaBit(int posicaoAtual, char chaveInsercao) {
        int posicaoBack = this.numeroDeBitsNaChave - posicaoAtual;

        int atual = 0;
        while (atual < posicaoBack - 1) {
            chaveInsercao /= 2;
            atual++;
        }

        return chaveInsercao % 2;
    }

    private int pesquisa(char chavePesquisa, NoArvorePatricia raizAtual) {
        int result = 0;

        if (raizAtual instanceof NoInternoArvorePatricia) {
            for (int i = 0; i < this.numeroDeBitsNaChave; i++) {
                if (testaBit(i, chavePesquisa) == ((NoInternoArvorePatricia) raizAtual).indice) {
                    switch (testaBit(i + 1, chavePesquisa)) {
                        case 1:
                            if (((NoInternoArvorePatricia) raizAtual).filhoDireita == null) {
                                System.out.println("Vocábulo inexistente");
                                return 0;
                            }
                            result = pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoDireita);
                            break;
                        case 0:
                            if (((NoInternoArvorePatricia) raizAtual).filhoEsquerda == null) {
                                System.out.println("Vocábulo inexistente");
                                return 0;
                            }
                            result = pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoEsquerda);
                            break;
                    }
                }
            }

        } else if (raizAtual instanceof NoExternoArvorePatricia) {
            result = ((NoExternoArvorePatricia) raizAtual).quantidade;
        }

        return result;
    }

    private boolean verificaNoExterno(NoArvorePatricia noAtual) {
        if (noAtual instanceof NoExternoArvorePatricia) {
            return true;
        }
        return false;
    }

    private NoArvorePatricia criaNoInterno(int posicaoAtual, NoArvorePatricia filhoEsq, NoArvorePatricia filhoDir) {
        NoInternoArvorePatricia aux= new NoInternoArvorePatricia( );
        aux.filhoDireita= filhoDir;
        aux.filhoEsquerda= filhoEsq;
        
        return aux;
    }
    
    private NoArvorePatricia criaNoExterno(char chaveInsercao){
    NoExternoArvorePatricia aux= new NoExternoArvorePatricia();
    aux.chave=chaveInsercao;
    aux.quantidade=1;
    return aux;
    }

}

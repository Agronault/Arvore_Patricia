package lista5;

/**
 *
 * @author Alexandre Luis Ribeiro Martins
 */
public class ArvorePatricia {

    private NoArvorePatricia raiz;
    private final int numeroDeBitsNaChave;
    private int nosVisitadosPesquisa;
    private int caracteresDistintos;

    public int getNosVisitadosPesquisa() {
        return nosVisitadosPesquisa;
    }

    public int getCaracteresDistintos() {
        return caracteresDistintos;
    }

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
        if (posicaoAtual == 0) {
            return 0;
        }
        int aux = (int) chaveInsercao;
        for (int i = 1; i <= this.numeroDeBitsNaChave - posicaoAtual; i++) {
            aux /= 2;
        }

        return aux % 2;
    }

    private int pesquisa(char chavePesquisa, NoArvorePatricia raizAtual) {
        this.nosVisitadosPesquisa++;
        if (!this.verificaNoExterno(raizAtual)) {
            switch (testaBit(((NoInternoArvorePatricia) raizAtual).indice, chavePesquisa)) {
                case 1:
                    return pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoDireita);
                case 0:
                    return pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoEsquerda);
            }
        } else {
            if (((NoExternoArvorePatricia) raizAtual).chave == chavePesquisa) {
                return ((NoExternoArvorePatricia) raizAtual).quantidade;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private NoArvorePatricia insere(char chaveInsercao, NoArvorePatricia raizAtual) {
        if (raizAtual == null) {
            this.caracteresDistintos++;
            return criaNoExterno(chaveInsercao);
        }

        NoArvorePatricia aux = raizAtual;
        while (!verificaNoExterno(aux)) {
            if (testaBit(((NoInternoArvorePatricia) aux).indice, chaveInsercao) == 1) {
                aux = ((NoInternoArvorePatricia) aux).filhoDireita;
            } else {
                aux = ((NoInternoArvorePatricia) aux).filhoEsquerda;
            }
        }
        
        
        if (verificaNoExterno(aux)) {
            int i = 1;
            //for(i=1; testaBit(i, chaveInsercao)==testaBit(i, ((NoExternoArvorePatricia)aux).chave) && i<=this.numeroDeBitsNaChave ;i++);
            while ((i <= this.numeroDeBitsNaChave) && (this.testaBit(i, chaveInsercao) == this.testaBit(i, ((NoExternoArvorePatricia) aux).chave))) {
                i++;
            }
            if (i > numeroDeBitsNaChave) {
                ((NoExternoArvorePatricia) aux).quantidade++;
                return raizAtual;
            } else {
                this.caracteresDistintos++;
                return this.insereEntre(chaveInsercao, raizAtual, i);
            }
        }
        
        
        return aux;
    }

    private NoArvorePatricia insereEntre(char chaveInsercao, NoArvorePatricia raizAtual, int posicaoAtual) {
        NoInternoArvorePatricia aux = null;

        if (!verificaNoExterno(raizAtual)) {
            aux = ((NoInternoArvorePatricia) raizAtual);
        }

        if (verificaNoExterno(raizAtual) || posicaoAtual < aux.indice) {
            NoArvorePatricia replace1 = criaNoExterno(chaveInsercao);
            if (testaBit(posicaoAtual, chaveInsercao) == 1) {
                return criaNoInterno(posicaoAtual, raizAtual, replace1);
            } else {
                return criaNoInterno(posicaoAtual, replace1, raizAtual);
            }
        } else {
            if (testaBit(aux.indice, chaveInsercao) == 1) {
                aux.filhoDireita = insereEntre(chaveInsercao, aux.filhoDireita, posicaoAtual);
            } else {
                aux.filhoEsquerda = insereEntre(chaveInsercao, aux.filhoEsquerda, posicaoAtual);
            }
            return aux;
        }
    }

    private boolean verificaNoExterno(NoArvorePatricia noAtual) {
        Class classe= noAtual.getClass();
        return classe.getName().equals(NoExternoArvorePatricia.class.getName());
    }

    private NoArvorePatricia criaNoInterno(int posicaoAtual, NoArvorePatricia filhoEsq, NoArvorePatricia filhoDir) {
        NoInternoArvorePatricia aux = new NoInternoArvorePatricia();
        aux.indice=posicaoAtual;
        aux.filhoDireita = filhoDir;
        aux.filhoEsquerda = filhoEsq;

        return aux;
    }

    private NoArvorePatricia criaNoExterno(char chaveInsercao) {
        NoExternoArvorePatricia aux = new NoExternoArvorePatricia();
        aux.chave = chaveInsercao;
        aux.quantidade = 1;
        return aux;
    }

    public void insere(char chaveInsercao) {
        this.raiz = this.insere(chaveInsercao, this.raiz);
    }

    public void pesquisa(char chavePesquisa) {
        this.nosVisitadosPesquisa = 0;
        this.pesquisa(chavePesquisa, raiz);
    }

}

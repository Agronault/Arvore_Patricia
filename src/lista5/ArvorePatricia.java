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
        
        if(raizAtual instanceof NoInternoArvorePatricia){
            switch(testaBit(((NoInternoArvorePatricia) raizAtual).indice, chavePesquisa)){
                case 1:
                    return pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoDireita);
                case 0:
                    return pesquisa(chavePesquisa, ((NoInternoArvorePatricia) raizAtual).filhoEsquerda);
            }
        }else{
            if(((NoExternoArvorePatricia)raizAtual).chave==chavePesquisa) return ((NoExternoArvorePatricia)raizAtual).quantidade;
            else return 0;
        }
     return 0;   
    }
    
    private NoArvorePatricia insere(char chaveInsercao, NoArvorePatricia raizAtual){
        if(raizAtual==null) return criaNoExterno(chaveInsercao);
        
        if(verificaNoExterno(raizAtual)){
        int i;
        for(i=1; testaBit(i, chaveInsercao)==testaBit(i, ((NoExternoArvorePatricia)raizAtual).chave) && i<=this.numeroDeBitsNaChave ;i++);
        if(i>numeroDeBitsNaChave) {
        ((NoExternoArvorePatricia)raizAtual).quantidade++;
        return raizAtual;
        }else
            return this.insereEntre(chaveInsercao, raizAtual, i);
        }else{
        NoArvorePatricia aux= ((NoInternoArvorePatricia)raizAtual);
        while(!verificaNoExterno(aux)){
            if(testaBit(((NoInternoArvorePatricia)aux).indice, chaveInsercao)==1)
                aux=((NoInternoArvorePatricia)aux).filhoDireita;
            else
                aux=((NoInternoArvorePatricia)aux).filhoEsquerda;
        }
        //TODO
        }
    }
    
    private NoArvorePatricia insereEntre(char chaveInsercao, NoArvorePatricia raizAtual,int posicaoAtual){
    //TODO
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

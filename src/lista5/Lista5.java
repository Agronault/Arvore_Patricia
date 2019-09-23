
package lista5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Lista5 {

    public static void main(String[] args) throws FileNotFoundException {
        ArvorePatricia[] arvores = new ArvorePatricia[10];
        System.out.println("_______________________________Exercicio 1_______________________________");
        for(int i=0; i<arvores.length; i++){
            arvores[i]= new ArvorePatricia(i+1);
            Scanner scanner = new Scanner(new FileReader("text.txt"));
            while(scanner.hasNext()){
            String aux= scanner.next();
            for(char a:aux.toCharArray()){
                arvores[i].insere(a);
            }
            }
            if(i==8) System.out.println("_______________________________Exercicio 2_______________________________");
            System.out.println(i+1+ " bits: " +arvores[i].getCaracteresDistintos());
            arvores[i].pesquisa('a');
            System.out.println("pesquisa: "+ arvores[i].getNosVisitadosPesquisa());
        }
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopalavras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static trabalhopalavras.Algoritmos.identificaCompConexos;
/**
 *
 * @author miche
 */
public class TrabalhoPalavras {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]){
        Grafo grafo;
        try{
            //=======================================Exemplo 1============================================================================//
            /**/grafo = lerGrafo(new File(".").getCanonicalPath()+"\\src\\TrabalhoPalavras\\grafo_pontes.txt", true);                            /**/                                                                                             /**/
//            /**/System.out.println("Nro Vertices: "+ grafo.getVertice().size());                                                        /**/
//            /**/System.out.println("Nro Arestas: "+ grafo.getAresta().size()); 
            /**/
                grafo.StronglyConnectedComponents(grafo);
//                System.out.println(grafo.getVertice());
//            /**/System.out.println(grafo.pontosDeArticulacao(grafo));
//            /**/System.out.println(grafo.pontes(grafo));
//                Algoritmos.dfsPontesNaoDirecionado(grafo);
            //=======================================Exemplo 1============================================================================//
        }catch(Exception e){
            System.out.println("Exception: "+ e);
        } 
    }
    
    
    public static Grafo lerGrafo(String path, boolean orientado){
        try {
            Grafo grafo = new Grafo();
            FileReader arq = new FileReader(path);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            String linha_split[]    = linha.split(" ");
            int nro_vertices        = Integer.parseInt(linha_split[0]);
            int nro_arestas         = Integer.parseInt(linha_split[1]);
            System.out.println("Arestas: "+nro_arestas);
            System.out.println("Vertices: "+nro_vertices);
            for(int i = 0; i < nro_arestas; i++){
                linha = lerArq.readLine();
                linha_split = linha.split(" ");
                Vertice u           = new Vertice(linha_split[0]);
                Vertice v           = new Vertice(linha_split[1]);                
                int verifica_vertice_u = grafo.verificaVerticeGrafo(u);
                if(verifica_vertice_u == -1){
                    grafo.addVertice(u);
                    verifica_vertice_u = grafo.verificaVerticeGrafo(u);
                }else{
                    u = grafo.getVertice().get(verifica_vertice_u);
                }
                int verifica_vertice_v = grafo.verificaVerticeGrafo(v);
                if(verifica_vertice_v == -1){
                    grafo.addVertice(v);
                    verifica_vertice_v = grafo.verificaVerticeGrafo(v);
                }else{
                    v = grafo.getVertice().get(verifica_vertice_v);
                }
                Aresta a = new Aresta(u, v);
                grafo.addAresta(a);
                grafo.getVertice().get((verifica_vertice_u == -1) ? i : verifica_vertice_u).addAresta(a);
                grafo.getVertice().get((verifica_vertice_u == -1) ? i : verifica_vertice_u).addAdjacente(v);
                if(!orientado){
                    Aresta b = new Aresta(v, u);
                    grafo.addAresta(b);
                    grafo.getVertice().get((verifica_vertice_v == -1) ? i : verifica_vertice_v).addAresta(b);
                    grafo.getVertice().get((verifica_vertice_v == -1) ? i : verifica_vertice_v).addAdjacente(u);
                }
            }
            
            arq.close();
            if(nro_vertices != grafo.getVertice().size()){
                System.out.println("Erro!! Número de vertices não são iguais a quantidade informada");
                return null;
            }
            return grafo;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
            return null;
        }
    }
}

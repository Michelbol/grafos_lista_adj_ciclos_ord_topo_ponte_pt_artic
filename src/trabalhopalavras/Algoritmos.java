/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopalavras;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miche
 */
public class Algoritmos {
    static List<Aresta> pontes = new ArrayList();
    
    public static int identificaCompConexos(Grafo g){
        g.setAllVerticeBranco();
        int compConex = 0;
        for(Vertice vertice : g.getVertice()){
            if(vertice.getCor() == Cor.Branco){
                compConex += 1;
                buscaEmProfundidadeConex(vertice, compConex);
            }
        }
        for(int i = 0; i <= compConex; i++){
            int nroVertices = 0;
            for(Vertice vertice : g.getVertice()){
                if(vertice.getCompConex() == i){
                    nroVertices += 1;
                }
                if(nroVertices >= 2){
                    break;
                }
            }
        }
        return compConex;
    }
    
    public static void buscaEmProfundidadeConex(Vertice v, int compConex){
        v.setCor(Cor.Cinza);
        v.setCompConex(compConex);
        for(Vertice vertice : v.getAdjacentes()){
            if (vertice.getCor() == Cor.Branco){
                buscaEmProfundidadeConex(vertice, compConex);
            }
        }
        v.setCor(Cor.Preto);
    }
       
    public static List<Vertice> getCaminho(Grafo g,Vertice origem,Vertice destino){
        List<Vertice> filaVertice = new ArrayList();
        List<Vertice> listaCaminho = new ArrayList();
        Vertice u;
        Vertice aux;
        
        g.initForBuscaLargura(origem);     
        
        filaVertice.add(origem);
        while (!filaVertice.isEmpty()){
            u = filaVertice.remove(0);
            
            for (Vertice v : u.getAdjacentes()){
                if (v.getCor().equals(Cor.Branco)){
                    v.setCor(Cor.Cinza);
                    v.setDistancia(u.getDistancia() + 1);
                    v.setPredecessor(u);
                    filaVertice.add(v);
                }                                
                if (destino.equals(v)){                    
                    filaVertice.clear();
                    break;
                }
            }
            u.setCor(Cor.Preto);
        }
        
        if (destino.getDistancia()!=Integer.MAX_VALUE){
            aux = destino;
            while(!aux.equals(origem)){
                listaCaminho.add(0, aux);
                aux = aux.getPredecessor();
            }
            listaCaminho.add(0, origem);
        }
        return listaCaminho;
    }
    
    public static void DfsVisit(Vertice u, int tempo, List<Vertice> vertices){
        tempo += 1;
        u.setCor(Cor.Cinza);        
        u.setInitTmpDesc(tempo);
        u.setLow(u.getInitTmpDesc());
        
        for (Vertice v : u.getAdjacentes()){
            if (v.getCor().equals(Cor.Branco)){
                v.setPredecessor(u);
                DfsVisit(v, tempo, vertices);
            }
        }
        u.setCor(Cor.Preto);
        tempo += 1;
        if(u.verificaVertice(vertices)){
            return;
        }
        u.setFinalTmpDesc(tempo);
        vertices.add(u);
    }
    
    public static void DfsVisitOrderByTmpDesc(Vertice u, int tempo, List<Vertice> vertices){
        tempo += 1;
        u.setCor(Cor.Cinza);        
        u.setInitTmpDesc(tempo);
        u.setLow(u.getInitTmpDesc());
        
        for (Vertice v : u.getAdjacentes()){
            if (v.getCor().equals(Cor.Branco)){
                v.setPredecessor(u);
                DfsVisit(v, tempo, vertices);
            }
        }
        u.setCor(Cor.Preto);
        tempo += 1;
        if(u.verificaVertice(vertices)){
            return;
        }
        u.setFinalTmpDesc(tempo);
        vertices.add(u);
    }
    
    
    public static void transpostaGrafo(Grafo g){
        List<Vertice> vertices = g.getVertice();
        List<Aresta> arestas = g.getAresta();
        
        for(int i = 0; i < arestas.size(); i++){
            Aresta aresta = arestas.get(i);
            Vertice v1 = aresta.getV1();
            Vertice v2 = aresta.getV2();
            aresta.setV1(v2);
            aresta.setV1(v1);
            List<Vertice> v1Adj = v1.getAdjacentes();
            for(int j = 0; j < v1Adj.size(); j++){
                Vertice adj = v1Adj.get(j);
                if(adj.equals(v2)){
                    v1Adj.remove(j);
                }
            }
            v2.addAdjacente(v1);
        }
    }
    
    public static void isPontoArticulacao(Vertice u, int tempo){
        tempo += 1;
        u.setCor(Cor.Cinza);        
        u.setInitTmpDesc(tempo);
        u.setLow(u.getInitTmpDesc());
        
        for (Vertice v : u.getAdjacentes()){
            if (v.getCor().equals(Cor.Branco)){
                v.setPredecessor(u);
                isPontoArticulacao(v,tempo);
                if (u.getPredecessor()==null){
                    if(isSegundoFilho(u,v)){
                        u.setIsPontoArticulacao(true);
                    }
                }               
                else {
                    u.setLow(Integer.min(u.getLow(),v.getLow()));
                    if (v.getLow() >= u.getInitTmpDesc()){
                        u.setIsPontoArticulacao(true);
                    }
                }
            } else if ((v!=u.getPredecessor()) && (v.getInitTmpDesc() < u.getInitTmpDesc())){
                u.setLow(Integer.min(u.getLow(),v.getInitTmpDesc()));
            }
        }     
        u.setCor(Cor.Preto);
        tempo += 1;
        u.setFinalTmpDesc(tempo);
    }
    
    public static boolean isSegundoFilho(Vertice u, Vertice v){
        return u.equals(v.getPredecessor().getPredecessor());
    }
    public static void isPonte(int tempo, Vertice v){
        tempo += 1;
        v.setCor(Cor.Cinza);
        v.setInitTmpDesc(tempo);
        v.setLow(v.getInitTmpDesc());
        for(Vertice verticeAdjacente: v.getAdjacentes()){
            if(verticeAdjacente.getCor() == Cor.Branco){
                verticeAdjacente.setPredecessor(v); 
                isPonte(tempo, verticeAdjacente);
                v.setLow(Integer.min(v.getLow(), verticeAdjacente.getLow()));
                if(verticeAdjacente.getLow() > v.getInitTmpDesc()){
                    Aresta a = new Aresta(verticeAdjacente, v);
                    pontes.add(a);
                }else{
                    if((verticeAdjacente != v.getPredecessor()) && (verticeAdjacente.getInitTmpDesc() < v.getInitTmpDesc())){
                       v.setLow(Integer.min(v.getLow(), verticeAdjacente.getInitTmpDesc()));
                    }
                }
            }
        }
        v.setCor(Cor.Preto);
        tempo += 1;
        v.setFinalTmpDesc(tempo);
    }
}

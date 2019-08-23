/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopalavras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author miche
 */
public class Grafo{
    private List<Aresta> aresta;
    private List<Vertice> vertice;
    private int nro_grupos;

    public int getNro_grupos() {
        return nro_grupos;
    }

    public void setNro_grupos(int nro_grupos) {
        this.nro_grupos = nro_grupos;
    }

    public List<Aresta> getAresta() {
        return aresta;
    }

    public void setAresta(List<Aresta> aresta) {
        this.aresta = aresta;
    }

    public List<Vertice> getVertice() {
        return vertice;
    }

    public void setVertice(List<Vertice> vertice) {
        this.vertice = vertice;
    }
    
    public int distanciaEntreVertices(Vertice v1, Vertice v2){
        return 999;
    }
    
    public void setAllVerticeBranco(){
        for(Vertice v: this.vertice){
            v.setCor(Cor.Branco);
        }
    }
    public void setAllCompConexZero(){
        for(Vertice v: this.vertice){
            v.setCompConex(0);
        }
    }
    
    public void addAresta(Aresta aresta){
        List arestas = this.getAresta();
        arestas.add(aresta);
        this.setAresta(arestas);
    }
    public void addVertice(Vertice vertice){
        List vertices = this.getVertice();
        vertices.add(vertice);
        this.setVertice(vertices);
    }

    public Grafo() {
        List<Vertice> v = new ArrayList();
        List<Aresta> a = new ArrayList();
        this.setAresta(a);
        this.setVertice(v);
    }

    @Override
    public String toString() {
        return "Grafo{" + "aresta=" + aresta + ", vertice=" + vertice + '}';
    }
    
    public void initForBuscaLargura(Vertice s){
        for(Vertice v: this.vertice){
            v.setCor(Cor.Branco);
            v.setDistancia(Integer.MAX_VALUE);
            v.setPredecessor(null);
            if (v.equals(s)){
                s.setCor(Cor.Cinza);
                s.setDistancia(0);
            }
        }
    }
    
    public List<Aresta> pontes(Grafo g){
        g.limpaGrafo(g);
        Algoritmos.isPonte(0, g.getVertice().get(0));
        return Algoritmos.pontes;
    }
    
    public List<Vertice> pontosDeArticulacao(Grafo g){
        List<Vertice> pontosDeArticulacao = new ArrayList();

        for(Vertice v : g.getVertice()){
            limpaGrafo(g);
            Algoritmos.isPontoArticulacao(v, 0);
        }
        
        for(Vertice v : g.getVertice()){
            if(v.isIsPontoArticulacao()){
                pontosDeArticulacao.add(v);
            }
        }
        return pontosDeArticulacao;
    }
    
    public List<Vertice> TopologicalSort(){
        List<Vertice> vertices = new ArrayList();

        for(Vertice v : this.getVertice()){
            limpaGrafo(this);
            Algoritmos.DfsVisit(v, 0, vertices);
        }
        List<Vertice> vertices1 = new ArrayList();
        for(int i = vertices.size()-1; i >= 0; i--){
            vertices1.add(vertices.get(i));
        }
        return vertices1;
    }
    
    public boolean hasCicles(){        
        limpaGrafo(this);
        List<Vertice> vertices = this.getVertice();
        int vertices_length = vertices.size();
        for(int i = 0; i < vertices_length; i++){
            Vertice v = vertices.get(i);
            if (v.getCor() == Cor.Preto){
                continue;
            }
            v.setCor(Cor.Cinza);
            if (Algoritmos.hasCiclesAvailables(v) == true){
                return true;
            }
        } 
        return false;
    }
    
    public List<Vertice> StronglyConnectedComponents(Grafo g){
        try{
            List<Vertice> vertices = new ArrayList<Vertice>();
            limpaGrafo(g);
            for(Vertice v : g.getVertice()){
                Algoritmos.DfsVisit(v, 0, vertices);
            }
            Algoritmos.transpostaGrafo(g);
            vertices.sort(new Vertice());
            for(Vertice v : vertices){
                Algoritmos.DfsVisit(v, 0, vertices);
            }
            System.out.println(vertices);
            return null;
        }catch(Exception e){
            System.out.println("Erro: "+e.toString());
            return null;
        }
    }
    
    public boolean maior(int x, int y){
        return x > y;
    }
    
    public Grafo limpaGrafo(Grafo g){
        for(Vertice v: g.getVertice()){
            v.setLow(0);
            v.setInitTmpDesc(0);
            v.setFinalTmpDesc(0);
            v.setCor(Cor.Branco);
            v.setCompConex(0);
            v.setDistancia(0);
            v.setPredecessor(null);
            v.setIsPontoArticulacao(false);
            Algoritmos.pontes = new ArrayList();
        }
        return g;
    }
    
        public void imprimeListaAdjacencia(){
        List<Vertice>   vertices  = this.getVertice();
        List<Aresta>    arestas   = this.getAresta();
        
        for(int i = 0; i < vertices.size(); i++){
            Vertice vertice = vertices.get(i);
            System.out.print("["+ vertice.getNome() +"]");
            List<Vertice> adjacentes = vertice.getAdjacentes();
            for(int j = 0; j < adjacentes.size(); j++){
                Vertice adjacente = adjacentes.get(j);
                if(j==0){
                    System.out.print("->");
                }
                System.out.print("["+ adjacente.getNome()+"]");
                if((j+1) != adjacentes.size()){
                    System.out.print("[]->");
                }else{
                    System.out.print("[/]");
                }
            }
            System.out.println("");
        }
    }
    public int verificaVerticeGrafo(Vertice u){
        for(int i = 0; i < this.vertice.size(); i++){
            if(this.vertice.get(i).equals(u)){
                return i;
            }
        }
        return -1;
    }
}

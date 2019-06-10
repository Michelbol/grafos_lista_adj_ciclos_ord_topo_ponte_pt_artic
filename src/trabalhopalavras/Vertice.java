/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopalavras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author miche
 */
public class Vertice implements Comparator<Vertice>{
    private String nome;
    private Vertice predecessor;
    private int low;
    private int initTmpDesc;
    private int finalTmpDesc;
    private Cor cor;
    private List<Vertice> adjacentes;
    private int compConex;
    private int distancia;
    private boolean isPontoArticulacao;
    private List<Aresta> arestas;
    
   
    public int compare(Vertice a1, Vertice a2){
            return a1.getFinalTmpDesc() - a2.getFinalTmpDesc();
    }
    
    public Vertice(String nome) {
        List<Vertice> adjacente = new ArrayList();
        this.adjacentes = adjacente;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getInitTmpDesc() {
        return initTmpDesc;
    }

    public void setInitTmpDesc(int initTmpDesc) {
        this.initTmpDesc = initTmpDesc;
    }

    public int getFinalTmpDesc() {
        return finalTmpDesc;
    }

    public void setFinalTmpDesc(int finalTmpDesc) {
        this.finalTmpDesc = finalTmpDesc;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public List<Vertice> getAdjacentes() {
        return adjacentes;
    }

    public void setAdjacentes(List<Vertice> adjacentes) {
        this.adjacentes = adjacentes;
    }
    
    public List<Aresta> getArestas() {
        if(this.arestas == null){
            this.arestas = new ArrayList();
        }
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }


    public Vertice() {
        this.cor = Cor.Branco;
        List<Vertice> adjacente = new ArrayList();
        this.adjacentes = adjacente;
    }

    public Vertice getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertice predecessor) {
        this.predecessor = predecessor;
    }

    public int getCompConex() {
        return compConex;
    }

    public void setCompConex(int compConex) {
        this.compConex = compConex;
    }
    
    public boolean verificaAdjavence(Vertice v1, Vertice v2){
        int verificaNome = 0;
        System.out.println("Comparando: " + v1.getNome()+" com:" + v2.getNome());
        for(int i = 0; i < 5; i++){
//            System.out.println("Comparando: " + v1.getNome().charAt(i)+" com:" + v2.getNome().charAt(i));
            if(v1.getNome().charAt(i) == v2.getNome().charAt(i)){
                verificaNome += 1;
            }
        }
        if(verificaNome >= 4){
//            System.out.println("São adjacentes");
            return true;
        }
//        System.out.println("Não são adjacentes");
        return false;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Vertice{" + "nome=" + nome + " Tempo de Término: "+ finalTmpDesc + '}';
    }
    public void addAdjacente(Vertice v){
        List adjacente = this.getAdjacentes();
        adjacente.add(v);
        this.setAdjacentes(adjacente);
    }

    public boolean isIsPontoArticulacao() {
        return isPontoArticulacao;
    }

    public void setIsPontoArticulacao(boolean isPontoArticulacao) {
        this.isPontoArticulacao = isPontoArticulacao;
    }
    
    public void addAresta(Aresta a){
        List lista_arestas = this.getArestas();
        lista_arestas.add(a);
        this.arestas = lista_arestas;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertice other = (Vertice) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    public Aresta verificaAresta(Vertice v){
        for(Aresta a : this.arestas){
            if(a.getV2().equals(v)){
                return a;
            }
        }
        return null;
    }
    
    public boolean verificaVertice(List<Vertice> vertices){
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).equals(this)){
                return true;
            }
        }
        return false;
    }
}

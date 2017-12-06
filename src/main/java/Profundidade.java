import java.util.HashMap;

/**
 *
 * @author nakao
 */
public class Profundidade {
    public GraphAdjacencyList graph;
    public HashMap<String,GraphColor> states;//Estados de cada node
    public HashMap<String,String> predecessors;//Predecessores de cada node
    public HashMap<String,Integer> tempoDescoberta;
    public HashMap<String,Integer> tempoFinalizacao;
    public int tempo;
    /*
    Para cada vértice u de G
    Cor[u] = Branco
    Predecessor[u] = NIL
    Tempo = 0
    Para cada vértice u de G
    Se Cor[u] == BRANCO então
        DFS-Visit(G,u)
    */
    public Profundidade(){
        this.graph = new GraphAdjacencyList();
        this.states = new HashMap();
        this.predecessors = new HashMap();
        this.tempoDescoberta = new HashMap();
        this.tempoFinalizacao = new HashMap();
    }

    public void run(String raiz){
        tempo = 0;
        for(GraphAdjacencyList.InnerList al:graph.adjacencyList.values()){
            this.states.put(al.node, GraphColor.WHITE);
            this.predecessors.put(al.node, "");
        }
        for(GraphAdjacencyList.InnerList al:graph.adjacencyList.values()){
            if(this.states.get(al.node)==GraphColor.WHITE){
                this.dfsVisit(al.node);
            }
        }
    }
    /*
    DFSVisit(Grafo G, Vértice u)
        Tempo = Tempo + 1
        D[u] = tempo
        Cor[u] = CINZA
        Para cada v no conjunto em Adj[u]
            SE Cor[v] == BRANCO
                Predecessor[v] = u
        DFS-Visit(G, v)
        Cor[u] = PRETO
        tempo = tempo + 1
        F[u] = tempo
*/
    private void dfsVisit(String vertex){
        this.tempo = tempo+1;
        this.tempoDescoberta.put(vertex, tempo);
        this.states.put(vertex, GraphColor.GRAY);
        for(String s:this.graph.adjacencyList.get(vertex).innerNodes){
            if(this.states.get(s)==GraphColor.WHITE){
                this.predecessors.put(s, vertex);
                dfsVisit(s);
            }
        }
        this.states.put(vertex, GraphColor.BLACK);
        this.tempo = this.tempo+1;
        this.tempoFinalizacao.put(vertex, tempo);
    }

    public void print(){
        GraphAdjacencyList gal = (GraphAdjacencyList)this.graph;
        for(String s:gal.adjacencyList.keySet()){
            System.out.println(s+" - Predecessor:"+this.predecessors.get(s) +" - TempoDescoberta:"+this.tempoDescoberta.get(s)+" - TempoFinalizacao:"+this.tempoFinalizacao.get(s));
        }
    }
}

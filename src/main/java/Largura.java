import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Largura {
    public Graph graph;
    public HashMap<String,GraphColor> states;//Estados de cada node
    public HashMap<String,String> predecessors;//Predecessores de cada node
    public FilaNode executionQueue;//Fila de execucao
    public HashMap<String,Integer> distancesToRaiz;//Distancia para raiz de cada node


    public Largura(GraphType gt){
        switch (gt) {
            case ListaAdjacencia:
                this.graph = new GraphAdjacencyList();
                break;
            case MatrizAdjacencia:
                break;
            case MatrizIncidencia:
                break;
            default:
                break;
        }
        this.states = new HashMap();
        this.predecessors = new HashMap();
        this.executionQueue = new FilaNode();
        this.distancesToRaiz = new HashMap();
    }


    public void runBFS(String raiz){
       /* Pseudo-code: Busca em Largura
       for( todo nó U do grafo G: ){
           cor[U] = branco
           predecessor[U] = nulo
           distanciaParaRaiz[U] = nulo
       }
       cor[Raiz] = cinza
       distanciaParaRaiz[Raiz] = 0
       predecessor[Raiz] = nulo
       enfileira(raiz)
       while(fila nao esta vazia){
            U = desinfileira(Q)
            for(cada vertice V de U){
                cor[V] = cinza
                distanciaParaRaiz[V] = distanciaParaRaiz+1
                predecessor[V] = U
                ENFILEIRA(V)
            }
       }
       COR[U] = PRETO
       */
        //Inicializacao da busca
        GraphAdjacencyList gal = (GraphAdjacencyList)graph;
        for(GraphAdjacencyList.InnerList al:gal.adjacencyList.values()){
            this.states.put(al.node, GraphColor.WHITE);
            this.predecessors.put(al.node, "");
            this.distancesToRaiz.put(al.node, -999);
        }
        this.distancesToRaiz.put(raiz, 0);
        this.executionQueue.enqueue(raiz);

        while(!this.executionQueue.nodes.isEmpty()){
            String corrente = this.executionQueue.dequeue();
            //System.out.println("Corrente:"+corrente);
            GraphAdjacencyList.InnerList il = gal.getInnerList(corrente);
            for(String s:il.innerNodes){
                if(this.states.get(s)==GraphColor.WHITE){
                    this.states.put(s, GraphColor.GRAY);
                    this.distancesToRaiz.put(s, this.distancesToRaiz.get(corrente)+1);
                    this.predecessors.put(s, corrente);
                    this.executionQueue.enqueue(s);
                }
            }
            this.states.put(corrente, GraphColor.BLACK);
        }
    }

    public static class FilaNode{
        private List<String> nodes;

        public FilaNode(){
            this.nodes = new ArrayList();
        }

        public void enqueue(String node){
            this.nodes.add(node);
        }
        public String dequeue(){
            return this.nodes.remove(0);
        }
    };
}

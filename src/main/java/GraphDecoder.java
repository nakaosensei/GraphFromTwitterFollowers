import java.util.ArrayList;
import java.util.List;

public class GraphDecoder {
    public GraphAdjacencyList graph;

    public void loadGraph(String filePath){
        graph = new GraphAdjacencyList();
        graph.loadFromFile(filePath);
    }

    public Largura doBuscaLargura(String user){
        Largura l = new Largura(GraphType.ListaAdjacencia);
        l.graph=this.graph;
        l.runBFS(user);
        return l;
    }

    public Profundidade doBuscaProfundidade(String user){
        Profundidade p = new Profundidade();
        p.graph=this.graph;
        p.run(user);
        return p;
    }

    public String getFollowerFromFolowers(String node){
        String out = "";
        Largura l = this.doBuscaLargura(node);
        List<String> guys = new ArrayList<String>();
        for(String key:l.distancesToRaiz.keySet()){
            if(l.distancesToRaiz.get(key)==1 ){
                guys.add(key);
            }
        }
        for(String s:guys){
            out+=s+" :"+this.graph.getInnerList(s).innerNodesToString()+"\n";
        }
        return out;
    };

}

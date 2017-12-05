import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphAdjacencyList extends Graph{
    public HashMap<String,InnerList> adjacencyList;

    public GraphAdjacencyList(){
        adjacencyList = new HashMap();
        this.graphTyppe = graphTyppe.ListaAdjacencia;
    }

    public void addNode(String node){
        if(!this.containsNode(node)){
            InnerList il = new InnerList();
            il.node = node;
            adjacencyList.put(node,il);
        }
    }

    //Adiciona todas as strings da lista innerNodes para o no node.
    public void addInners(String node, List<String> innerNodes){
        InnerList il = this.adjacencyList.get(node);
        if(il!=null){
            for(String s:innerNodes){
                il.innerNodes.add(s);
            }
        }
    }

    //Adiciona todas as strings da lista innerNodes para o no node.
    public void addInner(String node, String innerNode){
        InnerList il = this.adjacencyList.get(node);
        if(il!=null){
            il.innerNodes.add(innerNode);
        }
    }


    public void print(){
        for(InnerList il:this.adjacencyList.values()){
            il.print();
        }
    }

    private boolean containsNode(String node){
        InnerList il = this.adjacencyList.get(node);
        if(il!=null){
            return true;
        }
        return false;
    }

    public InnerList getInnerList(String node){
        return this.adjacencyList.get(node);
    }

    public void writeToFile(String path){
        String write = "";
        for(String s:this.adjacencyList.keySet()){
            write+=s+this.adjacencyList.get(s).innerNodesToString()+"\n";
        };
        try{
            Arquivo.escreverArquivo(write, path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loadFromFile(String path){
        try{
            adjacencyList = new HashMap();
            List<String> in = Arquivo.lerArquivo(path);
            for(String s:in){
                if(!s.trim().equals("")){
                    String split[] = s.split("/");
                    this.addNode(split[0]);
                    for(int i=1;i<split.length;i++){
                        this.addInner(split[0], split[i]);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static class InnerList{
        public String node = "";
        public List<String> innerNodes = new ArrayList();

        public InnerList(){
        }

        public boolean contains(String node){
            for(String s:innerNodes){
                if(s.equals(node)){
                    return true;
                }
            }
            return false;
        }

        //Esse metodo retorna os proximos nodes que podem ser percorridos,
        //ele verifica primeiro o node corrente tem algum outro node ligado a ele,
        //depois, verifica se ele possui algum no que nao esta na lista
        //de visitados, se encontrar, incrementa na lista de retorno, depois do
        //processamento, retorna a lista
        public List<String> getNextWays(HashMap<String,InnerList> visiteds){
            List<String> nexts = new ArrayList();
            if(this.innerNodes.isEmpty()){
                return nexts;
            }
            for(String s:this.innerNodes){
                boolean isOnList = false;
                InnerList il = visiteds.get(s);
                if(il!=null){
                    isOnList = true;
                }
                if(isOnList == false){
                    nexts.add(s);
                }
            }
            return nexts;
        }

        public void print(){
            System.out.println(this.node+" - "+this.innerNodesToString());
        }

        public boolean hasInnerNodes(){
            if(this.innerNodes.isEmpty()){
                return false;
            }else{
                return true;
            }
        };

        public String innerNodesToString(){
            String out = "";
            for(String s:innerNodes){
                out+="/"+s;
            }
            return out;
        }
    };
}

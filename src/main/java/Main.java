import br.com.Graph.GraphAdjacencyList;
import twitter4j.*;

public class Main {


    public static void main(String[] args) {
        try{
            Arquivo.escreverArquivo("Hello World","src/main/arquivo.txt");
        }catch(Exception e){
            e.printStackTrace();
        }

       //GraphMounter gm = new GraphMounter();
       //gm.MountGraph("tiagofnakao");
       //gm.graph.print();
        /*
        Twitter twitter = TwitterFactory.getSingleton();
        String message="\"A Visit to Transylvania\" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
        try{
            PagableResponseList<User> followingList= twitter.getFollowersList("tiagofnakao",-1);
            for(int i=0;i<followingList.size();i++){
                System.out.println(followingList.get(i).getName()+"  -  "+followingList.get(i).getScreenName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}

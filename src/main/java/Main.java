import br.com.Graph.GraphAdjacencyList;
import twitter4j.*;

public class Main {


    public static void main(String[] args) {
        /*
        try{
            Arquivo.escreverArquivo("Hello World","src/main/arquivo.txt");
        }catch(Exception e){
            e.printStackTrace();
        }
        */
       GraphMounter gm = new GraphMounter();
       gm.MountGraph("tiagofnakao");
       gm.graph.print();
       System.out.println(gm.writesCount);
       System.out.println(gm.levelsCount);

        //Twitter twitter = TwitterFactory.getSingleton();
        //String message="\"A Visit to Transylvania\" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
        //try{
        //    PagableResponseList<User> followingList= twitter.getFriendsList("BsThemesCo ",-1, 20);
        //    System.out.println(followingList.size());
        //    for(int i=0;i<followingList.size();i++){
        //        System.out.println(followingList.get(i).getScreenName());
        //    }
        //}catch(Exception e){
        //    e.printStackTrace();
        //}
    }
}

import br.com.Graph.GraphAdjacencyList;
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;

import java.util.ArrayList;

public class GraphMounter {
    public GraphAdjacencyList graph;
    public int writesCount;
    public int levelsCount;

    public GraphMounter(){
        this.graph = new GraphAdjacencyList();
    }

    public void MountGraph(String sourceAccountLogin){
        int sleepTime = 75000;
        try{
            long startTime = System.nanoTime();
            Twitter twitter = TwitterFactory.getSingleton();
            ArrayList<String> actuals  =  new ArrayList<String>();
            ArrayList<String> nexts    =  new ArrayList<String>();
            ArrayList<String> visiteds =  new ArrayList<String>();
            levelsCount=2;
            graph.addNode(sourceAccountLogin);
            visiteds.add(sourceAccountLogin);
            int count=0;
            PagableResponseList<User> followingList= twitter.getFriendsList(sourceAccountLogin,-1,20);
            for(User s:followingList){
                if(!visiteds.contains(s.getScreenName()) && s.getFriendsCount()>70){
                    if(count<10){
                        visiteds.add(s.getScreenName());
                        nexts.add(s.getScreenName());
                        graph.addNode(s.getScreenName());
                        graph.addInner(sourceAccountLogin,s.getScreenName());
                    }else{
                        break;
                    }
                    count++;
                }
            }
            graph.writeToFile("src/main/graph"+writesCount+".txt");
            writesCount++;
            long duration = System.nanoTime() - startTime;
            if ((sleepTime - duration / 1000000) > 0) {
                System.out.println("Sleep for " + (sleepTime - duration / 1000000) + " miliseconds");
                Thread.sleep((sleepTime - duration / 1000000));
            }

            while(levelsCount<=2 && !nexts.isEmpty()){
                System.out.println("Actuals:");
                for(String s:actuals){
                    System.out.print(s+" - ");
                }
                System.out.println("\nNexts");
                for(String s:nexts){
                    System.out.print(s+" - ");
                }

                actuals.clear();
                actuals.addAll(nexts);
                nexts.clear();
                for(String s:actuals){
                    startTime = System.nanoTime();
                    followingList= twitter.getFriendsList(s,-1,20);
                    count = 0;
                    for(User u:followingList){
                        if(!visiteds.contains(u.getScreenName())&& u.getFriendsCount()>70){
                            if(count<10){
                                visiteds.add(u.getScreenName());
                                nexts.add(u.getScreenName());
                                graph.addNode(u.getScreenName());
                                graph.addInner(s,u.getScreenName());
                                count++;
                            }else{
                                break;
                            }
                        }
                    }
                    graph.writeToFile("src/main/graph"+writesCount+".txt");
                    writesCount++;
                    duration = System.nanoTime() - startTime;
                    if ((sleepTime - duration / 1000000) > 0) {
                        System.out.println("Sleep for " + (sleepTime - duration / 1000000) + " miliseconds");
                        Thread.sleep((sleepTime - duration / 1000000));
                    }
                }
                levelsCount++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        /*
        try{
            PagableResponseList<User> followersList= twitter.getFriendsList(sourceAccountLogin,-1);
            for(int i=0;i<followersList.size();i++){
                graph.addInner(sourceAccountLogin,followersList.get(i).getScreenName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}

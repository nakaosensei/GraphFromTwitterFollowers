package main.java;


import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class GraphMounter {
    public GraphAdjacencyList graph;
    public int writesCount;
    public int levelsCount;
    public ConfigurationBuilder cb;
    public Twitter twitter;

    public GraphMounter(){
        this.graph = new GraphAdjacencyList();
    }


    public void initConfigurationBuilder(){
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZfyYT762f8iptmyOAQiPVpSSG")
                .setOAuthConsumerSecret("abdgza7zIl2lbRaYdY4L7C5zhGgxftLiACt5WJfrFtGnNIQqIF")
                .setOAuthAccessToken("188507287-370vFKYm0sA6OumKatEWXRZuA4ippzCl7kteJCpi")
                .setOAuthAccessTokenSecret("MIaKNuYorBD5qqsxzzb58gOuvrBuGBToya5nzLEHsJ8kT");
                //.setHttpProxyPort(8080)
                //.setHttpProxyHost("proxy");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void MountGraph(String sourceAccountLogin,String pathToWrite){
        int sleepTime = 100000;
        this.initConfigurationBuilder();
        try{
            long startTime = System.nanoTime();
            ArrayList<String> actuals  =  new ArrayList<String>();
            ArrayList<String> nexts    =  new ArrayList<String>();
            ArrayList<String> visiteds =  new ArrayList<String>();
            levelsCount=2;
            graph.addNode(sourceAccountLogin);
            visiteds.add(sourceAccountLogin);
            int count=0;
            PagableResponseList<User> followingList= twitter.getFriendsList(sourceAccountLogin,-1,20);
            for(User s:followingList){
                if(count<10){
                    if(!visiteds.contains(s.getScreenName()) && s.getFriendsCount()>70){
                        visiteds.add(s.getScreenName());
                        nexts.add(s.getScreenName());
                        graph.addNode(s.getScreenName());
                        graph.addInner(sourceAccountLogin,s.getScreenName());
                        count++;
                    }else{
                        graph.addNode(s.getScreenName());
                        graph.addInner(sourceAccountLogin,s.getScreenName());
                    }
                }else{
                    break;
                }
            }
            graph.writeToFile(pathToWrite+"/graph"+writesCount+".txt");
            writesCount++;
            long duration = System.nanoTime() - startTime;
            if ((sleepTime - duration / 1000000) > 0) {
                System.out.println("Sleep for " + (sleepTime - duration / 1000000) + " miliseconds");
                //this.initConfigurationBuilder();
                Thread.sleep((sleepTime - duration / 1000000));
                //this.initConfigurationBuilder();
            }

            while(levelsCount<10 && !nexts.isEmpty()){
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
                        if(count<10){
                            if(!visiteds.contains(u.getScreenName())&& u.getFriendsCount()>70){
                                visiteds.add(u.getScreenName());
                                nexts.add(u.getScreenName());
                                graph.addNode(u.getScreenName());
                                graph.addInner(s,u.getScreenName());
                                count++;
                            }else{
                                graph.addNode(u.getScreenName());
                                graph.addInner(s,u.getScreenName());
                            }
                        }else{
                            break;
                        }
                    }
                    graph.writeToFile(pathToWrite+"/graph"+writesCount+".txt");
                    writesCount++;
                    duration = System.nanoTime() - startTime;
                    if ((sleepTime - duration / 1000000) > 0) {
                        System.out.println("Sleep for " + (sleepTime - duration / 1000000) + " miliseconds");
                        //this.initConfigurationBuilder();
                        Thread.sleep((sleepTime - duration / 1000000));
                        //this.initConfigurationBuilder();
                    }

                }
                levelsCount++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            Arquivo.escreverArquivo("Levels count:"+this.levelsCount+" - Nodes count:"+this.writesCount,"src/main/Generated/information.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}

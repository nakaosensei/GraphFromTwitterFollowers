import twitter4j.*;

public class Main {


    public static void main(String[] args) {
        Twitter twitter = TwitterFactory.getSingleton();
        String message="\"A Visit to Transylvania\" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
        try{
            PagableResponseList<User> followingList= twitter.getFollowersList("tiagofnakao",-1);
            for(int i=0;i<followingList.size();i++){
                System.out.println(followingList.get(i).getName()+"  -  "+followingList.get(i).getScreenName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

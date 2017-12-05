import java.util.List;

public class MainGraphDecoder {

    public static void main(String[] args) {
        GraphDecoder gd = new GraphDecoder();
        gd.loadGraph("src/main/Generated/graph.txt");
        String followersFromFollowers = gd.getFollowerFromFolowers("tiagofnakao");
        System.out.println("Bem vindo a APS de grafos");
        System.out.println("Friends from friends:");
        System.out.println(followersFromFollowers);
    }
}

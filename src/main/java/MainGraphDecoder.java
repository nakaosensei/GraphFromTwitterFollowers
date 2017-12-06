import java.util.List;
import java.util.Scanner;

public class MainGraphDecoder {

    public static void main(String[] args) {
        GraphDecoder gd = new GraphDecoder();
        gd.loadGraph("src/main/Generated/graph.txt");
        String user;
        System.out.println("Bem vindo a APS de grafos,digite uma opção:");
        Scanner entrada = new Scanner(System.in);
        int lido = -1;
        while(lido!=5){
            System.out.println("1-Buscar amigos de amigos");
            System.out.println("2-Executar Busca em Largura");
            System.out.println("3-Executar Busca em Profundidade");
            System.out.println("4-Gerar novo grafo a partir de uma conta do twitter");
            System.out.println("5-Sair");
            lido = entrada.nextInt();
            switch (lido){
                case 1:
                    System.out.println("Digite um nome de usuario, como o grafo tem quatro niveis, voce so pode escolher nos do primeiro e segundo nivel, recomendo tiagofnakao");
                    user = entrada.next();
                    String followersFromFollowers = gd.getFollowerFromFolowers(user);
                    System.out.println(followersFromFollowers);
                    break;
                case 2:
                    System.out.println("Digite um nome de usuario");
                    user = entrada.next();
                    Largura l = gd.doBuscaLargura(user);
                    l.print();
                    break;
                case 3:
                    System.out.println("Digite um nome de usuario");
                    user = entrada.next();
                    Profundidade p = gd.doBuscaProfundidade(user);
                    p.print();
                    break;
                case 4:
                    System.out.println("Digite um nome de usuario");
                    user = entrada.next();
                    GraphMounter gm = new GraphMounter();
                    gm.MountGraph(user);
                    gm.graph.print();
                    System.out.println(gm.writesCount);
                    System.out.println(gm.levelsCount);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}

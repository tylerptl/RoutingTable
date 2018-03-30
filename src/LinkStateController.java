import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;

public class LinkStateController {

    public static void main(String... args){
        NetworkUtils net = new NetworkUtils("nodes.txt");
        int len = net.getNodeNames().length;
        String[] names = net.getNodeNames();

        HipsterDirectedGraph<String, Double> graph = GraphBuilder.<String, Double>create()
                .connect("u").to("v").withEdge(2d)
                .connect("u").to("w").withEdge(5d)
                .connect("u").to("x").withEdge(1d)
                .connect("v").to("u").withEdge(2d)
                .connect("v").to("x").withEdge(2d)
                .connect("v").to("w").withEdge(3d)
                .connect("w").to("u").withEdge(5d)
                .connect("w").to("x").withEdge(3d)
                .connect("w").to("y").withEdge(1d)
                .connect("w").to("z").withEdge(5d)
                .connect("w").to("v").withEdge(3d)
                .connect("x").to("v").withEdge(2d)
                .connect("x").to("w").withEdge(3d)
                .connect("x").to("y").withEdge(1d)
                .connect("x").to("u").withEdge(1d)
                .connect("y").to("x").withEdge(1d)
                .connect("y").to("z").withEdge(2d)
                .connect("y").to("w").withEdge(1d)
                .connect("z").to("w").withEdge(5d)
                .connect("z").to("y").withEdge(2d)
                .createDirectedGraph();


        for(String str :names){
            SearchProblem p = GraphSearchProblem
                    .startingFrom(str)
                    .in(graph)
                    .takeCostsFromEdges()
                    .build();
            System.out.println("Table for " + str);
            for(int i = 0; i < len; i++){
                if(names[i] == str){
                    continue;
                }
                System.out.println("\t " + names[i] + " (" + Hipster.createDijkstra(p).search(names[i]).getOptimalPaths()+", " +
                        Hipster.createDijkstra(p).search(names[i]).getGoalNodes() );
            }
        }
        //System.out.println(Hipster.createDijkstra(p).search("z").getGoalNode().pathSize());
//        for(String str: net.getConnections()){
//            System.out.println(str);
//        }
//        for(String str : net.getNodeNames()){
//            System.out.println(str);
//        }
    }
}

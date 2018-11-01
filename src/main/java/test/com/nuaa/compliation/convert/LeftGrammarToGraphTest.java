package test.com.nuaa.compliation.convert;

import com.nuaa.compliation.convert.GrammarToGraph;
import com.nuaa.compliation.enums.ModelType;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * LeftGrammarToGraph Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十月 30, 2018</pre>
 */
public class LeftGrammarToGraphTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: GrammarToGraph(LeftGrammar leftGrammar)
     */
    @Test
    public void testLeftGrammarToGraph() throws Exception {
        //TODO: Test goes here...
        String start = "D";
        String expression = "1|c1|d0|d1|b0";


        GrammarToGraph grammarToGraph = new GrammarToGraph();

        grammarToGraph.addEdges(start, expression, ModelType.LeftToNf);
        String[][] edges = grammarToGraph.getStrEdges(ModelType.LeftToNf);

        for (String[] edge : edges) {
            System.out.println(edge[0] + "-" + edge[1] + "->" + edge[2]);
        }


    }

    @Test
    public void rightLeftGrammarToGraph() throws Exception {
        //TODO: Test goes here...
        String start = "D";
        String expression = "1|1c|0d|1d|0b";


        GrammarToGraph grammarToGraph = new GrammarToGraph();

        grammarToGraph.addEdges(start, expression, ModelType.RightToNf);
        String[][] edges = grammarToGraph.getStrEdges(ModelType.RightToNf);

        for (String[] edge : edges) {
            System.out.println(edge[0] + "-" + edge[1] + "->" + edge[2]);
        }


    }


    /**
     * Method: addEdges(String startNode, String expression)
     */
    @Test
    public void testAddEdges() throws Exception {
//TODO: Test goes here... 
    }


    @Test
    public void testCharDigit() {
        char a = 'd';
        if (Character.isDigit(a)) {
            System.out.println("is number");
        } else {
            System.out.println("not number");
        }
    }

} 

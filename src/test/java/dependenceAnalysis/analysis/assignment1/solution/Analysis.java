package dependenceAnalysis.analysis.assignment1.solution;

import dependenceAnalysis.util.cfg.CFGExtractor;
import dependenceAnalysis.util.cfg.Graph;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;

/**
 *
 *
 * Created by neilwalkinshaw on 19/10/2017.
 */
public abstract class Analysis {

    protected  Graph controlFlowGraph;

    protected final MethodNode mn;
    protected final ClassNode cn;

    public Analysis(ClassNode cn, MethodNode mn){
        Graph cfg = null;
        if(cn == null) {
            //This should only happen under testing conditions.
            this.cn = null;
            this.mn = null;
            this.controlFlowGraph = null;
            return;
        }
        try {
            cfg = CFGExtractor.getCFG(cn.name, mn);

        } catch (AnalyzerException e) {
            e.printStackTrace();
        }
        controlFlowGraph = cfg;
        this.mn = mn;
        this.cn = cn;
    }

    /**
     * Mainly a testability method - returns the control flow graph.
     * @return
     */
    public Graph getControlFlowGraph(){
        return controlFlowGraph;
    }

    public void setControlFlowGraph(Graph g){
        this.controlFlowGraph = g;
    }

    /**
     * Create a new graph object that returns a Graph representation of the results of the analysis.
     * @return
     */
    public abstract Graph computeResult();



}

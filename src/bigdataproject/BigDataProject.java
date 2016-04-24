/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

/**
 *
 * @author MarcoM
 */
public class BigDataProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readDataSet read = new readDataSet();
        Dataset ds = new DefaultDataset();
        read.run();
        for (int i = 0; i < read.matrix.length; i++) {
            Instance tmp = new DenseInstance(read.matrix[i]);
            ds.add(tmp);
        }
        Clusterer km = new KMeans();
        Dataset[] clusters = km.cluster(ds);
        System.out.println("Length: " + clusters.length);
        for (int i = 0; i < clusters.length; i++) {
            int index = i + 1;
            System.out.println("Cluster: " + index);
            for (Instance temp : clusters[i]) {
                System.out.println(temp);
            }
        }

        ClusterEvaluation sse = new SumOfSquaredErrors();
        /* Measure the quality of the clustering */
        double score = sse.score(clusters);
        System.out.println("Score: "+score);

    }

}

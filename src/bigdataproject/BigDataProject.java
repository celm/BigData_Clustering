/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.AICScore;
import net.sf.javaml.clustering.evaluation.BICScore;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfAveragePairwiseSimilarities;
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
//        for (int i = 2; i < 30; i++) {
//            Clusterer km = new KMeans(i);
//            Dataset[] clusters = km.cluster(ds);
//            System.out.println("Length: " + clusters.length);
//            ClusterEvaluation aic = new AICScore();
//            ClusterEvaluation bic = new BICScore();
//            ClusterEvaluation sse = new SumOfSquaredErrors();
//            ClusterEvaluation saps = new SumOfAveragePairwiseSimilarities();
//            double aicScore = aic.score(clusters);
//            double bicScore = bic.score(clusters);
//            double sseScore = sse.score(clusters);
//            double sapsScore = saps.score(clusters);
//            System.out.println("AIC Score: " + aicScore);
//            System.out.println("BIC Score: " + bicScore);
//            System.out.println("SSE Score: " + sseScore);
//            System.out.println("SAPS Score: " + sapsScore);
//        }
        Clusterer km = new KMeans(10);
        Dataset[] clusters = km.cluster(ds);
        for (int i = 0; i < clusters.length; i++) {
            int index = i + 1;
            DatasetToMatrix sdm = new DatasetToMatrix(clusters[i]);
            System.out.println("\nCluster "+index+" Number of samples: "+sdm.samples+"\n");
            sdm.printMatrix();
            sdm.printCentroid();
            
        }

    }

}

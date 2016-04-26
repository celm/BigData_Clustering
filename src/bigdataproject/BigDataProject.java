/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.correlation.Covariance;

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
        read.readFromFile();
        read.filter();
        if(!read.checkHashMap()){
            System.out.println("DIOCANE");
        }

//        BlockRealMatrix matrix = new BlockRealMatrix(read.matrix);
//        Covariance cov = new Covariance(matrix, false);
//        RealMatrix covarianceMatrix = cov.getCovarianceMatrix();

//        EigenDecomposition dec = new EigenDecomposition(covarianceMatrix);
//        double[] eigenValues = dec.getRealEigenvalues();
//        RealVector principalEigenVector = dec.getEigenvector(0);
//        RealVector secondEigenVector = dec.getEigenvector(1);
//        BlockRealMatrix pca = new BlockRealMatrix(principalEigenVector.getDimension(), 2);
//        pca.setColumnVector(0, principalEigenVector);
//        pca.setColumnVector(1, secondEigenVector);
//        BlockRealMatrix matrixTranspose = matrix.transpose();
//        BlockRealMatrix pcaTranspose = pca.transpose();
//        BlockRealMatrix matrix2D = pcaTranspose.multiply(matrixTranspose);
//        CreateCSV writeToFile = new CreateCSV(matrix2D.getData(), "matrix");
//        writeToFile.writeToFile();

//        System.out.println(covDouble.length);
//        System.out.println(covDouble[0].length);
//        for (int k = 0; k < eigenVectorsDouble.length; k++) {
//            System.out.print("[");
//            for (int w = 0; w < eigenVectorsDouble[k].length; w++) {
//                System.out.print(eigenVectorsDouble[k][w] + " ");
//            }
//            System.out.print("]\n");
//        }
//iterate changing k
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
//        Clusterer km = new KMeans(10);
//        Dataset[] clusters = km.cluster(ds);
//        for (int i = 0; i < clusters.length; i++) {
//            int index = i + 1;
//            DatasetToMatrix sdm = new DatasetToMatrix(clusters[i]);
//            System.out.println("\nCluster "+index+" Number of samples: "+sdm.samples+"\n");
//            sdm.printMatrix();
//            sdm.printCentroid();
//            
//        }
    }

}

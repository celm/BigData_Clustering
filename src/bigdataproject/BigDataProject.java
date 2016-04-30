/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;


import java.util.HashMap;
import java.util.List;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author MarcoM
 */
public class BigDataProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReadDataSet read = new ReadDataSet();
        read.readFromFile();
        read.filter();
        double[][] matrix = read.getMatrix();
        System.out.println("SAMPLES: "+matrix.length);
        System.out.println("DIM: "+matrix[0].length);
        PCA pca = new PCA(matrix);
        double[][] matrix2DPCA = pca.reduceDimensions();
        CreateCSV writeToFile = new CreateCSV(matrix2DPCA, "matrix");
        writeToFile.writeToFile();
        BlockRealMatrix pcaMatrix = new BlockRealMatrix(matrix2DPCA);
        BlockRealMatrix pcaMatrixTranspose = pcaMatrix.transpose();
        KDistances dist = new KDistances(pcaMatrixTranspose.getData());
        dist.calculateDistances();
        dist.getKSortedNearestNeighbors(5);
        int minPts = 5;
        double eps =  dist.disancesMaxDifference();
        System.out.println("EPS: "+eps);
        List<DoublePoint> list = read.getCollection(read.getHashMap(pcaMatrixTranspose.getData()));
        DBSCANClusterer dbscan = new DBSCANClusterer(0.985, minPts);
        List<Cluster<DoublePoint>> clusterList = dbscan.cluster(list);
        int i = 1;
        System.out.println("CLUSTERS: "+clusterList.size());
        for (Cluster<DoublePoint> c : clusterList) {
                List cluster = c.getPoints();
                int size = cluster.size();
                System.out.println("\nCLUSTER N."+i+" POINTS: "+size+"\n");
                for(Object p : cluster){
                }
                i++;
        }
        final ScatterPlot demo = new ScatterPlot("Big Data Clustering Project", matrix2DPCA, clusterList);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
    }
}

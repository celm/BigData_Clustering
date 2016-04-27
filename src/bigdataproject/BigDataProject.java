/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;


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
        int eps = 100;
        int minPts = 54;
        KDistances dist = new KDistances(matrix);
        dist.calculateDistances();
        double[] array = dist.getKSortedNearestNeighbors(5);
        final ScatterPlot demo = new ScatterPlot("Big Data Clustering Project", matrix2DPCA);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        //dist.printArray(array);
//        DBSCANClusterer dbscan = new DBSCANClusterer(eps, minPts);
//        List<Cluster<DoublePoint>> clusterList = dbscan.cluster(list);
//        for (Cluster<DoublePoint> p : clusterList) {}
    }
}

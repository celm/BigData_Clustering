/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import org.apache.commons.math3.ml.clustering.DBSCANClusterer;

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
        PCA pca = new PCA(read.getMatrix());
        double[][] matrix2DPCA = pca.reduceDimensions();
        CreateCSV writeToFile = new CreateCSV(matrix2DPCA, "matrix");
        writeToFile.writeToFile();
        int eps = 0;
        int minPts = 0;
        DBSCANClusterer dbscan = new DBSCANClusterer(eps, minPts);
        dbscan.cluster(null);

    }

}

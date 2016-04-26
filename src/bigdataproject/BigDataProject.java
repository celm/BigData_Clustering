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
        ReadDataSet read = new ReadDataSet();
        read.readFromFile();
        read.filter();
        PCA pca = new PCA(read.getMatrix());
        double[][] matrix2DPCA = pca.reduceDimension();
        CreateCSV writeToFile = new CreateCSV(matrix2DPCA, "matrix");
        writeToFile.writeToFile();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.stat.correlation.Covariance;

/**
 *
 * @author raffaele
 */
public class PCA {
    double[][] dataSet;

    public PCA(double[][] dataSet) {
        this.dataSet = dataSet;
    }
    
    public double[][] reduceDimension(){
        BlockRealMatrix matrix = new BlockRealMatrix(dataSet);
        Covariance cov = new Covariance(matrix, false);
        RealMatrix covarianceMatrix = cov.getCovarianceMatrix();
        EigenDecomposition dec = new EigenDecomposition(covarianceMatrix);
        RealVector principalEigenVector = dec.getEigenvector(0);
        RealVector secondEigenVector = dec.getEigenvector(1);
        BlockRealMatrix pca = new BlockRealMatrix(principalEigenVector.getDimension(), 2);
        pca.setColumnVector(0, principalEigenVector);
        pca.setColumnVector(1, secondEigenVector);
        BlockRealMatrix matrixTranspose = matrix.transpose();
        BlockRealMatrix pcaTranspose = pca.transpose();
        BlockRealMatrix matrix2D = pcaTranspose.multiply(matrixTranspose);
        return matrix2D.getData();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

/**
 *
 * @author raffaele
 */
public class KDistances {

    double[][] samples;
    double[][] distanceMatrix;
    double[] Karray;

    public KDistances(double[][] samples) {
        if (samples != null) {
            this.samples = samples;
            int size = samples.length;
            distanceMatrix = new double[size][size];
        }
    }

    public void calculateDistances() {
        for (int i = 0; i < samples.length; i++) {
            double[] point = samples[i];
            for (int j = i + 1; j < samples.length; j++) {
                double[] point2 = samples[j];
                EuclideanDistance dist = new EuclideanDistance();
                distanceMatrix[i][j] = dist.compute(point, point2);
            }
        }
        BlockRealMatrix dist = new BlockRealMatrix(distanceMatrix);
        BlockRealMatrix distTranspose = dist.transpose();
        dist = dist.add(distTranspose);
        distanceMatrix = dist.getData();
    }

    void getKSortedNearestNeighbors(int k) {
        double[] allK = new double[k * samples.length];
        int index = 0;
        for (double[] distRow : distanceMatrix) {
            Arrays.sort(distRow);
            double[] subK = Arrays.copyOfRange(distRow, 1, k + 1);
            for (int j = 0; j < subK.length; j++) {
                final double value = subK[j];
                if (!DoubleStream.of(allK).anyMatch(x -> x == value)) {
                    allK[index++] = value;
                }
            }
        }
        double[] finalArray = Arrays.copyOfRange(allK, 0, index);
        Arrays.sort(finalArray);
        Karray = finalArray;
    }

    //debug function
    void printDistanceMatrix() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print("|" + distanceMatrix[i][j]);
            }
            System.out.println();
        }
    }

    double disancesMaxDifference() {
        double diff = 0.0;
        double epsilon = 0.0;
        int index = 0;
        for (int i = 1; i < Karray.length; i++) {
            double dist = Karray[i] - Karray[i - 1];
            if (dist > diff) {
                diff = dist;
                index = i - 1;
                epsilon = Karray[i - 1];
            }
        }
        return epsilon;
    }

    //debug function
    void printArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "|");
            }
            System.out.println();
        }
    }

    void printMatrixFloat(float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "|");
            }
            System.out.println();
        }
    }

}

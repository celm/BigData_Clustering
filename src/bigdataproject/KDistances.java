/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.collections.transformation.SortedList;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

/**
 *
 * @author raffaele
 */
public class KDistances {

    double[][] samples;
    double[][] distanceMatrix;

    public KDistances(double[][] samples) {
        this.samples = samples;
        int size = samples.length;
        distanceMatrix = new double[size][size];
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
        Random rand = new Random();
        double [] point = distanceMatrix[rand.nextInt(distanceMatrix.length -1)];
        Arrays.sort(point);
        printArray(point);
    }

    public double[] getKSortedNearestNeighbors(int k) {
        double[] allK = new double[k * samples.length];
        int index = 0;
        for (double[] distRow : distanceMatrix) {
            Arrays.sort(distRow);
            double[] subK = Arrays.copyOfRange(distRow, 1, k+1);
            for (int j = 0; j < subK.length; j++) {
                final double value = subK[j];
                if (!DoubleStream.of(allK).anyMatch(x -> x == value)) {
                    allK[index++] = value;
                }
            }
        }
        double[] finalArray =  Arrays.copyOfRange(allK, 0, index);
        Arrays.sort(finalArray);
        return finalArray;
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
    
    //debug function
    void printArray(double[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

}

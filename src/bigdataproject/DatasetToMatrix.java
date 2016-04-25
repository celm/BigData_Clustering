/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

/**
 *
 * @author raffaele
 */
class DatasetToMatrix {

    Dataset ds;
    int samples;
    int attributes;
    double[][] matrix;

    public DatasetToMatrix(Dataset ds) {
        this.ds = ds;
        attributes = ds.noAttributes();
        samples = ds.size();
        int i = 0, j = 0;
        matrix = new double[samples][attributes];
        for (Instance temp : ds) {
            for (Integer sample : temp.keySet()) {
                matrix[i][j] = temp.get(sample);
                j++;
            }
            i++;
            j = 0;
        }
    }

    void printMatrix() {
        for (int k = 0; k < samples; k++) {
            System.out.print("[");
            for (int w = 0; w < attributes; w++) {
                System.out.print(matrix[k][w] + " ");
            }
            System.out.print("]\n");
        }
    }

    double[] getCentroid() {
        double[] centroid = new double[attributes];
        double sum = 0.0;
        for (int j = 0; j < attributes; j++) {
            for (int i = 0; i < samples; i++) {
                sum += matrix[i][j];
            }
            centroid[j] = sum / samples;
            sum = 0.0;
        }
        return centroid;
    }

    void printCentroid() {
        double[] centroid = getCentroid();
        System.out.print("Centroid: \n[");
        for (int w = 0; w < centroid.length; w++) {
            System.out.print(centroid[w] + " ");
        }
        System.out.print("]\n");
    }
}

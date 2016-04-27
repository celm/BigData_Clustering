/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author raffaele
 */
public class CreateCSV {
    double[][] matrix;
    String fileName;
    List clusters;

    public CreateCSV(double[][] matrix, String fileName) {
        this.matrix = matrix;
        this.fileName = fileName;
    }

    public CreateCSV(List clusters, String fileName) {
        this.clusters = clusters;
        this.fileName = fileName;
    }

    void writeClusters() {
        if (clusters != null) {
            try {
                FileWriter file = new FileWriter(fileName + ".csv");

            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    void writeToFile() {
        if (matrix != null) {
            try {
                FileWriter file = new FileWriter(fileName + ".csv");
                file.append("X1,X2\n");
                for (int j = 0; j < matrix[0].length; j++) {
                    for (int i = 0; i < matrix.length; i++) {
                        String append = i == 0 ? matrix[i][j] + "," : matrix[i][j] + "\n";
                        file.append(append);
                    }
                }
                file.flush();
                file.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            System.err.println("2D Matrix Not Found");
        }
    }
}



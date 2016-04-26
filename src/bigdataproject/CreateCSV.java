/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author raffaele
 */
public class CreateCSV {

    double[][] matrix;
    String fileName;

    public CreateCSV(double[][] matrix, String fileName) {
        this.matrix = matrix;
        this.fileName = fileName;
    }

    void writeToFile() {
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
            System.err.println("Damn! It is an error on writing into file :(");
        }
    }

}

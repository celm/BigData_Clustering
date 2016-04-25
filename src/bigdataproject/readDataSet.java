/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

/**
 *
 * @author MarcoM
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class readDataSet {

    double[][] matrix;

    public void run() {

        String csvFile = "src/bigdataproject/wiki4HE.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int N_rows = 913;//no first row
        int N_columns = 53;
        matrix = new double[N_rows][N_columns];
        try {
            br = new BufferedReader(new FileReader(csvFile));
            for (int i = 0; i < N_rows; i++) {
                line = br.readLine();
                if (line != null) {
                    String[] tokens = line.split(cvsSplitBy);
                    for (int j = 0; j < N_columns; j++) {
                        if (!tokens[j].equals("?")) {
                            matrix[i][j] = Double.parseDouble(tokens[j]);;
                        } else {
                            matrix[i][j] = -1.0;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Done");
        }
    }

}

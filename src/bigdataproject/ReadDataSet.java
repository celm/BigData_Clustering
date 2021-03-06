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
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.math3.ml.clustering.DoublePoint;

public class ReadDataSet {

    int N_columns = 50;
    int N_rows = 913;
    HashMap<Integer, double[]> samples = new HashMap();

    /*
     * load dataset from csv file and select only samples having missing values
     * lower than 10% of their features
     */
    public void readFromFile() {
        String csvFile = "wiki4HE2.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            for (int i = 0; i < N_rows; i++) {
                line = br.readLine();
                if (line != null) {
                    String[] tokens = line.split(cvsSplitBy);
                    double[] row = new double[N_columns];
                    double missing = 0.0;
                    double value;
                    for (int j = 0; j < N_columns; j++) {
                        if (!tokens[j].equals("?")) {
                            value = Double.parseDouble(tokens[j]);
                        } else {
                            value = -1.0;
                            missing++;
                        }
                        row[j] = value;
                    }
                    double percentage = (missing / N_columns) * 100;
                    if (percentage < 10.0) {
                        samples.put(i, row);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("File Not Found or bad file format\n"
                    + "Make sure the file named wiki4HE2.csv is on the same path of the executable jar\n" + e);
            System.exit(0);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Something worng happened with the csv file\n" + e);
                    System.exit(0);
                }
            }
        }
    }

    //debug function
    void printHashMap() {
        System.out.println("Samples: " + samples.size());
        samples.keySet().stream().map((key) -> samples.get(key)).map((sample) -> {
            for (int i = 0; i < sample.length; i++) {
                System.out.print(sample[i] + "|");
            }
            return sample;
        }).forEach((_item) -> {
            System.out.println();
        });
    }
    //double matrix to HashMap
    HashMap<Integer, double[]> getHashMap(double[][] matrix) {
        HashMap<Integer, double[]> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, matrix[i]);
        }
        return map;
    }

    //debug function
    boolean checkHashMap() {
        for (Integer key : samples.keySet()) {
            double[] sample = samples.get(key);
            for (int i = 0; i < sample.length; i++) {
                if (sample[1] == -1.0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //from HashMap to double matrix
    double[][] getMatrix() {
        double[][] matrixToReturn = new double[samples.size()][samples.get(0).length];
        int i = 0;
        for (Integer key : samples.keySet()) {
            double[] sample = samples.get(key);
            System.arraycopy(sample, 0, matrixToReturn[i], 0, sample.length);
            i++;
        }
        return matrixToReturn;
    }
    //HashMap to List Collection useful as input for clustering algorithm objects
    List<DoublePoint> getCollection(HashMap<Integer, double[]> map) {
        List<DoublePoint> list = new ArrayList<>();
        map.keySet().stream().map((key) -> new DoublePoint(map.get(key))).forEach((p) -> {
            list.add(p);
        });
        return list;
    }

    /*
     * replace missing values with the following criteria:
     * feature 2: (domain) (eg. art, science..) replace with 0 (unknown domain)
     * feature 4: (years of experience) replaced with average of values for that dimension
     * feature 6: (wikipedia user 0 or 1) replaced with 0 (values with 0 = 89% of remaining values)
     * feature 7-49 (survey answer values from 1 to 5) average of values for that dimension
     */
    void filter() {
        double[] sum = new double[N_columns];
        double[] missing = new double[N_columns];
        samples.keySet().stream().map((key) -> samples.get(key)).forEach((row) -> {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == -1.0) {
                    missing[i]++;
                } else {
                    sum[i] += row[i];
                }
            }
        });
        samples.keySet().stream().forEach((key) -> {
            double[] row = samples.get(key);
            for (int i = 0; i < row.length; i++) {
                if (row[i] == -1.0) {
                    if (i == 2 || i == 6) {
                        samples.get(key)[i] = 0.0;
                    } else {
                        samples.get(key)[i] = (double) Math.round(sum[i] / ((double) N_rows - missing[i]));
                    }
                }
            }
        });
    }
}

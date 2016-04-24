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
public class BigDataProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readDataSet read = new readDataSet();
        read.run();
        for(int i=0; i<read.matrix.length; i++){
            for(int j=0 ; j<read.matrix[0].length;j++){
                System.out.print(read.matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}

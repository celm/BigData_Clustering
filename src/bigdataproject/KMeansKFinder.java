/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

/**
 *
 * @author raffaele
 */
public class KMeansKFinder {

    List<DoublePoint> list;
    int numSamples;

    public KMeansKFinder(List<DoublePoint> list) {
        this.list = list;
        numSamples = list.size();
    }

    public int find(double epsilon) {
        double oldAvDist = 0.0;
        for (int k = 2; k < 100; k++) {
            KMeansPlusPlusClusterer kmeans = new KMeansPlusPlusClusterer(k, 100, new EuclideanDistance());
            List<Cluster<DoublePoint>> clusterList = kmeans.cluster(list);
            double[] avDistances = new double[k];
            int index = 0;
            System.out.println("\nSUDDIVISION K: "+k+"\n");
            for (Cluster<DoublePoint> c : clusterList) {
                List cluster = c.getPoints();
                int size = cluster.size();
                double[] centroid = getCentroid(cluster);
                System.out.println("CENTROID CLUSTER N."+(index+1));
                printVector(centroid);
                double distanceSum = 0.0;
                for (Object p : cluster) {
                    DoublePoint point = (DoublePoint) p;
                    double[] pointDouble = point.getPoint();
                    EuclideanDistance dist = new EuclideanDistance();
                    distanceSum += dist.compute(centroid, pointDouble);
                }
                avDistances[index] = distanceSum / size;
                System.out.println("AVERAGE DISTANCE: "+avDistances[index]);
                index++;
            }
            double avDistSum = 0.0;
            for(int i = 0; i < avDistances.length; i++){
                avDistSum += avDistances[i];
            }
            double newAvDist = avDistSum / avDistances.length;
            double difference = Math.abs(newAvDist - oldAvDist);
            System.out.println("AVERAGE DISTANCES: "+newAvDist+" OLD AVERAGE: "+oldAvDist+" DIFFERENCE: "+difference);
            if(difference >= epsilon){
                oldAvDist = newAvDist;
            }
            else
                return k-1;
        }
        return 0;
    }

    private double[] getCentroid(List cluster) {
        double size = (double) cluster.size();
        double x = 0.0, y = 0.0;
        for (Object p : cluster) {
            DoublePoint point = (DoublePoint) p;
            double[] pointDouble = point.getPoint();
            x += pointDouble[0];
            y += pointDouble[1];
        }
        double[] centroid = new double[2];
        centroid[0] = x / size;
        centroid[1] = y / size;
        return centroid;
    }
    
    private void printVector(double[] vector){
        for(int i = 0; i < vector.length; i++){
            System.out.print(vector[i]+"|");
        }
    }

}

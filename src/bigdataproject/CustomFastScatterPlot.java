/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author raffaele
 */
public class CustomFastScatterPlot extends FastScatterPlot {

    HashMap<Integer, float[][]> clusters;
    Color[] colors = new Color[]{
        Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,Color.MAGENTA,
        Color.BLACK, Color.PINK, Color.ORANGE,Color.LIGHT_GRAY,Color.GRAY,
        Color.CYAN,Color.DARK_GRAY
    };
    String[] colorArray = {"Red","Blue","Yellow","Green","Magenta","Black","Pink","Orange","Light Grey","Gray","Cyan","Dark Gray"};

    public CustomFastScatterPlot(float[][] data, ValueAxis domainAxis, ValueAxis rangeAxis, HashMap<Integer, float[][]> clusters) {
        super(data, domainAxis, rangeAxis);
        this.clusters = clusters;

    }

    @Override
    public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState) {     
        if (clusters != null) {
            int colorIndex = 0, points = 0;
            String shape = "";
            for (Integer index : clusters.keySet()) {
                float[][] clusterFloat = clusters.get(index);
                for (float[] clusterFloat1 : clusterFloat) {
                    points++;
                    float x = clusterFloat1[0];
                    float y = clusterFloat1[1];
                    int size = 6;
                    int transX = (int) this.getDomainAxis().valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
                    int transY = (int) this.getRangeAxis().valueToJava2D(y, dataArea, RectangleEdge.LEFT);
                    g2.setPaint(colors[colorIndex % 11]);
                    if(colorIndex % 2 == 0){
                        g2.fillOval(transX, transY, size, size);
                        shape = "Round";
                    }else{
                        g2.fillRect(transX, transY, size, size);
                        shape = "Square";
                    }    
                }
                System.out.println("Cluster number: "+colorIndex+" Points: "+clusterFloat.length+ " Shape: "+shape+" Color: "+colorArray[colorIndex % 11]);
                colorIndex++;
            }
            System.out.println("\nClustering done! Total clusters: "+colorIndex+" Total points: "+points+"\n");
        }
    }

}

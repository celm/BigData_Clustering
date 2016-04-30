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

    public CustomFastScatterPlot(float[][] data, ValueAxis domainAxis, ValueAxis rangeAxis, HashMap<Integer, float[][]> clusters) {
        super(data, domainAxis, rangeAxis);
        this.clusters = clusters;

    }

    @Override
    public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState) {
        //g2.setPaint(Color.BLUE);

        if (clusters != null) {
            int colorIndex = 0;
            for (Integer index : clusters.keySet()) {
                float[][] clusterFloat = clusters.get(index);
                for (int i = 0; i < clusterFloat.length; i++) {
                    float x = clusterFloat[i][0];
                    float y = clusterFloat[i][1];
                    int size = 5;
                    int transX = (int) this.getDomainAxis().valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
                    int transY = (int) this.getRangeAxis().valueToJava2D(y, dataArea, RectangleEdge.LEFT);
                    g2.setPaint(colors[colorIndex % 11]);
                    g2.fillOval(transX, transY, size, size);
               
                }
                colorIndex++;
            }
        }
    }

}

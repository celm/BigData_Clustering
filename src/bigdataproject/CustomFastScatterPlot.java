/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.ui.RectangleEdge;

/**
 *
 * @author raffaele
 */
public class CustomFastScatterPlot extends FastScatterPlot{

    public CustomFastScatterPlot(float[][] data, ValueAxis domainAxis, ValueAxis rangeAxis) {
        super(data, domainAxis, rangeAxis);
        
    }
    
    @Override
   public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState) {
       //g2.setPaint(Color.BLUE);

       if (this.getData() != null) {
           for (int i = 0; i < this.getData()[0].length; i++) {
               float x = this.getData()[0][i];
               float y = this.getData()[1][i];
               int size = 5;
               int transX = (int) this.getDomainAxis().valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
               int transY = (int) this.getRangeAxis().valueToJava2D(y, dataArea, RectangleEdge.LEFT);
               g2.setPaint(Color.RED);
               g2.fillOval(transX, transY, size, size);
           }
       }
   }
    
                
    
}

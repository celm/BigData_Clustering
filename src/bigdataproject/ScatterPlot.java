/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdataproject;

/**
 *
 * @author raffaele
 */
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Shape;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.ShapeUtilities;
/**
 * A demo of the fast scatter plot.
 *
 */
public class ScatterPlot extends ApplicationFrame {

    /**
     * The data.
     */
    private float[][] data;

    /**
     * Creates a new fast scatter plot.
     *
     * @param title the frame title.
     * @param samples the 2D matrix to plot.
     */
    public ScatterPlot(final String title, double[][]samples) {

        super(title);
        convertToFloat(samples);
        final NumberAxis domainAxis = new NumberAxis("X1");
        domainAxis.setAutoRangeIncludesZero(false);
        final NumberAxis rangeAxis = new NumberAxis("X2");
        rangeAxis.setAutoRangeIncludesZero(false);
        final CustomFastScatterPlot plot = new CustomFastScatterPlot(this.data, domainAxis, rangeAxis);
        final JFreeChart chart = new JFreeChart(title, plot);
        //chart.setLegend(null);

        // force aliasing of the rendered content..
        chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final ChartPanel panel = new ChartPanel(chart, true);
        panel.setPreferredSize(new java.awt.Dimension(1000, 540));
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(20);
        panel.setMaximumDrawWidth(2000);
        setContentPane(panel);
        

    }
    
    private void convertToFloat(double[][] matrix) {
        data = new float[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                data[i][j] = (float) matrix[i][j];
            }
        }
    }
}

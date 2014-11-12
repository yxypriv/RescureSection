package KNNvisualization.demos.javaplotDemos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
 

import javax.swing.JFrame;
 

import com.panayotis.gnuplot.GNUPlotParameters;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.swing.JPlot;
 
// include JavaPlot.jar from this site to your classpath
// http://gnujavaplot.sourceforge.net/JavaPlot/About.html
 
public class InteractiveGnuPlot {
 
	public static void main(String[] args) {
		createJPanel();
	}
 
	private static void createJPanel() {
		GNUPlotParameters par = new GNUPlotParameters(true);
		String path = "D:/Program Files/gnuplot/bin/gnuplot.exe";
		JavaPlot javaPlot = new JavaPlot(path);
		javaPlot.newGraph3D();
		final JPlot plot = new JPlot(javaPlot);
		final JavaPlot p = plot.getJavaPlot();
		
		// 3d function
		p.addPlot("sin(x)*cos(y)");
		p.addPlot("sin(x)*sin(y)");
		
		p.getAxis("x").setLabel("Axis X");
		p.getAxis("y").setLabel("Axis Y");
		p.getAxis("z").setLabel("Axis Z");
		p.set("xr", "[-4:4]");
		p.set("yr", "[-4:4]");
		p.set("zr", "[-2:2]");
//		p.plot();
		plot.plot();
 
		plot.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
 
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				double rotX = (double) x / plot.getWidth() * 360;
				double rotY = (double) y / plot.getHeight() * 360;
				
				// range check
				if (rotX < 0) {
					rotX = 0;
				}
				if (rotX > 360) {
					rotX = 360;
				}
				if (rotY < 0) {
					rotY = 0;
				}
				if (rotY > 360) {
					rotY = 360;
				}
				// set view
				p.set("view", rotY + "," + rotX);
				// repaint
				plot.plot();
				plot.repaint();
			}
		});
 
		// pack and display frame
		JFrame f = new JFrame();
		f.getContentPane().add(plot);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
 
	}
 
}
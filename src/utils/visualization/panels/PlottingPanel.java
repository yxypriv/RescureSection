package utils.visualization.panels;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class PlottingPanel extends JPanel {
	List<List<double[]>> data;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}

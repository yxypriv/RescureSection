package perceptron.visualization;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import perceptron.algorithm.PerceptronAlgorithm;

public class PerceptronFrame extends JFrame {

	private static final long serialVersionUID = 347352325038131916L;

	public PerceptronAlgorithm algorithm;

	public PerceptronFrame() {
		initFrame();
		initTable();
	}

	public void initFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initTable() {
		JScrollPane scroll = new JScrollPane();
		JTable table = new JTable();
		Dimension dimension = new Dimension(300, 400);
		table.setSize(dimension);
		table.setPreferredSize(dimension);
		scroll.setPreferredSize(dimension);
		scroll.setSize(dimension);
		GeneralizedTableModel model = new GeneralizedTableModel();

		model.getTitle().add("a");
		model.getTitle().add("b");
		model.getData().add(new ArrayList<Object>());
		model.getData().get(0).add(12);
		model.getData().get(0).add("sss");

		table.setModel(model);
		scroll.setViewportView(table);

		add(table);
		add(scroll);
		table.repaint();
		repaint();
	}

	public static void main(String[] args) {
		new PerceptronFrame();
	}
}

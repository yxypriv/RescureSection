package perceptron.visualization;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import perceptron.SocketDataReceiver;
import perceptron.SocketDataReceiver.Processor;
import perceptron.algorithm.PerceptronAlgorithmOnlineBuff;
import perceptron.io.TicTacToeFeatureExtraction;
import perceptron.model.OnlinePerceptronData;

public class PerceptronFrame extends JFrame {

	private static final long serialVersionUID = 347352325038131916L;

	public PerceptronAlgorithmOnlineBuff algorithm;
	public SocketDataReceiver receiver;
	JLabel[] weightLabels;

	public PerceptronFrame() {
		initFrame();
		init();
	}

	public void initFrame() {
		setSize(800, 600);
		setResizable(false);
		getContentPane().setLayout(new GridLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initWeightLabels(int size) {

	}

	public void initButton() {
		JButton button = new JButton();
		button.setSize(100, 50);
		button.setText("start listening");
		button.setPreferredSize(new Dimension(100, 50));
		button.setAction(new AbstractAction() {

			private static final long serialVersionUID = -9126243567079062935L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				receiver.start(new Processor() {
					@Override
					public void process(String line) {
						String[] split = line.split(",");
						char[] originalFeature = new char[9];
						for (int i = 0; i < 9; i++) {
							originalFeature[i] = split[i].charAt(0);
						}
						String label = split[9];

						OnlinePerceptronData data = new OnlinePerceptronData(//
								TicTacToeFeatureExtraction.extractFeature(originalFeature),//
								label, algorithm.getHistorySize());
						// allData.add(data);
						algorithm.train(data);
					}
				});
			}
		});
		getContentPane().add(button, GridBagConstraints.LINE_START);
		repaint();
	}

	public void init() {
		algorithm = new PerceptronAlgorithmOnlineBuff();
		receiver = new SocketDataReceiver();

	}

	public static void main(String[] args) {
		new PerceptronFrame();
	}
}

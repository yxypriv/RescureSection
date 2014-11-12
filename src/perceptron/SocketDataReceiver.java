package perceptron;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import perceptron.algorithm.PerceptronAlgorithmOnlineBuff;
import perceptron.io.TicTacToeFeatureExtraction;
import perceptron.model.OnlinePerceptronData;
import utils.io.FileUtil;
import utils.io.FileUtil.FileLineProcess;

public class SocketDataReceiver {
	PerceptronAlgorithmOnlineBuff algorithm = new PerceptronAlgorithmOnlineBuff();
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(1234);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			socket = serverSocket.accept();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		final List<String> lines = new ArrayList<String>();
		try {
			FileUtil.iterateStreamByLine(socket.getInputStream(), new FileLineProcess() {
				@Override
				public boolean process(String line) {
					if(null == line)
						return false;
					System.out.println(line);
					String[] split = line.split(",");
					char[] originalFeature = new char[9];
					for (int i = 0; i < 9; i++) {
						originalFeature[i] = split[i].charAt(0);
					}
					String label = split[9];

					OnlinePerceptronData data = new OnlinePerceptronData(//
							TicTacToeFeatureExtraction.extractFeature(originalFeature),//
							label, algorithm.getHistorySize());
//					allData.add(data);
					algorithm.train(data);
					algorithm.errorRate();

					return true;
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		System.out.println(lines.size());
		
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SocketDataReceiver receiver = new SocketDataReceiver();
		receiver.start();
	}
}

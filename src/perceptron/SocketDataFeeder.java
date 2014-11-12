package perceptron;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.io.FileUtil;
import utils.io.FileUtil.FileLineProcess;

public class SocketDataFeeder {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Socket socket = null;
		try {
			socket = new Socket("localhost", 1234);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final PrintWriter writer = new PrintWriter(outputStream);
		FileUtil.iterateResourceFileByLine("perceptron/tic-tac-toe.data.txt", new FileLineProcess() {
			@Override
			public boolean process(String line) {
				if(null == line)
					return false;
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				writer.println(line);
				writer.flush();
//				System.out.println(line);
				return true;
			}
		});
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

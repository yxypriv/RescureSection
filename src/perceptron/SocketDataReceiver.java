package perceptron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utils.io.FileUtil;
import utils.io.FileUtil.FileLineProcess;

public class SocketDataReceiver {
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
		final List<String> lines = new ArrayList<String>();
		try {
			FileUtil.iterateStreamByLine(socket.getInputStream(), new FileLineProcess() {
				@Override
				public boolean process(String line) {
					if(null == line)
						return false;
					System.out.println(line);
					lines.add(line);
					return true;
				}
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(lines.size());
		
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
	}
}

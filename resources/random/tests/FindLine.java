package random.tests;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class FindLine {

	public static void main(String[] args) {

		AudioFormat format = new AudioFormat(22000, 16, 2, true, true);
		TargetDataLine line;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format
																				// object
		if (!AudioSystem.isLineSupported(info)) {
			// Handle the error ...
		}
		// Obtain and open the line.
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
//			float level = line.getLevel();
//			System.out.println(level);
			AudioInputStream audioInputStream = new AudioInputStream(line);
			line.open(format);
			line.start();
			while (true) {
//				System.out.println(audioInputStream.getFormat());
				try {
					byte[] buff = new byte[4];
					audioInputStream.read(buff);
					int temp = buff[3];
					System.out.println(temp + 128);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}

}
package random;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class MicrophoneInterruption {
	static AudioFormat format = new AudioFormat(22000, 16, 2, true, true);
	static TargetDataLine line;
	static DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	private static AudioInputStream audioInputStream;

	static {
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		// float level = line.getLevel();
		// System.out.println(level);
		audioInputStream = new AudioInputStream(line);
		try {
			line.open(format);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		line.start();

	}

	public static synchronized int nextMircophoneInterruption() {
		byte[] buff = new byte[4];
		try {
			audioInputStream.read(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int temp = buff[3];
		return (temp + 128) % 2;
	}
}

package utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class FileUtil {
	public static interface FileLineProcess {
		/**
		 * @param line
		 * @return true: continue literation
		 * 			false: break;
		 */
		public boolean process(String line);
	}

	public static void iterateResourceFileByLine(String resourcePath, FileLineProcess process) {
		InputStream stream = FileUtil.class.getResourceAsStream(resourcePath);
		iterateStreamByLine(stream, process);
	}

	public static void iteratePathFileByLine(String path, FileLineProcess process) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		iterateStreamByLine(stream, process);
	}

	public static void iterateStreamByLine(InputStream stream, FileLineProcess process) {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				if (!process.process(line))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package qn.qsharing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

public class Getter extends Thread {
	
	public String ip;
	
	public void run() {
		try {
			Socket fileNameSocket = new Socket(ip, 1119);
			InputStream fileNameData = fileNameSocket.getInputStream();
			String name = IOUtils.toString(fileNameData, "EUC-KR");
			fileNameSocket.close();
			
			try {
				Path path = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "\\SharedFiles");
				Files.createDirectories(path);
			} catch (FileAlreadyExistsException e) {
				System.out.println("ERROR: Already Existing File '" + System.getProperty("user.dir") + "\\SharedFiles'");
			}
			
			Socket fileSocket = new Socket(ip, 1119);
			InputStream fileData = fileSocket.getInputStream();
			OutputStream fileStream = new FileOutputStream(System.getProperty("user.dir") + "\\SharedFiles\\" + name);
			IOUtils.copy(fileData, fileStream);
			fileStream.close();
			fileSocket.close();
		} catch (UnknownHostException e) {
			System.out.println("ERROR: Invalid IP");
		} catch (IOException e) {
			System.out.println("ERROR: Unknown I/O Exception");
			System.out.println(e.getMessage());
		}
	}

}

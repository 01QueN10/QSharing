package qn.qsharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.io.IOUtils;

public class Sender extends Thread {
	
	public File file;
	
	public void run() {
		try {
			ServerSocket serverSocket;
			serverSocket = new ServerSocket(1119);
			
			Socket fileNameSocket = serverSocket.accept();
			OutputStream fileNameData = fileNameSocket.getOutputStream();
			fileNameData.write(file.getName().getBytes());
			fileNameSocket.close();
		
			Socket fileSocket = serverSocket.accept();
			OutputStream fileData = fileSocket.getOutputStream();
			InputStream fileStream = new FileInputStream(file);
			byte[] data = IOUtils.toByteArray(fileStream);
			fileData.write(data);
			fileStream.close();
			fileSocket.close();
			
			serverSocket.close();
		} catch (FileNotFoundException e) {
					System.out.println("ERROR: Mising File '" + file + "'");
		} catch (IOException e) {
			System.out.println("ERROR: Unknown I/O Exception");
			System.out.println(e.getMessage());
		}
	}
	
}
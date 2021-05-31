package kr.co.gda.webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static void main( String[] args ) {
    	Logger log = LoggerFactory.getLogger(App.class);
    //	log.info("debug test...");
    	
    	ServerSocket server = null;
    	Socket client = null;
    	BufferedReader in = null; // index.html파일을 읽어오는 InputStream
    	PrintWriter out = null; // 클라이언트에게 보낼 OutputStream
    	try {
    		server = new ServerSocket(9999);
    		while(true) {
    			log.info("브라우저 요청 대기중...");
    			client = server.accept();
    			
    			in = new BufferedReader(new FileReader("index.html"));
    			out = new PrintWriter(client.getOutputStream());
    			
    			String line = null;
    			
    			//out.println("HTTP/1.0 200 Document Follows \r\n");
    			while((line = in.readLine()) != null) {
    				out.println(line);
    				out.flush();
    			}
    			
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}

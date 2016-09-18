import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// 和本线程相关的Socket
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 线程执行的操作，响应客户端的请求
	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		String change = "";
		try {
			// 获取输入流，并读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;

			os = socket.getOutputStream();
			pw = new PrintWriter(os,true);
			while ((info = br.readLine()) != null) {// 循环读取客户端的信息
				for (int i = info.length() - 1; i > -1; i--) {
					change = change + String.valueOf(info.charAt(i));
				}
				System.out.println("客户端：" + info);
				pw.println(change);
			}

		} catch (IOException e) {
			System.out.println("断开链接。。。");

		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// �ͱ��߳���ص�Socket
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// �߳�ִ�еĲ�������Ӧ�ͻ��˵�����
	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		String change = "";
		try {
			// ��ȡ������������ȡ�ͻ�����Ϣ
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;

			os = socket.getOutputStream();
			pw = new PrintWriter(os,true);
			while ((info = br.readLine()) != null) {// ѭ����ȡ�ͻ��˵���Ϣ
				for (int i = info.length() - 1; i > -1; i--) {
					change = change + String.valueOf(info.charAt(i));
				}
				System.out.println("�ͻ��ˣ�" + info);
				pw.println(change);
			}

		} catch (IOException e) {
			System.out.println("�Ͽ����ӡ�����");

		}
	}

}

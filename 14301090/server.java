import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable {
	private Socket socket = null;
    private ServerSocket servers = null;
	public static void main(String[] args) throws IOException {

		server s = new server();
		Thread t = new Thread(s);
		t.start();
	}

	public server() throws IOException {
		// 1.����һ����������Socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�
		servers = new ServerSocket(3333);
	}

	@Override
	public void run() {
		try {
			// ��¼�ͻ��˵�����
			int count = 0;
			System.out.println("***�����������������ȴ��ͻ��˵�����***");
			// ѭ�������ȴ��ͻ��˵�����
			while (true) {
				// ����accept()������ʼ�������ȴ��ͻ��˵�����
				socket = servers.accept();
				// ����һ���µ��߳�
				ServerThread serverThread = new ServerThread(socket);
				// �����߳�
				serverThread.start();

				count++;// ͳ�ƿͻ��˵�����
				System.out.println("�ͻ��˵�������" + count);
				InetAddress address = socket.getInetAddress();
				System.out.println("��ǰ�ͻ��˵�IP��" + address.getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

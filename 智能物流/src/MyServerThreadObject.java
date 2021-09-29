import java.io.*;
import java.net.Socket;

/**
 * @author 宋景坤
 */
public class MyServerThreadObject extends Thread {
	Socket socket;
	ObjectInputStream r;
	ObjectOutputStream w;
	String a[][]=new String[101][10];
	//a= {{"1","2","3"}};
	public MyServerThreadObject(Socket socket) {
		a[1][1]="2019001";
		a[1][2]="张三";
		a[1][3]="79.5";
		a[2][1]="2019002";
		a[2][2]="李四";
		a[2][3]="86";
		try {
			w = new ObjectOutputStream(socket.getOutputStream());
			w.flush();
			r= new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run()
	{
		while(true)
		{
			try {
				Object s= r.readObject();
				StuMessage stuMessage = (StuMessage) s;
				String no=stuMessage.no,name="",balance="";
				float bal;
				for(int i=1;i<=100;i++)
				{
					if(a[i][1].equals(no))
					{
						name=a[i][2];
						balance=a[i][3];
						break;
					}
				}
				bal = Float.parseFloat(balance);
				stuMessage = new StuMessage(no,name,bal);
				w.writeObject(stuMessage);
//				w.writeObject(name);
//				w.writeObject(balance);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

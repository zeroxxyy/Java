import java.io.*;
import java.net.Socket;

/**
 * @author 宋景坤
 */
public class MyThreadObject extends Thread {
	Socket socket;
	ObjectInputStream r;
	ObjectOutputStream w;
	public MyThreadObject()
	{
		try {
			socket=new Socket("localhost",5826);
			//socket=new Socket("192.168.1.185",5826);
			w=new ObjectOutputStream(socket.getOutputStream());
			//w.flush();
			r=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void run()
	{
		String name="",balance="";
		int step=1;
		while(true)
		{
			try {
				Object s=r.readObject();
				StuMessage stuMessage = (StuMessage) s;
				System.out.println("name" + stuMessage.name);
				System.out.println("balance"+stuMessage.balance);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    void f0()
    {
    	try {
    		System.out.println("No.:"+"2019002");
			StuMessage stuMessage = new StuMessage("2019002");
			w.writeObject(stuMessage);
//    		w.writeObject("2019002");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

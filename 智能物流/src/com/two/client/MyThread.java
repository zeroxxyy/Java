package com.two.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author 宋景坤
 */
public class MyThread extends Thread {
	Socket socket;
	DataInputStream r;
	DataOutputStream w;
	public MyThread()
	{
		try {
			socket=new Socket("localhost",5826);
			//socket=new Socket("192.168.1.185",5826);
			w=new DataOutputStream(socket.getOutputStream());
			//w.flush();
			r=new DataInputStream(socket.getInputStream());
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
				String s=r.readUTF();
				if(step==1)
				{
					System.out.println("name: "+s);
					step=2;
				}
				else
				{
					System.out.println("balance: "+s);
					step=1;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    void f0()
    {
    	try {
    		System.out.println("No.:"+"2019002");
    		w.writeUTF("2019002");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

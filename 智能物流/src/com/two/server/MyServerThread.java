package com.two.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author 宋景坤
 */
public class MyServerThread extends Thread {
	Socket socket;
	DataInputStream r;
	DataOutputStream w;
	String a[][]=new String[101][10];
	//a= {{"1","2","3"}};
	public MyServerThread(Socket socket) {
		a[1][1]="2019001";
		a[1][2]="张三";
		a[1][3]="79.5";
		a[2][1]="2019002";
		a[2][2]="李四";
		a[2][3]="86";
		try {
			w = new DataOutputStream(socket.getOutputStream());
			w.flush();
			r=new DataInputStream(socket.getInputStream());
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
				String s=r.readUTF();
				String name="",balance="";
				for(int i=1;i<=100;i++)
				{
					if(a[i][1].equals(s))
					{
						name=a[i][2];
						balance=a[i][3];
						break;
					}
				}
				w.writeUTF(name);
				w.writeUTF(balance);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

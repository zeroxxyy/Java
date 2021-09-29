package com.two.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 宋景坤
 */
public class MyServer {
	static ServerSocket ss;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ss=new ServerSocket(5826);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("MyServer 17");
		Socket s;
		while(true)
		{
			try {
				s=ss.accept();
				MyServerThread cs=new MyServerThread(s);
				cs.start();
				System.out.println("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

package com.baosight.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class LoginService {
	/*
	 * 保存用户名的业务方法,成功返回true,失败返回false
	 * 参数 context 上下文
	 * 上下文就是一个类,提供一些方便的API,可以得到应用程序的环境:
	 *    环境包名,安装路径,文件路径,资源路径,资产路径等.
	 */
	public static boolean saveUserInfo(Context context,String user,String pwd){
		
		try{
			
			//File file = new File("/data/data/com.baosight.logon/info.txt");
			//context.getFilesDir();//帮助我们返回一个目录:/data/data/com.baosight.logon/files/"
			/*
			 * 参数1:路径
			 * 参数2:文件名
			 */
			File file = new File(context.getFilesDir(),"info.txt");	
			
			//把记住的用户放置缓存区
			//File file = new File(context.getCacheDir(),"info.txt");
			
			//FileOutputStream fos = new FileOutputStream(file);
			
			FileOutputStream fos =  context.openFileOutput("info.txt",Context.MODE_PRIVATE);
			fos.write((user+"##"+pwd).getBytes());
			fos.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * 获取保存的数据
	 */
	public static Map<String,String> getSaveUser(Context context){
		File file = new File(context.getFilesDir(),"info.txt");
		//File file = new File(context.getCacheDir(),"info.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String info = br.readLine();
			String[] userInfo = info.split("##");
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", userInfo[0]);
			map.put("password", userInfo[1]);
			br.close();
			fis.close();
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void removeUser(Context context){
		File file = new File(context.getFilesDir(),"info.txt");
		file.delete();
	}
	
}

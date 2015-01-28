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
	 * �����û�����ҵ�񷽷�,�ɹ�����true,ʧ�ܷ���false
	 * ���� context ������
	 * �����ľ���һ����,�ṩһЩ�����API,���Եõ�Ӧ�ó���Ļ���:
	 *    ��������,��װ·��,�ļ�·��,��Դ·��,�ʲ�·����.
	 */
	public static boolean saveUserInfo(Context context,String user,String pwd){
		
		try{
			
			//File file = new File("/data/data/com.baosight.logon/info.txt");
			//context.getFilesDir();//�������Ƿ���һ��Ŀ¼:/data/data/com.baosight.logon/files/"
			/*
			 * ����1:·��
			 * ����2:�ļ���
			 */
			File file = new File(context.getFilesDir(),"info.txt");	
			
			//�Ѽ�ס���û����û�����
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
	 * ��ȡ���������
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

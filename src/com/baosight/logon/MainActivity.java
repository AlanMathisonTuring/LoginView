package com.baosight.logon;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.baosight.service.LoginService;

public class MainActivity extends Activity {
	
	private EditText et_username;
	private EditText et_password;
	private CheckBox cb_remberpwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_username = (EditText)this.findViewById(R.id.et_username);
		et_password= (EditText)this.findViewById(R.id.et_password);
		cb_remberpwd = (CheckBox)findViewById(R.id.cb_remberpwd);
		
		//����Ƿ��б�����û���������������,�����,�ڿ���Ӧ�õ�ʱ�����û������봦���Գ���
		Map<String,String> userMap = LoginService.getSaveUser(this);
		
		if(userMap != null){
			et_username.setText(userMap.get("username"));
			et_password.setText(userMap.get("password"));
		}
		
	}

	public void logonIn(View v){
		String username = et_username.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
			Toast.makeText(this, "�û������벻��Ϊ��", Toast.LENGTH_SHORT).show();
		}else{
			//��¼(������Ϣ��������,��������֤�Ƿ���ȷ,���ж�)	ģ��
			if(("lml".equals(username)&&"123".equals(password)) || ("wxc".equals(username)&&"123".equals(password))){
				//�ж��Ƿ��ס����
				if(cb_remberpwd.isChecked()){
					//��������
					//Ӧ�ó������ļ�  ָ����data--data--���� ���ļ����´��
					boolean result = LoginService.saveUserInfo(this,username, password);
					if(result == true){
						Toast.makeText(this, "�����û���Ϣ�ɹ�", 0).show();
					}
				}else{
					//ɾ��������û���Ϣ
					LoginService.removeUser(this);
				}
				Toast.makeText(this, "��¼�ɹ�", 0).show();
				setContentView(R.layout.test_loginsuccess);
			}else{
				Toast.makeText(this, "��¼ʧ��,�û������������", 0).show();
			}
		}
	}

}

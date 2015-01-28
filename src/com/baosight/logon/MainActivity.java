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
		
		//检查是否有保存的用户名或者密码数据,如果有,在开启应用的时候在用户名密码处回显出来
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
			Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
		}else{
			//登录(发送消息到服务器,服务器验证是否正确,做判断)	模拟
			if(("lml".equals(username)&&"123".equals(password)) || ("wxc".equals(username)&&"123".equals(password))){
				//判断是否记住密码
				if(cb_remberpwd.isChecked()){
					//保存密码
					//应用程序存放文件  指定在data--data--包名 的文件夹下存放
					boolean result = LoginService.saveUserInfo(this,username, password);
					if(result == true){
						Toast.makeText(this, "保存用户信息成功", 0).show();
					}
				}else{
					//删除保存的用户信息
					LoginService.removeUser(this);
				}
				Toast.makeText(this, "登录成功", 0).show();
				setContentView(R.layout.test_loginsuccess);
			}else{
				Toast.makeText(this, "登录失败,用户名或密码错误", 0).show();
			}
		}
	}

}

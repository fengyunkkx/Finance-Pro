package proj.android.zyl.finance_pro.projx.ver01;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.PwdDAO;
import proj.android.zyl.finance_pro.model.Tb_pwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjXSysset extends Activity {
	EditText now_txtPwd1, now_txtPwd2;
	Button btnSet, btnsetCancel, btnClose;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_sysset);

		now_txtPwd1 = findViewById(R.id.now_txtPwd1);
		now_txtPwd2 = findViewById(R.id.now_txtPwd2);
		btnSet = findViewById(R.id.btnSet);
		btnsetCancel = findViewById(R.id.btnsetCancel);
		btnClose = findViewById(R.id.btnClose);


		btnSet.setOnClickListener(new OnClickListener() {			//设置按钮
			public void onClick(View arg0) {
				PwdDAO pwdDAO = new PwdDAO(ProjXSysset.this);

				Tb_pwd tb_pwd = new Tb_pwd(now_txtPwd1.getText().toString(),now_txtPwd1.getText().toString());

				if (pwdDAO.getCount() == 0) {
					pwdDAO.add(tb_pwd);
				}
				else if(now_txtPwd1.getText().toString().equals("")||now_txtPwd2.getText().toString().equals("")){
					Toast.makeText(ProjXSysset.this, "输入的密码为空", Toast.LENGTH_SHORT).show();
				}
				else if(!now_txtPwd1.getText().toString().equals(now_txtPwd2.getText().toString())){
					Toast.makeText(ProjXSysset.this, "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
					now_txtPwd1.setText("");
					now_txtPwd2.setText("");
				}
				else {
					pwdDAO.update(tb_pwd);

					Toast.makeText(ProjXSysset.this, "密码设置成功！", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});


		btnsetCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				now_txtPwd1.setText("");
				now_txtPwd1.setHint("请输入新密码");
				now_txtPwd2.setText("");
				now_txtPwd2.setHint("再次输入密码");
			}
		});

		btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();  //退出
			}
		});

	}
}

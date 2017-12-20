package proj.android.zyl.finance_pro.projx.ver01;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.PwdDAO;
import proj.android.zyl.finance_pro.model.Tb_pwd;
import proj.android.zyl.finance_pro.projx.ver01.ProjXActivity;
import proj.android.zyl.finance_pro.projx.ver01.ProjXLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjXRegister extends Activity { // 注册界面
	EditText userregister, pwdregister1, pwdregister2;
	Button btnclose, btnregister;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_register);

		userregister = findViewById(R.id.userRegister);
		pwdregister1 = findViewById(R.id.pwdRegister1);
		pwdregister2 = findViewById(R.id.pwdRegister2);
		btnregister = findViewById(R.id.btnRegister);
		btnclose = findViewById(R.id.btnClose);


		btnregister.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(ProjXRegister.this, ProjXActivity.class);
				PwdDAO pwdDAO = new PwdDAO(ProjXRegister.this);


				if(userregister.getText().toString().equals("")||pwdregister1.getText().toString().equals("")){
					Toast.makeText(ProjXRegister.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
				}
				else if(!pwdregister1.getText().toString().equals(pwdregister2.getText().toString())){
					Toast.makeText(ProjXRegister.this, "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
					pwdregister1.setText("");
					pwdregister2.setText("");
				}
				else if(pwdDAO.find(userregister.getText().toString()) != null){
					Toast.makeText(ProjXRegister.this, "此用户名已经被注册", Toast.LENGTH_SHORT).show();

				}
				else{
					Tb_pwd tb_pwd = new Tb_pwd(userregister.getText().toString(), pwdregister1.getText().toString());
					pwdDAO.add(tb_pwd);

					finish();
				}


				Toast.makeText(ProjXRegister.this, "注册成功，请重新登录",
						Toast.LENGTH_SHORT).show();
				intent = new Intent(ProjXRegister.this, ProjXLogin.class);
				startActivity(intent);
			}
		});

		userregister.setOnFocusChangeListener(new OnFocusChangeListener() {

			public void onFocusChange(View v, boolean hasFocus) {
				PwdDAO pwdDAO = new PwdDAO(ProjXRegister.this);
				if(pwdDAO.find(userregister.getText().toString()) != null){
					Toast.makeText(ProjXRegister.this, "此用户名已经被注册", Toast.LENGTH_SHORT).show();
				}
			}
		});
		/*


			public void onClick(View login) {





				PwdDAO pwdDAO = new PwdDAO(ProjXRegister.this);
				Tb_pwd tb_pwd = new Tb_pwd(userregister.getText().toString(), pwdregister.getText().toString());

				// 判断用户名和密码是否正确

				pwdDAO.add(tb_pwd);




			}

		});
		 */
		btnclose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}


	/*
	public void register(View view){
		//SQLiteDatabase db=dbHelper.getWritableDatabase();

		String newname =userregister.getText().toString();
		String password=pwdregister.getText().toString();
		if (CheckIsDataAlreadyInDBorNot(newname)) {
			Toast.makeText(this,"该用户名已被注册，注册失败",Toast.LENGTH_SHORT).show();
		}
		else {

			if (register(newname, password)) {
				Toast.makeText(this, "插入数据表成功", Toast.LENGTH_SHORT).show();
			}
		}
	}
	//向数据库插入数据
	public boolean register(String username,String password){
		SQLiteDatabase db= DBOpenHelper.getWritableDatabase();

		String sql = "insert into userData(name,password) value(?,?)";
		Object obj[]={username,password};
		db.execSQL(sql,obj);


		ContentValues values=new ContentValues();
		values.put("name",username);
		values.put("password",password);
		db.insert("userData",null,values);
		db.close();
		//db.execSQL("insert into userData (name,password) values (?,?)",new String[]{username,password});
		return true;
	}
	//检验用户名是否已存在
	public boolean CheckIsDataAlreadyInDBorNot(String value){
		SQLiteDatabase db = DBOpenHelper.getWritableDatabase();
		String Query = "Select * from userData where name =?";
		Cursor cursor = db.rawQuery(Query,new String[] { value });
		if (cursor.getCount()>0){
			cursor.close();
			return true;
		}
		cursor.close();
		return false;
	}
	 */
}
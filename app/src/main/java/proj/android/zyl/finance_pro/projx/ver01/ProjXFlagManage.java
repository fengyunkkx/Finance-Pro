package proj.android.zyl.finance_pro.projx.ver01;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.FlagDAO;
import proj.android.zyl.finance_pro.model.Tb_flag;
import proj.android.zyl.finance_pro.projx.ver01.ProjXShowinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjXFlagManage extends Activity {
	EditText txtFlag;
	Button btnEdit, btnDel;
	String strid;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_flagmanage);
		txtFlag = findViewById(R.id.txtFlagManage);
		btnEdit = findViewById(R.id.btnFlagManageEdit);
		btnDel = findViewById(R.id.btnFlagManageDelete);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		strid = bundle.getString(ProjXShowinfo.FLAG);
		final FlagDAO flagDAO = new FlagDAO(ProjXFlagManage.this);
		txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
		btnEdit.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Tb_flag tb_flag = new Tb_flag();
				tb_flag.setid(Integer.parseInt(strid));
				tb_flag.setFlag(txtFlag.getText().toString());
				flagDAO.update(tb_flag);
				Toast.makeText(ProjXFlagManage.this, "便签已修改！",	Toast.LENGTH_SHORT).show();
			}
		});

		btnDel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				flagDAO.detele(Integer.parseInt(strid));
				Toast.makeText(ProjXFlagManage.this, "便签已删除！",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}

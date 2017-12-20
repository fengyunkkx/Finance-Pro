package proj.android.zyl.finance_pro.projx.ver01;

import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.InaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_inaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXInfoManage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ProjXInaccountinfo extends Activity {		// 收入信息界面
	public static final String FLAG = "id";
	ListView lvinfo;
	String strType = "";
	Button btnClose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_inaccountinfo);
		lvinfo = findViewById(R.id.lvinaccountinfo);
		ShowInfo(R.id.btnininfo);

		btnClose = findViewById(R.id.btnClose);

		lvinfo.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());
				String strid = strInfo.substring(0, strInfo.indexOf('-'));
				Intent intent = new Intent(ProjXInaccountinfo.this, ProjXInfoManage.class);
				intent.putExtra(FLAG, new String[] { strid, strType });
				startActivity(intent);
			}
		});

		btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();  //退出
			}
		});
	}

	private void ShowInfo(int intType) {
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		strType = "btnininfo";
		InaccountDAO inaccountinfo = new InaccountDAO(ProjXInaccountinfo.this);
		List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
				(int) inaccountinfo.getCount());
		strInfos = new String[listinfos.size()];
		int m = 0;
		for (Tb_inaccount tb_inaccount : listinfos) {
			strInfos[m] = tb_inaccount.getid() + "-" + tb_inaccount.getType()
					+ " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
					+ tb_inaccount.getTime();
			m++;
		}
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		ShowInfo(R.id.btnininfo);
	}
}

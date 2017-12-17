package proj.android.zyl.finance_pro.projx.ver01;

import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.OutaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_outaccount;
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

public class ProjXOutaccountinfo extends Activity {		// 支出管理界面
	public static final String FLAG = "id";
	ListView lvinfo;
	String strType = "";
	Button btnClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_outaccountinfo);
		lvinfo = (ListView) findViewById(R.id.lvoutaccountinfo);

		ShowInfo(R.id.btnoutinfo);

		btnClose = (Button) findViewById(R.id.btnClose);

		lvinfo.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());
				String strid = strInfo.substring(0, strInfo.indexOf('-'));
				Intent intent = new Intent(ProjXOutaccountinfo.this,
						ProjXInfoManage.class);
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
		strType = "btnoutinfo";
		OutaccountDAO outaccountinfo = new OutaccountDAO(ProjXOutaccountinfo.this);
		List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
				(int) outaccountinfo.getCount());
		strInfos = new String[listoutinfos.size()];
		int i = 0;
		for (Tb_outaccount tb_outaccount : listoutinfos) {						// 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[i] = tb_outaccount.getid() + "-" + tb_outaccount.getType()
					+ " " + String.valueOf(tb_outaccount.getMoney()) + "元     "
					+ tb_outaccount.getTime();
			i++;
		}
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);
	}

	protected void onRestart() {
		super.onRestart();
		ShowInfo(R.id.btnoutinfo);
	}
}
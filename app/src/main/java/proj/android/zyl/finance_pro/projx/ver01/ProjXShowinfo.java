package proj.android.zyl.finance_pro.projx.ver01;

import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.FlagDAO;
import proj.android.zyl.finance_pro.dao.InaccountDAO;
import proj.android.zyl.finance_pro.dao.OutaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_flag;
import proj.android.zyl.finance_pro.model.Tb_inaccount;
import proj.android.zyl.finance_pro.model.Tb_outaccount;
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

public class ProjXShowinfo extends Activity {					// 数据管理界面
	public static final String FLAG = "id";
	Button btnoutinfo, btnininfo, btnflaginfo, btnClose;
	ListView lvinfo;
	String strType = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver01_showinfo);

		btnClose = findViewById(R.id.btnClose);
		lvinfo = findViewById(R.id.lvinfo);
		btnoutinfo = findViewById(R.id.btnoutinfo);
		btnininfo = findViewById(R.id.btnininfo);
		btnflaginfo = findViewById(R.id.btnflaginfo);

		ShowInfo(R.id.btnoutinfo);

		btnoutinfo.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ShowInfo(R.id.btnoutinfo);				// 显示支出信息
			}
		});

		btnininfo.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ShowInfo(R.id.btnininfo);				// 显示收入信息
			}
		});
		btnflaginfo.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ShowInfo(R.id.btnflaginfo);				// 显示便签信息
			}
		});

		btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();  //退出
			}
		});



		lvinfo.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());		// 记录信息
				String strid = strInfo.substring(0, strInfo.indexOf('-'));			// 取编号
				Intent intent = null;
				if (strType == "btnoutinfo" | strType == "btnininfo") {				// 判断支出还是收入
					intent = new Intent(ProjXShowinfo.this, ProjXInfoManage.class);
					intent.putExtra(FLAG, new String[] { strid, strType });			// 设置传值
				} else if (strType == "btnflaginfo") {								// 判断便签信息
					intent = new Intent(ProjXShowinfo.this, ProjXFlagManage.class);
					intent.putExtra(FLAG, strid);									// 设置传值
				}
				startActivity(intent);
			}
		});
	}

	private void ShowInfo(int intType) {
		String[] strInfos = null;
		ArrayAdapter<String> arrayAdapter = null;
		switch (intType) {
			case R.id.btnoutinfo:
				strType = "btnoutinfo";
				OutaccountDAO outaccountinfo = new OutaccountDAO(ProjXShowinfo.this);
				List<Tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
						(int) outaccountinfo.getCount());
				strInfos = new String[listoutinfos.size()];
				int i = 0;
				for (Tb_outaccount tb_outaccount : listoutinfos) {						// 遍历List泛型集合
					// 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
					strInfos[i] = tb_outaccount.getid() + "-"
							+ tb_outaccount.getType() + " "
							+ String.valueOf(tb_outaccount.getMoney()) + "元     "
							+ tb_outaccount.getTime();
					i++;
				}
				break;
			case R.id.btnininfo:
				strType = "btnininfo";
				InaccountDAO inaccountinfo = new InaccountDAO(ProjXShowinfo.this);
				List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
						(int) inaccountinfo.getCount());
				strInfos = new String[listinfos.size()];
				int m = 0;
				for (Tb_inaccount tb_inaccount : listinfos) {							// 遍历List泛型集合
					// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
					strInfos[m] = tb_inaccount.getid() + "-"
							+ tb_inaccount.getType() + " "
							+ String.valueOf(tb_inaccount.getMoney()) + "元     "
							+ tb_inaccount.getTime();
					m++;
				}
				break;
			case R.id.btnflaginfo:
				strType = "btnflaginfo";
				FlagDAO flaginfo = new FlagDAO(ProjXShowinfo.this);
				List<Tb_flag> listFlags = flaginfo.getScrollData(0,
						(int) flaginfo.getCount());
				strInfos = new String[listFlags.size()];
				int n = 0;
				for (Tb_flag tb_flag : listFlags) {										// 遍历List泛型集合
					// 将便签相关信息组合成一个字符串

					// 存储到字符串数组的相应位置
					strInfos[n] = tb_flag.getid() + "-" + tb_flag.getFlag();
					if (strInfos[n].length() > 15)
						strInfos[n] = strInfos[n].substring(0, 15) + "……";
					n++;
				}
				break;
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

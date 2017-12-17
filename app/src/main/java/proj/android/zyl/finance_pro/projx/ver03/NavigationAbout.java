package proj.android.zyl.finance_pro.projx.ver03;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import proj.android.zyl.finance_pro.R;


public class NavigationAbout extends Navigation_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.navigation_about);
        toolbar.setTitle(R.string.about);//设置ToolBar Title
        setUpToolBar();//使用父类别的setUpToolBar()，设置ToolBar
        CurrentMenuItem = 1;//目前Navigation项目位置
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true);//设置Navigation当前项目被选取状态
        TextView tvVersion = (TextView) findViewById(R.id.tvVersion);
        try {//获取版本号
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tvVersion.setText( packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}

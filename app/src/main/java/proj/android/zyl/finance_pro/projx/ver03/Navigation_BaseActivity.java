package proj.android.zyl.finance_pro.projx.ver03;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.projx.ver03.Navigation01_MainActivity.Navigation_MainActivity;
import proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity.Navigation_AddActivity;
import proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity.Navigation_DataActivity;
import proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity.Navigation_SettingsActivity;
import proj.android.zyl.finance_pro.projx.ver03.Navigation05_AboutActivity.Navigation_AboutActivity;

// 模板作者教程：https://solinariwu.blogspot.com/2017/02/android-navigation-viewactivity.html
// 原作者 GitHub 链接：https://github.com/SolinariWu/NavigationViewBaseActivity
// 感谢原作者 SolinariWu

/*

Finance Pro 布局说明

Navigation 布局切换器 Navigation_BaseActivity

- 首页 Navigation_MainActivity
- - 欢迎来到 Main_FragmentList_Welcome
- - 说明文档 Main_FragmentList_Readme

- 新增 Navigation_AddActivity
- - 新增支出 Add_FragmentList_AddOut
- - 新增收入 Add_FragmentList_AddIn
- - 收支便签 Add_FragmentList_AddNote

- 管理 Navigation_DataActivity
- - 数据管理 Data_FragmentList_DataAdmin
- - 收入管理 Data_FragmentList_DataIn
- - 支出管理 Data_FragmentList_DataOut

- 设置 Navigation_SettingsActivity
- - 密码修改 Settings_FragmentList_Password
- - 系统设置 Settings_FragmentList_Settings

- 关于 Navigation_AboutActivity

- 登出 Logout


*/


public class Navigation_BaseActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private FrameLayout FL;
    protected NavigationView NV;
    protected Toolbar toolbar;
    protected int CurrentMenuItem = 0;//记录用户正在哪一个页面

    private long mExitTime;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.navigation_drawer, null);
        FL = DL.findViewById(R.id.content_frame);
        NV = DL.findViewById(R.id.Left_Navigation);
        getLayoutInflater().inflate(layoutResID, FL, true);
        super.setContentView(DL);
        toolbar = (Toolbar) findViewById(R.id.NavigationToolBar);
        setUpNavigation();
    }

    private void setUpNavigation() {
        // Set navigation item selected listener
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判断是否点击当前画面的项目，作出判断
                    switch (menuItem.getItemId()) {
                        case R.id.navItemMain:
                            Intent intent1 = new Intent();
                            intent1.setClass(Navigation_BaseActivity.this, Navigation_MainActivity.class);
                            startActivity(intent1);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemAdd:
                            Intent intent2 = new Intent();
                            intent2.setClass(Navigation_BaseActivity.this, Navigation_AddActivity.class);
                            startActivity(intent2);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemData:
                            Intent intent3 = new Intent();
                            intent3.setClass(Navigation_BaseActivity.this, Navigation_DataActivity.class);
                            startActivity(intent3);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemSettings:
                            Intent intent4 = new Intent();
                            intent4.setClass(Navigation_BaseActivity.this, Navigation_SettingsActivity.class);
                            startActivity(intent4);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemAbout:
                            Intent intent5 = new Intent();
                            intent5.setClass(Navigation_BaseActivity.this, Navigation_AboutActivity.class);
                            startActivity(intent5);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemLogout:
                            new AlertDialog.Builder(Navigation_BaseActivity.this)
                                    .setTitle("注销")
                                    .setMessage("你想退出登录吗？")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }

                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                            break;
                    }
                } else {//点击当前项目，收起Navigation
                    DL.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }

    public void setUpToolBar() {//设置ToolBar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                DL.openDrawer(GravityCompat.START);
            }
        });
        //Icon 会产生变化
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, DL, toolbar, R.string.open_navigation, R.string.close_navigation) {

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        DL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(Navigation_BaseActivity.this, "再按一次退出登陆", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //MyConfig.clearSharePre(this, "users");
            finish();
            System.exit(0);
        }
    }
}


package proj.android.zyl.finance_pro.projx.ver02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import proj.android.zyl.finance_pro.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mainTextView;


    // 定义多个 Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = (TextView) findViewById(R.id.mainTextView); //测试修改主文字

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);   // navigationView 的选项列表
        navigationView.setNavigationItemSelectedListener(this);


        /*
        // 初始化控件和声明事件

        // TO-DO
        // 【重要】  Android Fragment 真正的完全解析（上）http://blog.csdn.net/lmj623565791/article/details/37970961
        //  NavigationView配合Fragment切换+动画+使用+check问题解决方案 http://blog.csdn.net/lvwenbo0107/article/details/50888570
        // 如何给navigationView中的按钮添加点击事件 http://blog.csdn.net/wu8787887/article/details/53308076

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);

        Button btnTop = headerLayout.findViewById(R.id.nav_index);
        Button btnAdd = headerLayout.findViewById(R.id.nav_add);

        btnTop.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        setDefaultFragment();


    */

    }


    @Override
    // 按下返回键
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    // 按下菜单键
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // 菜单选择
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*
    private void setDefaultFragment()
    // 设置默认的 Fragment
    {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        TopFragment = new TopFragment();
        transaction.replace(R.id.id_content, TopFragment);
        transaction.commit();
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.

        String string = null;

        int id = menuItem.getItemId();

        if (id == R.id.nav_index) {
            string = "首页";

        } else if (id == R.id.nav_add) {
            string = "新增数据";

        } else if (id == R.id.nav_statistics) {
            string = "数据汇总";

        } else if (id == R.id.nav_settings) {
            string = "设置";

        } else if (id == R.id.nav_share) {
            string = "分享";

        } else if (id == R.id.nav_send) {
            string = "发送";

        }

        if (!TextUtils.isEmpty(string))
            mainTextView.setText("欢迎来到"+string+"页面");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
        /*
        android.app.FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        */

        /*
        switch (menuItem.getItemId())
        {
            case R.id.nav_index:
                if (TopFragment == null)
                {
                    TopFragment = new TopFragment();
                }
                // 使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content, TopFragment);
                transaction.commit();
                break;
            case R.id.nav_add:

                AddFragment = new AddFragment();
                transaction.replace(R.id.id_content, AddFragment);
                transaction.commit();
                break;
            case R.id.nav_statistics:

                    StatisticsFragment = new StatisticsFragment();

                transaction.replace(R.id.id_content, StatisticsFragment);
                transaction.commit();
                break;
            case R.id.nav_settings:
                if (SettingsFragment == null)
                {
                    SettingsFragment = new SettingsFragment();
                }
                transaction.replace(R.id.id_content, SettingsFragment);
                transaction.commit();
                break;
            case R.id.nav_share:
                if (ShareFragment == null)
                {
                    ShareFragment = new ShareFragment();
                }
                transaction.replace(R.id.id_content, ShareFragment);
                transaction.commit();
                break;
            case R.id.nav_send:
                if (SendFragment == null)
                {
                    SendFragment = new SendFragment();
                }
                transaction.replace(R.id.id_content, SendFragment);
                transaction.commit();
                // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                // drawer.closeDrawer(GravityCompat.START);

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
        */


        /*
    int id = menuItem.getItemId();
        String string = null;
        if (id == R.id.nav_index) {
            string = "首页";

        } else if (id == R.id.nav_add) {
            string = "新增数据";

        } else if (id == R.id.nav_statistics) {
            string = "数据汇总";

        } else if (id == R.id.nav_settings) {
            string = "设置";

        } else if (id == R.id.nav_share) {
            string = "分享";

        } else if (id == R.id.nav_send) {
            string = "发送";


            //FragmentManager manager = getSupportFragmentManager();
            //FragmentTransaction transaction = manager.beginTransaction();
            //transaction.replace(this, AddFragment);
            //transaction.commit();
            // getFragmentManager().beginTransaction().replace(R.id.content, TopFragment).commit();
            // getDrawable().closeDrawer(fragment_top);

        }

        if (!TextUtils.isEmpty(string))
            mainTextView.setText(string+" 页面");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }
*/

        // Handle navigation view item clicks here.


    }


}

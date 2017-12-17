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
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import proj.android.zyl.finance_pro.R;


public class Navigation_BaseActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private FrameLayout FL;
    protected NavigationView NV;
    protected Toolbar toolbar;
    protected int CurrentMenuItem = 0;//记录用户正在哪一个页面

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.navigation_drawer, null);
        FL = (FrameLayout) DL.findViewById(R.id.content_frame);
        NV = (NavigationView) DL.findViewById(R.id.Left_Navigation);
        getLayoutInflater().inflate(layoutResID, FL, true);
        super.setContentView(DL);
        toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setUpNavigation();
    }

    private void setUpNavigation() {
        // Set navigation item selected listener
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判断是否点击当前画面的项目，作出判断
                    switch (menuItem.getItemId()) {
                        case R.id.navItemOne:
                            Intent intent = new Intent();
                            intent.setClass(Navigation_BaseActivity.this, NavigationMainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemAbout:
                            Intent intent2 = new Intent();
                            intent2.setClass(Navigation_BaseActivity.this, NavigationAbout.class);
                            startActivity(intent2);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemLogout:
                            new AlertDialog.Builder(Navigation_BaseActivity.this)
                                    .setTitle("Logout")
                                    .setMessage("Are you sure you want to Logout?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }

                                    })
                                    .setNegativeButton("No", null)
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
}

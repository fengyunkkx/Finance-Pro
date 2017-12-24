package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.PwdDAO;
import proj.android.zyl.finance_pro.model.Tb_pwd;
import proj.android.zyl.finance_pro.projx.ver01.ProjXSysset;
import proj.android.zyl.finance_pro.projx.ver03.Navigation_BaseActivity;
import proj.android.zyl.finance_pro.projx.ver03.ViewPagerFragmentAdapter;

public class Navigation_SettingsActivity extends Navigation_BaseActivity {
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private int[] IconResID = {R.drawable.selector_04_1, R.drawable.selector_04_2, R.drawable.selector_04_3};
    private int[] TollBarTitle = {R.string.nv_settings_password, R.string.nv_settings_settings, R.string.nv_settings_more};


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        myViewPager = (ViewPager) findViewById(R.id.NavigationMyViewPager);
        tabLayout = (TabLayout) findViewById(R.id.NavigationTabLayout);
        toolbar.setTitle(TollBarTitle[0]);//设置ToolBar Title
        setUpToolBar();//使用父类别的setUpToolBar()，设置ToolBar
        CurrentMenuItem = 3;//当前的Navigation位置
        NV.getMenu().getItem(CurrentMenuItem).setChecked(true);//设置Navigation目前被选取项目
        setViewPager();
        tabLayout.setupWithViewPager(myViewPager);
        setTabLayoutIcon();

    }



    public void setTabLayoutIcon() {
        for (int i = 0; i < 3; i++) {
            tabLayout.getTabAt(i).setIcon(IconResID[i]);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.getMenu().clear();
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.inflateMenu(R.menu.menu_one);
                        toolbar.setTitle(TollBarTitle[0]);
                        break;
                    case 1:
                        toolbar.inflateMenu(R.menu.menu_two);
                        toolbar.setTitle(TollBarTitle[1]);
                        break;
                    case 2:
                        toolbar.inflateMenu(R.menu.menu_three);
                        toolbar.setTitle(TollBarTitle[2]);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void setViewPager() {
        Settings_FragmentList_Password myFragment1 = new Settings_FragmentList_Password();
        Settings_FragmentList_Settings myFragment2 = new Settings_FragmentList_Settings();
        Settings_FragmentList_More myFragment3 = new Settings_FragmentList_More();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment1);
        fragmentList.add(myFragment2);
        fragmentList.add(myFragment3);
        ViewPagerFragmentAdapter myFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one, menu);
        return true;
    }
}





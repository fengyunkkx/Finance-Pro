package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.PwdDAO;
import proj.android.zyl.finance_pro.dao.SyssetDAO;
import proj.android.zyl.finance_pro.projx.ver01.ProjXLogin;


public class Settings_FragmentList_Settings extends Fragment {


    Button btnDelete, btnBackup, btnRecovery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragmentlist_settings, container, false);


        btnDelete = view.findViewById(R.id.btnDelete);

        btnBackup = view.findViewById(R.id.btnBackup);

        btnRecovery = view.findViewById(R.id.btnRecovery);



        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Toast.makeText(getActivity(), "这是一步危险操作！", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("警告");
                builder.setMessage("你确定要进行初始化吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        SyssetDAO SyssetDAO = new SyssetDAO(getActivity());
                        SyssetDAO.deleteAllUserDate();
                        Toast.makeText(getActivity(), "初始化数据成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });


        btnBackup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SyssetDAO SyssetDAO = new SyssetDAO(getActivity());
                SyssetDAO.saveDate();
                Toast.makeText(getActivity(), "数据备份成功！", Toast.LENGTH_SHORT).show();

            }
        });

        btnRecovery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {



                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("警告");
                builder.setMessage("你确定要恢复数据吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        SyssetDAO SyssetDAO = new SyssetDAO(getActivity());
                        SyssetDAO.getDate();
                        Toast.makeText(getActivity(), "数据恢复成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });

        return view;
    }

}

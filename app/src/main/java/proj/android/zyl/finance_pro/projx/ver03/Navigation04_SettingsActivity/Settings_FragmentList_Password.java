package proj.android.zyl.finance_pro.projx.ver03.Navigation04_SettingsActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.PwdDAO;
import proj.android.zyl.finance_pro.model.Tb_pwd;


public class Settings_FragmentList_Password extends Fragment {


    EditText now_txtPwd1, now_txtPwd2;
    Button btnSet, btnsetCancel, btnClose;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragmentlist_password, container, false);



        now_txtPwd1 = (EditText) view.findViewById(R.id.now_txtPwd1);
        now_txtPwd2 = (EditText) view.findViewById(R.id.now_txtPwd2);
        btnSet = (Button) view.findViewById(R.id.btnSet);
        btnsetCancel = (Button) view.findViewById(R.id.btnsetCancel);
        btnClose = (Button) view.findViewById(R.id.btnClose);


        btnSet.setOnClickListener(new View.OnClickListener() {            //设置按钮
            public void onClick(View arg0) {
                PwdDAO pwdDAO = new PwdDAO(getActivity());

                Tb_pwd tb_pwd = new Tb_pwd(now_txtPwd1.getText().toString(), now_txtPwd1.getText().toString());

                if (pwdDAO.getCount() == 0) {
                    pwdDAO.add(tb_pwd);
                } else if (now_txtPwd1.getText().toString().equals("") || now_txtPwd2.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "输入的密码为空", Toast.LENGTH_SHORT).show();
                } else if (!now_txtPwd1.getText().toString().equals(now_txtPwd2.getText().toString())) {
                    Toast.makeText(getActivity(), "两次密码输入不一致,请重新输入", Toast.LENGTH_SHORT).show();
                    now_txtPwd1.setText("");
                    now_txtPwd2.setText("");
                } else {
                    pwdDAO.update(tb_pwd);

                    Toast.makeText(getActivity(), "密码设置成功！", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        });


        btnsetCancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                now_txtPwd1.setText("");
                now_txtPwd1.setHint("请输入新密码");
                now_txtPwd2.setText("");
                now_txtPwd2.setHint("再次输入密码");
            }
        });


        return view;
    }


}

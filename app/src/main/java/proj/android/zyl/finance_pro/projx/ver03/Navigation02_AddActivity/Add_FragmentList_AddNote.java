package proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.FlagDAO;
import proj.android.zyl.finance_pro.model.Tb_flag;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddFlag;

public class Add_FragmentList_AddNote extends Fragment {

    private EditText txt_flag;
    private Button btn_save;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragmentlist_addnote, container, false);

        super.onCreate(savedInstanceState);

        //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);

        txt_flag = view.findViewById(R.id.txtFlag);
        btn_save = view.findViewById(R.id.btnflagSave);

        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String flag_str = txt_flag.getText().toString();
                if (!flag_str.trim().isEmpty()) {
                    FlagDAO flagDAO = new FlagDAO(getActivity());
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId() + 1, flag_str);
                    flagDAO.add(tb_flag);
                    Toast.makeText(getActivity(), "数据添加成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "你还没有输入便签信息", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.OutaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_outaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXInfoManage;
import proj.android.zyl.finance_pro.projx.ver01.ProjXOutaccountinfo;

public class Data_FragmentList_DataOut extends Fragment {

    public static final String FLAG = "id";
    ListView lvinfo;
    String strType = "";
    Button btnClose;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragmentlist_dataout, container, false);



        super.onCreate(savedInstanceState);
        lvinfo = (ListView) view.findViewById(R.id.lvoutaccountinfo);

        ShowInfo(R.id.btnoutinfo);

        btnClose = (Button) view.findViewById(R.id.btnClose);

        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('-'));
                Intent intent = new Intent(getActivity(),Data_FragmentList_DataAdmin.class);
                intent.putExtra(FLAG, new String[] { strid, strType });
                startActivity(intent);
            }
        });


        return view;
    }




    private void ShowInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnoutinfo";
        OutaccountDAO outaccountinfo = new OutaccountDAO(getActivity());
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
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }


    public void onResume() {
        super.onResume();
        ShowInfo(R.id.btnoutinfo);// 显示收入信息
    }

}
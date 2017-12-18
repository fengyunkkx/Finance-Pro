package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.InaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_inaccount;

public class Data_FragmentList_DataIn extends Fragment {


    public static final String FLAG = "id";
    ListView lvinfo;
    String strType = "";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragmentlist_datain, container, false);


        super.onCreate(savedInstanceState);
        lvinfo = (ListView) view.findViewById(R.id.lvinaccountinfo);


        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText());
                String strid = strInfo.substring(0, strInfo.indexOf('-'));
                Intent intent01 = new Intent();
                intent01.setClass(getActivity(),Data_FragmentList_DataAdmin.class);
                intent01.putExtra(FLAG, new String[] { strid, strType });
                startActivity(intent01);
            }
        });

        return view;
    }



    private void ShowInfo(int intType) {
        String[] strInfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType = "btnininfo";
        InaccountDAO inaccountinfo = new InaccountDAO(getActivity());
        List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0,
                (int) inaccountinfo.getCount());
        strInfos = new String[listinfos.size()];
        int m = 0;
        for (Tb_inaccount tb_inaccount : listinfos) {
            strInfos[m] = tb_inaccount.getid() + "-" + tb_inaccount.getType()
                    + " " + String.valueOf(tb_inaccount.getMoney()) + "元     "
                    + tb_inaccount.getTime();
            m++;
        }
        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter);
    }

    public void onResume() {
        super.onResume();
        ShowInfo(R.id.btnininfo);// 显示收入信息
    }
}
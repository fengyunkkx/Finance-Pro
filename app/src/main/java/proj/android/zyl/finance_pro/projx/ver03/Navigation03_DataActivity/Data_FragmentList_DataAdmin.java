package proj.android.zyl.finance_pro.projx.ver03.Navigation03_DataActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.projx.ver03.RecyclerViewAdapter;


public class Data_FragmentList_DataAdmin extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private String[] Dataset;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragmentlist_dataamin, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setRecyclerView();
        return view;
    }
    public void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setData();
        recyclerAdapter = new RecyclerViewAdapter(Dataset);
        recyclerView.setAdapter(recyclerAdapter);

    }
    public void setData(){
      Dataset = new String[21];
        for (int i = 0 ; i < 21;i++){
           Dataset[i] = Integer.toString(i);
        }

    }
}
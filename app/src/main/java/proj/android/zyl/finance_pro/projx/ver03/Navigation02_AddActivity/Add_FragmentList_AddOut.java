package proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.dao.OutaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_outaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddOutaccount;
import proj.android.zyl.finance_pro.projx.ver03.RecyclerViewAdapter;


public class Add_FragmentList_AddOut extends Fragment {

    protected static final int DATE_DIALOG_ID = 0;
    EditText txtMoney, txtTime, txtAddress, txtMark;
    Spinner spType;
    Button btnSaveButton;
    Button btnCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {           // 新增收入页面
        View view = inflater.inflate(R.layout.add_fragmentlist_addout, container, false);


        super.onCreate(savedInstanceState);

        txtMoney = view.findViewById(R.id.txtMoney);
        txtTime = view.findViewById(R.id.txtTime);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtMark = view.findViewById(R.id.txtMark);
        spType = view.findViewById(R.id.spType);
        btnSaveButton = view.findViewById(R.id.btnSave);
        btnCancelButton = view.findViewById(R.id.btnCancel);

        txtTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                getActivity().showDialog(DATE_DIALOG_ID);
            }
        });

        btnSaveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                String strMoney = txtMoney.getText().toString();
                if (!strMoney.isEmpty()) {
                    OutaccountDAO outaccountDAO = new OutaccountDAO(getActivity());
                    Tb_outaccount tb_outaccount = new Tb_outaccount(
                            outaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strMoney), txtTime
                            .getText().toString(), spType
                            .getSelectedItem().toString(),
                            txtAddress.getText().toString(), txtMark
                            .getText().toString());
                    outaccountDAO.add(tb_outaccount);
                    Toast.makeText(getActivity(), "新增支出添加成功！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "你忘了输入支出金额！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                txtMoney.setText("");                    // 金额文本框
                txtMoney.setHint("0.00");                // 金额文本框设置提示
                txtTime.setText("");                    // 时间文本框
                txtTime.setHint("2017-01-01");            // 时间文本框设置提示
                txtAddress.setText("");                    // 付款方文本框
                txtMark.setText("");                    // 备注文本框
                spType.setSelection(0);                    // 设置类别下拉列表默认选择第一项
            }
        });

        final Calendar c = Calendar.getInstance();                // 获取当前系统日期
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();                                        // 显示当前系统时间
        return view;
    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(getActivity(), mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {                                // 显示设置的时间
        txtTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));
    }
}


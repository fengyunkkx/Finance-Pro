package proj.android.zyl.finance_pro.projx.ver03.Navigation02_AddActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import proj.android.zyl.finance_pro.dao.InaccountDAO;
import proj.android.zyl.finance_pro.model.Tb_inaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddInaccount;


public class Add_FragmentList_AddIn extends Fragment {

    protected static final int DATE_DIALOG_ID = 0;
    EditText txtInMoney, txtInTime, txtInHandler, txtInMark;
    Spinner spInType;
    Button btnInSaveButton;
    Button btnInCancelButton;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {           // 新增收入页面
        View view = inflater.inflate(R.layout.add_fragmentlist_addin, container, false);


        super.onCreate(savedInstanceState);
        txtInMoney = view.findViewById(R.id.txtInMoney);
        txtInTime = view.findViewById(R.id.txtInTime);
        txtInHandler = view.findViewById(R.id.txtInHandler);
        txtInMark = view.findViewById(R.id.txtInMark);
        spInType = view.findViewById(R.id.spInType);
        btnInSaveButton = view.findViewById(R.id.btnInSave);
        btnInCancelButton = view.findViewById(R.id.btnInCancel);

        txtInTime.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    getActivity().showDialog(DATE_DIALOG_ID);
                }
            });

        btnInSaveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                String strInMoney = txtInMoney.getText().toString();
                if (!strInMoney.isEmpty()) {
                    InaccountDAO inaccountDAO = new InaccountDAO(getActivity());
                    Tb_inaccount tb_inaccount = new Tb_inaccount(
                            inaccountDAO.getMaxId() + 1, Double
                            .parseDouble(strInMoney), txtInTime
                            .getText().toString(), spInType
                            .getSelectedItem().toString(),
                            txtInHandler.getText().toString(),
                            txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);
                    Toast.makeText(getActivity(), "新增收入添加成功！",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "你忘了输入收入金额！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                txtInMoney.setText("");                    // 金额文本框
                txtInMoney.setHint("0.00");                // 金额文本框设置提示
                txtInTime.setText("");                    // 时间文本框
                txtInTime.setHint("2017-01-01");        // 时间文本框设置提示
                txtInHandler.setText("");                // 付款方文本框
                txtInMark.setText("");                    // 备注文本框
                spInType.setSelection(0);                // 设置类别下拉列表默认选择第一项
            }
        });

        final Calendar c = Calendar.getInstance();                // 自动获取当前系统日期
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();

        return view;
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(getActivity(), mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();                                    // 显示设置的日期
        }
    };

    private void updateDisplay() {                                // 显示设置的时间
        txtInTime.setText(new StringBuilder().append(mYear).append("-")
                .append(mMonth + 1).append("-").append(mDay));

    }
}


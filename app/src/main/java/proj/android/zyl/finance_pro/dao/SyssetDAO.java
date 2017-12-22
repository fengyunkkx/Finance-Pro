package proj.android.zyl.finance_pro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fengyunkkx on 2017/12/21.
 */

public class SyssetDAO {

    private DBOpenHelper dbh;
    private SQLiteDatabase db;

    public SyssetDAO(Context context) {
        dbh = new DBOpenHelper(context);
    }

    public void deleteAllUserDate() {
        db = dbh.getWritableDatabase();

        String sql1 = "delete from tb_flag";
        db.execSQL(sql1);

        String sql2 = "delete from tb_outaccount";
        db.execSQL(sql2);

        String sql3 = "delete from tb_inaccount";
        db.execSQL(sql3);

        db.close();
    }

    public void saveDate() {
        db = dbh.getWritableDatabase();
        db.execSQL("REPLACE INTO tb_cp_outaccount SELECT * FROM tb_outaccount");
        db.execSQL("REPLACE INTO tb_cp_inaccount SELECT * FROM tb_inaccount");
        db.execSQL("REPLACE INTO tb_cp_flag SELECT * FROM tb_flag");
        db.execSQL("REPLACE INTO tb_cp_pwd SELECT * FROM tb_pwd");
    }

    public void getDate() {
        db = dbh.getWritableDatabase();
        db.execSQL("REPLACE INTO tb_outaccount SELECT * FROM tb_cp_outaccount");
        db.execSQL("REPLACE INTO tb_inaccount SELECT * FROM tb_cp_inaccount");
        db.execSQL("REPLACE INTO tb_flag SELECT * FROM tb_cp_flag");
        db.execSQL("REPLACE INTO tb_pwd SELECT * FROM tb_cp_pwd");

    }
}



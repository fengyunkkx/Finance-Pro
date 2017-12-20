package proj.android.zyl.finance_pro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import proj.android.zyl.finance_pro.model.*;

public class FlagDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public FlagDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}

	/**
	 * 添加便签信息
	 *
	 * @param tb_flag
	 */
	public void add(Tb_flag tb_flag) {
		db = helper.getWritableDatabase();
        String sql ="insert into tb_flag (_id,flag) values (?,?)";
		db.execSQL(sql, new Object[] {
				tb_flag.getid(), tb_flag.getFlag() });
	}

	public void update(Tb_flag tb_flag) {
		db = helper.getWritableDatabase();
        String sql ="update tb_flag set flag = ? where _id = ?";
		db.execSQL(sql, new Object[] {
				tb_flag.getFlag(), tb_flag.getid() });
	}

    /**
     * 更新便签信息
     *
     * @param tb_flag
     */
	/**
	 * 查找便签信息
	 *
	 * @param id
	 * @return
	 */
	public Tb_flag find(int id) {
		db = helper.getWritableDatabase();
		String sql ="select _id,flag from tb_flag where _id = ?";
		Cursor cursor = db.rawQuery(sql ,new String[] { String.valueOf(id) });
		if (cursor.moveToNext())
		{
			return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("flag")));
		}
		return null;
	}

	/**
	 * 刪除便签信息
	 *
	 * @param ids
	 */
	public void detele(Integer... ids) {
		if (ids.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.execSQL("delete from tb_flag where _id in (" + sb + ")",
                    ids);
		}
	}

	/**
	 * 获取便签信息
	 *
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<Tb_flag> getScrollData(int start, int count) {
		List<Tb_flag> lisTb_flags = new ArrayList<Tb_flag>();
		db = helper.getWritableDatabase();
        String sql ="select * from tb_flag limit ?,?";
		Cursor cursor = db.rawQuery(sql,
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())
		{
			lisTb_flags.add(new Tb_flag(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getString(cursor
					.getColumnIndex("flag"))));
		}
		return lisTb_flags;
	}

	/**
	 * 获取总记录数
	 *
	 * @return
	 */
	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from tb_flag", null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}

	/**
	 * 获取便签最大编号
	 *
	 * @return
	 */
	public int getMaxId() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_flag", null);
		while (cursor.moveToLast()) {
			return cursor.getInt(0);
		}
		return 0;
	}
}

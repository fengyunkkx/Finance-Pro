package proj.android.zyl.finance_pro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import proj.android.zyl.finance_pro.model.*;

public class InaccountDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public InaccountDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}

	/**
	 * 添加收入信息
	 *
	 * @param tb_inaccount
	 */
	public void add(Tb_inaccount tb_inaccount) {
		db = helper.getWritableDatabase();
		String sql ="insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)";
		db.execSQL(sql,new Object[] { tb_inaccount.getid(), tb_inaccount.getMoney(),
						tb_inaccount.getTime(), tb_inaccount.getType(),
						tb_inaccount.getHandler(), tb_inaccount.getMark() });
	}

	/**
	 * 更新收入信息
	 *
	 * @param tb_inaccount
	 */
	public void update(Tb_inaccount tb_inaccount) {
		db = helper.getWritableDatabase();
		String sql ="update tb_inaccount set money = ?,time = ?,type = ?,handler = ?,mark = ? where _id = ?";
		db.execSQL(sql,new Object[] { tb_inaccount.getMoney(), tb_inaccount.getTime(),
						tb_inaccount.getType(), tb_inaccount.getHandler(),
						tb_inaccount.getMark(), tb_inaccount.getid() });
	}

	/**
	 * 查找收入信息
	 *
	 * @param id
	 * @return
	 */


	public Tb_inaccount find(int id) {
		db = helper.getWritableDatabase();
		String sql ="select _id,money,time,type,handler,mark from tb_inaccount where _id = ?";
		Cursor cursor = db.rawQuery(sql,new String[] { String.valueOf(id) });
		if (cursor.moveToNext())
		{
			return new Tb_inaccount(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("handler")),
					cursor.getString(cursor.getColumnIndex("mark")));
		}
		return null;
	}

	/**
	 * 刪除收入信息
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
			db.execSQL("delete from tb_inaccount where _id in (" + sb + ")",
                    ids);
		}
	}

	/**
	 * 获取收入信息
	 *
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<Tb_inaccount> getScrollData(int start, int count) {
		List<Tb_inaccount> tb_inaccount = new ArrayList<Tb_inaccount>();
		db = helper.getWritableDatabase();
		String sql ="select * from tb_inaccount limit ?,?";
		Cursor cursor = db.rawQuery(sql,
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())
		{
			tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("type")), cursor.getString(cursor
					.getColumnIndex("handler")), cursor.getString(cursor
					.getColumnIndex("mark"))));
		}
		return tb_inaccount;
	}

	/**
	 * 获取总记录数
	 *
	 * @return
	 */
	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db
				.rawQuery("select count(_id) from tb_inaccount", null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}

	/**
	 * 获取收入最大编号
	 *
	 * @return
	 */
	public int getMaxId() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);
		while (cursor.moveToLast()) {
			return cursor.getInt(0);
		}
		return 0;
	}
}

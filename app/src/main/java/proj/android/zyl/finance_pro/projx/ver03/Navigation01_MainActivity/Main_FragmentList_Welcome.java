package proj.android.zyl.finance_pro.projx.ver03.Navigation01_MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import proj.android.zyl.finance_pro.R;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddFlag;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddInaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXAddOutaccount;
import proj.android.zyl.finance_pro.projx.ver01.ProjXInaccountinfo;
import proj.android.zyl.finance_pro.projx.ver01.ProjXOutaccountinfo;
import proj.android.zyl.finance_pro.projx.ver01.ProjXShowinfo;
import proj.android.zyl.finance_pro.projx.ver01.ProjXSysset;


public class Main_FragmentList_Welcome extends Fragment {

    GridView gvInfo;
    String[] titles = new String[]{"新增支出", "新增收入", "我的支出", "我的收入", "数据管理",
            "系统设置", "收支便签"};
    int[] images = new int[]{R.drawable.img01xl,
            R.drawable.img02xl, R.drawable.img03xl,
            R.drawable.img04xl, R.drawable.img05xl,
            R.drawable.img06xl, R.drawable.img07xl,};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragmentlist_welcome, container, false);

        super.onCreate(savedInstanceState);

        gvInfo = view.findViewById(R.id.gvInfo2);
        pictureAdapter2 adapter = new pictureAdapter2(titles, images, getActivity());
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = null;
                switch (arg2) {

                    case 0:
                        intent = new Intent(getActivity(), ProjXAddOutaccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), ProjXAddInaccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), ProjXOutaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), ProjXInaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), ProjXShowinfo.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), ProjXSysset.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getActivity(), ProjXAddFlag.class);
                        startActivity(intent);
                        break;

                }
            }
        });

        return view;
    }
}


    class ViewHolder {
        public TextView title;
        public ImageView image;
    }

    class Picture {
        private String title;
        private int imageId;

        public Picture() {
            super();
        }

        public Picture(String title, int imageId) {
            super();
            this.title = title;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImageId() {
            return imageId;

        }

        public void setimageId(int imageId) {
            this.imageId = imageId;
        }

    }

    class pictureAdapter2 extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Picture> pictures;


        public pictureAdapter2(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++) {
                Picture picture = new Picture(titles[i], images[i]);
                pictures.add(picture);
            }
        }

        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }

        public Object getItem(int arg0) {
            return pictures.get(arg0);
        }


        public long getItemId(int arg0) {
            return arg0;
        }


        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ViewHolder viewHolder;
            if (arg1 == null) {
                arg1 = inflater.inflate(R.layout.main_gvitem, null);
                viewHolder = new ViewHolder();
                viewHolder.title = arg1.findViewById(R.id.ItemTitle);
                viewHolder.image = arg1.findViewById(R.id.ItemImage);
                arg1.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) arg1.getTag();
            }
            viewHolder.title.setText(pictures.get(arg0).getTitle());
            viewHolder.image.setImageResource(pictures.get(arg0).getImageId());
            return arg1;
        }
    }

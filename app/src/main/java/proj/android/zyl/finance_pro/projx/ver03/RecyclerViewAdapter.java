package proj.android.zyl.finance_pro.projx.ver03;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import proj.android.zyl.finance_pro.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] Dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.recyclerTextView);
        }

    }
    public RecyclerViewAdapter(String[] myDataset) {
        Dataset = myDataset;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_recycler_textview, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(Dataset[position]);

    }
    @Override
    public int getItemCount() {
        return Dataset.length;
    }
}
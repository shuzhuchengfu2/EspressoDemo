package barray.test.espresso.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import barray.test.espresso.R;

/**
 * author： xiongdejin
 * date: 2018/4/25
 * describe:
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private final List<String> mDataSet;

    private final Context mContext;

    /**
     * Provide a reference to the type of views that you are using
     * (custom {@link RecyclerView.ViewHolder}).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        // We'll use this field to showcase matching the holder from the test.
        private boolean mIsInTheMiddle = false;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
        }

        TextView getTextView() {
            return textView;
        }

        public boolean getIsInTheMiddle() {
            return mIsInTheMiddle;
        }

        void setIsInTheMiddle(boolean isInTheMiddle) {
            mIsInTheMiddle = isInTheMiddle;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(List<String> dataSet, Context context) {
        mDataSet = dataSet;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (position == mDataSet.size() / 2 /* calculate middle element position */) {
            viewHolder.setIsInTheMiddle(true);
            viewHolder.getTextView().setText(mContext.getResources().getString(R.string.middle));
        }
        else {
            viewHolder.setIsInTheMiddle(false);
            viewHolder.getTextView().setText(mDataSet.get(position));
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}

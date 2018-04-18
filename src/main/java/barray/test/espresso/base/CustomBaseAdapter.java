package barray.test.espresso.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author： xiongdejin
 * date: 2018/4/18
 * describe:
 */
public abstract class CustomBaseAdapter<T> extends BaseAdapter {
    protected List<T> allData;
    protected Context context;

    public CustomBaseAdapter(Context context, List<T> allData) {
        this.context = context;
        if (allData == null) {
            allData = new ArrayList<>();
        }
        this.allData = allData;
    }


    @Override
    public int getCount() {
        return allData.size();
    }

    @Override
    public Object getItem(int position) {
        return allData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

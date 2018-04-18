package barray.test.espresso.act;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import barray.test.espresso.R;
import barray.test.espresso.base.CustomBaseAdapter;
import barray.test.espresso.domain.User;
import barray.test.espresso.widget.dialog.DeleteDialog;

public class ListViewActivity extends AppCompatActivity {
    private DeleteDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ListView lv = findViewById(R.id.lv);
        final List<User> userList = initData();
        final MyAdapter myAdapter = new MyAdapter(ListViewActivity.this, userList);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                deleteDialog = new DeleteDialog(ListViewActivity.this);
                deleteDialog.setDeleteItemCallBack(new DeleteDialog.DeleteItemCallBack() {
                    @Override
                    public void callBack() {
                        userList.remove(position);
                        //局部刷新
                        myAdapter.notifyDataSetChanged();
                    }
                });
                deleteDialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (deleteDialog != null && deleteDialog.isShowing()) {
            deleteDialog.dismiss();
        }
        deleteDialog = null;
    }

    public List<User> initData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("张三");
            user.setAge(i);
            users.add(user);
        }
        return users;
    }

    public class MyAdapter extends CustomBaseAdapter<User> {
        MyAdapter(Context context, List<User> allData) {
            super(context, allData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.item_user, null);
                viewHolder = new ViewHolder();
                viewHolder.tvName = convertView.findViewById(R.id.tv_name);
                viewHolder.tvAge = convertView.findViewById(R.id.tv_age);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            User user = allData.get(position);
            viewHolder.tvName.setText(user.getName());
            viewHolder.tvAge.setText(user.getAge() + "");
            return convertView;
        }

        class ViewHolder {
            TextView tvName;
            TextView tvAge;
        }
    }
}

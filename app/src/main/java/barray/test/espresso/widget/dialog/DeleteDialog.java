package barray.test.espresso.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import barray.test.espresso.R;

/**
 * author： xiongdejin
 * date: 2018/4/18
 * describe:
 */
public class DeleteDialog extends Dialog{
    private DeleteItemCallBack deleteItemCallBack;

    public DeleteDialog(@NonNull Context context) {
        this(context,0);
    }

    public DeleteDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_delete);
        initData();
    }

    private void initData() {
        Button btnOk = findViewById(R.id.btn_ok);
        Button btnCancel = findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteItemCallBack != null){
                    deleteItemCallBack.callBack();
                    dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public void setDeleteItemCallBack(DeleteItemCallBack deleteItemCallBack){
        this.deleteItemCallBack = deleteItemCallBack;
    }

    public interface DeleteItemCallBack{
        void callBack();
    }


}

package data_bind.xyl.com.testlitepal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.text.TextUtils.TruncateAt;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import data_bind.xyl.com.model.UserModel;

public class DataListActivity extends Activity {

    private ListView mDataListView;
    private DataArrayAdapter mAdapter;
    private List<List<String>> mList = new ArrayList<>();
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DataListActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list_activity);
        mDataListView = findViewById(R.id.data_list_view);
        mAdapter = new DataArrayAdapter(this, 0, mList);
        mDataListView.setAdapter(mAdapter);
        populateDataFromDB();
//        UserModel singer=new UserModel();
//        refreshListView(singer.getId(), singer.getName(), singer.getAge());

    }
    private void populateDataFromDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                List<String> columnList = new ArrayList<String>();
                columnList.add("id");
                columnList.add("name");
                columnList.add("age");
//                columnList.add("ismale");
                mList.add(columnList);
                Cursor cursor = null;
                try {
                    //select * from usermodel where id<40 order by id
                    String sql="select  * from usermodel  order by  id desc limit 100";
                    cursor = Connector.getDatabase().rawQuery(sql,null);
                    if (cursor.moveToFirst()) {
                        do {
                            long id = cursor.getLong(cursor.getColumnIndex("id"));
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            int age = cursor.getInt(cursor.getColumnIndex("age"));
//                            int isMale = cursor.getInt(cursor.getColumnIndex("ismale"));
                            List<String> stringList = new ArrayList<String>();
                            stringList.add(String.valueOf(id));
                            stringList.add(name);
                            stringList.add(String.valueOf(age));
//                            stringList.add(String.valueOf(isMale));
                            mList.add(stringList);
                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }).start();
    }

    private void refreshListView(long id, String name, int age) {
        List<String> stringList = new ArrayList<String>();
        stringList.add(String.valueOf(id));
        stringList.add(name);
//        stringList.add(String.valueOf(age));
        stringList.add("1");
        mList.add(stringList);
        mAdapter.notifyDataSetChanged();
        mDataListView.setSelection(mList.size());
    }






    public class DataArrayAdapter extends ArrayAdapter<List<String>> {

        public DataArrayAdapter(Context context, int textViewResourceId, List<List<String>> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            List<String> dataList = getItem(position);
            LinearLayout layout;
            if (convertView == null) {
                layout = new LinearLayout(getContext());
            } else {
                layout = (LinearLayout) convertView;
            }
            layout.removeAllViews();
            int width = dp2px(getContext(), 100);
            int height = dp2px(getContext(), 30);
            for (String data : dataList) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                TextView textView = new TextView(getContext());
                textView.setText(data);
                textView.setSingleLine(true);
                textView.setEllipsize(TruncateAt.END);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                layout.addView(textView, params);
            }
            return layout;
        }

    }
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}

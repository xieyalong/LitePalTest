/*
 * Copyright (C)  Tony Green, Litepal Framework Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package data_bind.xyl.com.testlitepal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.litepal.tablemanager.Connector;
import org.litepal.util.DBUtility;

import java.util.ArrayList;
import java.util.List;

public class TableListActivity extends AppCompatActivity {

	private ProgressBar mProgressBar;

	private StringArrayAdapter mAdapter;

	private List<String> mList = new ArrayList<>();

	public static void actionStart(Context context) {
		Intent intent = new Intent(context, TableListActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_list_layout);
		mProgressBar = findViewById(R.id.progress_bar);
		ListView mTableListView = findViewById(R.id.table_listview);
		mAdapter = new StringArrayAdapter(this, 0, mList);
		mTableListView.setAdapter(mAdapter);
		populateTables();
//		mTableListView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View view, int index, long id) {
//				TableStructureActivity.actionStart(TableListActivity.this, mList.get(index));
//			}
//		});
	}

	private void populateTables() {
		mProgressBar.setVisibility(View.VISIBLE);
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<String> tables = DBUtility.findAllTableNames(Connector.getDatabase());
				for (String table : tables) {
					if (table.equalsIgnoreCase("android_metadata")
							|| table.equalsIgnoreCase("sqlite_sequence")
							|| table.equalsIgnoreCase("table_schema")) {
						continue;
					}
					mList.add(table);
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mProgressBar.setVisibility(View.GONE);
						mAdapter.notifyDataSetChanged();
					}
				});
			}
		}).start();
	}


	public class StringArrayAdapter extends ArrayAdapter<String> {

		public StringArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item, null);
			} else {
				view = convertView;
			}
			TextView textView = (TextView) view.findViewById(R.id.text_1);
			textView.setText("表名："+getItem(position));
			view.findViewById(R.id.text_3).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					TableStructureActivity.actionStart(TableListActivity.this, mList.get(position));
				}
			});
			return view;
		}
	}
}

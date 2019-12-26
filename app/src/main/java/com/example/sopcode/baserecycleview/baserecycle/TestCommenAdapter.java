package com.example.sopcode.baserecycleview.baserecycle;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.sopcode.R;

/**
 * 使用方法：
 * 1.继承CommenAdapter<T> 这里的T为RecyclerView需要展示的数据类型
 * 2.实现getLayoutItem() R.layout.xxx
 * bindViewHolder(View itemView)  //这里进行界面初始化;
 * updataView(int position)方法  //数据展示
 * 3.调用：
 * LinearLayoutManager manager = new LinearLayoutManager(this);
 * //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
 * manager.setOrientation(LinearLayoutManager.VERTICAL);//默认为垂直
 * final RecyclerView recyclerView = findViewById(R.id.recyclerview);
 * recyclerView.setLayoutManager(manager);
 * TestCommenAdapter adapter = new TestCommenAdapter(MainActivity.this);
 * adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
 *
 * @Override public void onItemClick(int position) {
 * Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
 * }
 * });
 * List<String> list = new ArrayList<>();
 * for (int i = 0; i < 10; i++) {
 * list.add("price " + i);
 * }
 * adapter.setList(list);
 * recyclerView.setAdapter(adapter);
 */

public class TestCommenAdapter extends CommenAdapter<String> {
    public TestCommenAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutItem() {
        return R.layout.item_base;
    }

    TextView textView;

    //这里进行界面初始化;
    @Override
    protected void bindViewHolder(View itemView) {
        textView = itemView.findViewById(R.id.tv_title);
    }

    //数据展示
    @Override
    protected void updataView(int position) {
        textView.setText(mList.get(position));
    }
}

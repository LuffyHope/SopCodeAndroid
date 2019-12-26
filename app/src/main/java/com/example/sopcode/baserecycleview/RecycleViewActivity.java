package com.example.sopcode.baserecycleview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sopcode.R;
import com.example.sopcode.baseactivity.CommonActivity;
import com.example.sopcode.baserecycleview.baserecycle.BaseAdapter;
import com.example.sopcode.baserecycleview.baserecycle.TestCommenAdapter;
import com.example.sopcode.baserecycleview.zhuangbirecycleview.BaseAdapter2;
import com.example.sopcode.baserecycleview.zhuangbirecycleview.PriceAdapter;
import com.example.sopcode.baserecycleview.zhuangbirecycleview.PriceModel;
import com.example.sopcode.databinding.ActivityRecycleBinding;
import com.example.sopcode.utils.EventHelper;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends CommonActivity<ActivityRecycleBinding> implements View.OnClickListener {
    @Override
    protected String setTitleText() {
        return "recycleView";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle;
    }

    @Override
    protected void init() {
        super.init();
        EventHelper.click(this, dataBind.tvTestAdpter);
    }

    @Override
    public void onClick(View view) {
        //zhuangbirecycleview();
        baserecycle();
    }

    private void baserecycle() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        TestCommenAdapter adapter = new TestCommenAdapter(RecycleViewActivity.this);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("name" + i);
        }
        adapter.setList(list);
        recyclerView.setAdapter(adapter);
    }

    private void zhuangbirecycleview() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(manager);
        PriceAdapter adapter = new PriceAdapter(RecycleViewActivity.this);
        adapter.setOnItemClickListener(new BaseAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        List<PriceModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PriceModel model = new PriceModel();
            model.name = "name" + i;
            model.price = "price" + i;
            list.add(model);
        }
        adapter.setList(list);
        recyclerView.setAdapter(adapter);
    }
}

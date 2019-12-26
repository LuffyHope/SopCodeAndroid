package com.example.sopcode.baserecycleview.zhuangbirecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
 * adapter.setOnItemClickListener(new BaseAdapter2.OnItemClickListener() {
 *
 * @Override public void onItemClick(int position) {
 * Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
 * }
 * });
 * List<PriceModel> list = new ArrayList<>();
 * for (int i = 0; i < 10; i++) {
 * PriceModel model = new PriceModel();
 * model.name = "name" + i;
 * model.price = "price" + i;
 * list.add(model);
 * }
 * adapter.setList(list);
 * recyclerView.setAdapter(adapter);
 */
public class PriceAdapter extends BaseAdapter2<PriceModel, PriceAdapter.MyViewHolder> {

    public PriceAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_base;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //必须调用父类的方法
        super.onBindViewHolder(holder, position);
        PriceModel model = mList.get(position);
        holder.priceText.setText(model.price + "");
    }
    // 这里要是静态的
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView priceText;

        public MyViewHolder(View itemView) {
            super(itemView);
            priceText = itemView.findViewById(R.id.tv_title);
        }
    }
}

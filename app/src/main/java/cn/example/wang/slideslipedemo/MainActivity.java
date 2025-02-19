package cn.example.wang.slideslipedemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.example.wang.slideslipedemo.slideswaphelper.PlusItemSlideCallback;
import cn.example.wang.slideslipedemo.slideswaphelper.WeItemTouchHelper;

/**
 * 1.侧滑的距离可控。
 * 2.统一CallBack里面的接口。
 * 3.下拉刷新的问题。
 * 4.侧滑打开下点击事件
 * 5.没打开侧滑也能点击到下面的删除按钮。
 * 6.一个阀值的管理。
 *
 *
 */
public class MainActivity extends AppCompatActivity implements RecAdapter.DeletedItemListener{
    RecyclerView recyclerView;
    private RecAdapter recAdapter;

    public static void start(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("Item  " +i);
        }
        recAdapter.setList(list);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recAdapter = new RecAdapter(this);
        recAdapter.setDelectedItemListener(this);
        recyclerView.setAdapter(recAdapter);
        /*ItemTouchHelperCallback touchHelperCallback = new ItemTouchHelperCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);*/

        //作为一个ItemDecoration 写入的
        PlusItemSlideCallback callback = new PlusItemSlideCallback();
        callback.setType(WeItemTouchHelper.SWIPE_ITEM_TYPE_FLOWWING);
        WeItemTouchHelper extension = new WeItemTouchHelper(callback);
        extension.attachToRecyclerView(recyclerView);
    }

    @Override
    public void deleted(int position) {
        recAdapter.removeDataByPosition(position);
    }
}

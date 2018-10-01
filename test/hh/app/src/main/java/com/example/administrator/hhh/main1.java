package com.example.administrator.hhh;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main1 extends  AppCompatActivity implements OnClickListener  {
    public static ClientThread clientThread=null;
    private List<rjb> r1=new ArrayList<>();
    private com.example.administrator.hhh.ListAdapter mAdapter;
    private ListView listView;
    private int id=0;
    private rjb r2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        Button btn = (Button)this.findViewById(R.id.add);
        btn.setOnClickListener(this);
        listView=(ListView)this.findViewById(R.id.l1);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerForContextMenu(listView);
                id  = Integer.parseInt(r1.get(i).getId());
                r2 = r1.get(i);
                return false;
            }
        });


        clientThread=new ClientThread(handler);
        clientThread.start();
        initView();
        initData();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        r1=new ArrayList<rjb>();
        mAdapter = new com.example.administrator.hhh.ListAdapter(this,r1);
        listView.setAdapter(mAdapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.l1);
    }


    public void onClick(View v){
        if(v.getId()==R.id.add){
            Intent i = new Intent(this,zeng.class);
            startActivity(i);
        }
    }
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){
        setTitle("rj");
        menu.add(0,1,0,"查看编辑");
        menu.add(0,2,0,"删除");
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Intent i = new Intent(this,gai.class);
                i.putExtra("aa",r2);
                startActivity(i);

                break;
            case 2:
                clientThread.sendMessage("del****delete from rjb where id="+id);
                clientThread.sendMessage("all****获取服务器全部数据");
                break;

        }
        return true;
    }
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String content=bundle.getString("response");
            Log.d("服务器返回数据",content);
            String[] s1=content.split("\\*\\*\\*\\*\\*");
            switch (s1[0])
            {
                case "删除成功":
                    Toast.makeText(main1.this,"删除成功",Toast.LENGTH_LONG).show();
                    break;


                default:
                    r1.clear();
                    for (int i = 1; i < s1.length-1; i++)
                    {
                        String[] note = s1[i].split("\\*\\*\\*\\*");//以三个星号切割每一个字段并将其赋值给各个控件
                        rjb diary = new rjb();
                        diary.setId(note[0]);
                        diary.setTitle(note[1]);
                        diary.setTime(note[2]);
                        diary.setContent(note[3]);
                        r1.add(diary);
                    }
                    Collections.reverse(r1);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
}
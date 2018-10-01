package com.example.administrator.hhh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/7/3/003.
 */
public class gai extends Activity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gai);
        Intent intent = getIntent();
        rjb a = (rjb) intent.getSerializableExtra("aa");
        EditText d1=(EditText)findViewById(R.id.d1);
        d1.setText(a.getTitle());
        EditText d2=(EditText)findViewById(R.id.d2);
        d2.setText(a.getContent());
        TextView time=(TextView)findViewById(R.id.time);
        time.setText(a.getTime());
        Button bj=(Button)findViewById(R.id.bj);
        bj.setOnClickListener(this);
        Button ex=(Button)findViewById(R.id.exit);
        ex.setOnClickListener(this);
        TextView n=(TextView)findViewById(R.id.time1);
        n.setText(getTime());
        TextView d=(TextView)findViewById(R.id.ids);
        d.setText(a.getId());


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.bj){
            TextView newtime=(TextView)findViewById(R.id.time1);
            EditText d1=(EditText)findViewById(R.id.d1);
            EditText d2=(EditText)findViewById(R.id.d2);
            TextView t=(TextView)findViewById(R.id.time1);
            TextView v=(TextView)findViewById(R.id.ids);
            TextView e=(TextView)findViewById(R.id.time);

            newtime.setText(getTime());
            main1.clientThread.sendMessage("gai****update rjb set title="+"'"+d1.getText().toString()+"'"+",time="+"'"+t.getText().toString()+"'"+",content="+"'"+d2.getText().toString()+"'"+" where time="+"'"+e.getText().toString()+"'");
            Intent i = new Intent(this,main1.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(this,main1.class);
            startActivity(i);

        }
    }
    public static String getTime() {
        android.text.format.Time t=new android.text.format.Time();
        t.setToNow();
        int year=t.year;
        int month=t.month+1;
        int day=t.monthDay;
        int hour=t.hour;
        int min=t.minute;
        int second=t.second;
        return String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日"+String.valueOf( hour)+":"+String.valueOf( min)+":"+String.valueOf( second);
    }
}


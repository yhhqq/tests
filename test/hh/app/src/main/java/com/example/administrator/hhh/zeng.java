package com.example.administrator.hhh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/7/2/002.
 */

public class zeng extends Activity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zeng);
        Button bu1=(Button)findViewById(R.id.cun);
        bu1.setOnClickListener(this);
        Button bu2=(Button)findViewById(R.id.exit);
        bu2.setOnClickListener(this);
        TextView tt=(TextView)findViewById(R.id.times);
        tt.setText(getTime());


   }



    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.cun){
            TextView tt=(TextView)findViewById(R.id.times);
            tt.setText(getTime());
            EditText title=(EditText)findViewById(R.id.d1);
            EditText content=(EditText)findViewById(R.id.d2);
            main1.clientThread.sendMessage("tian****insert into rjb(title,time,content)values("+"'"+title.getText().toString()+"'"+","+"'"+tt.getText().toString()+"'"+","+"'"+content.getText().toString()+"'"+")");
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

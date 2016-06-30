package com.lsh.recycleradapterdemo.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lsh.recycleradapterdemo.R;

public class MainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*PtrRefresh*/
    public void PtrRefreshLinearLayout(View view) {
        startActivity(new Intent(this, PtrLinearLayoutRefreshAct.class));
    }

    public void PtrRefreshLinearLayoutWithHead(View view) {
        startActivity(new Intent(this, PtrLinearLayoutWithHeadAct.class));
    }

    public void PtrRefreshStaggered(View view) {
        startActivity(new Intent(this, PtrStaggeredRefreshAct.class));
    }

    /*SwipeRefresh*/
    public void SwipeRefreshLinearLayout(View view) {
        startActivity(new Intent(this, SwipeLinearLayoutRefreshAct.class));
    }

    public void SwipeRefreshLinearLayoutWithHead(View view) {
        startActivity(new Intent(this, SwipeLinearLayoutWithHeadAct.class));
    }

    public void SwipeRefreshStaggered(View view) {
        startActivity(new Intent(this, SwipeStaggeredRefreshAct.class));
    }
}

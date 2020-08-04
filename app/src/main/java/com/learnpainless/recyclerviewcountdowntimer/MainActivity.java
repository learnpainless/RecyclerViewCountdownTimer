package com.learnpainless.recyclerviewcountdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerViewAdapter = new RecyclerViewAdapter(null); //pass item click listener of you want to handle recyclerview item clicks.
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //remove all timer before pausing app.
        recyclerViewAdapter.clearAll();
    }
}
package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShoppingItems extends AppCompatActivity {
    // log tag for specifying tags from this class
    private static final String LOG_TAG = "From ShoppingItems.java";
    public static final String EXTRA_MESSAGE = "com.example.shoppinglist.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_items);
    }

    /*
        Skeleton state methods for testing
     */
    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "Start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "Destroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "Pause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "Resume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(LOG_TAG, "Restart");
        super.onRestart();
    }

    public void addItem(View view) {
        // Casts current view to a button object
        Button b = (Button)view;
        // Then gets string from button object clicked
        String item = b.getText().toString();

        // Intent created for return to Main
        // Carries item as a message and result ok code (-1)
        Intent returnItem = new Intent();
        returnItem.putExtra(EXTRA_MESSAGE, item);
        setResult(RESULT_OK, returnItem);
        Log.d(LOG_TAG, "addItem finish, -1");
        finish();
    }
}
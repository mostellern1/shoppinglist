/*
    ShoppingList, developed by Noah Mosteller

    Copyright 2020

    This basic app functions as a simple shopping list, with
    the ability to add up to 10 items to a shopping list,
    with choices being from 8 predefined options
 */

package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.ShoppingItems;

public class MainActivity extends AppCompatActivity {
    // Log tag to indicate logs from this class
    private static final String LOG_TAG = "From MAIN";
    // Request code used in intent to ShoppingItems
    public static final int REQUEST_CODE = 1;
    // Used to hold and update TextViews on the main list
    private TextView updateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Logs to show new creation of activity for development
        Log.d(LOG_TAG, "-----");
        Log.d(LOG_TAG, "Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if checks for any saved data
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("message_visible");

            // if checks for visible data within saved Bundle
            // May not be needed, FIX
            if (isVisible) {

                /*
                    while loop used for adding saved data (from savedInstanceStateBundle) to
                    list
                 */
                int i = 1;
                while (i <= 10) {
                    // sets updateView to next TextView to update based off i
                    String getID = "shoppingItem_" + i;
                    int viewID = getResources().getIdentifier(getID, "id", getPackageName());
                    updateView = (TextView) findViewById(viewID);

                    // gets text from Bundle with same i value and sets it to updateView and value
                    String mText = "message_text" + i;
                    updateView.setText(savedInstanceState.getString(mText));
                    String value = updateView.getText().toString();

                    // if else for making sure placeholder 0's remain invisible
                    // possible FIX
                    if (value.equals("0")) {
                        updateView.setVisibility(View.INVISIBLE);
                    } else {
                        updateView.setVisibility(View.VISIBLE);
                    }
                    // increment i by 1
                    i++;
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Tests first view for the String value of "0"
        // if not a 0, adds all non-0 values to the bundle
        TextView firstView = findViewById(R.id.shoppingItem_1);
        if (firstView.getText().toString() != "0") {
            outState.putBoolean("message_visible", true);
            int i = 1;
            while (i <= 10) {
                String getID = "shoppingItem_" + i;
                int viewID = getResources().getIdentifier(getID, "id", getPackageName());
                updateView = (TextView) findViewById(viewID);

                // Check for finding and saving only non-0 values
                String value = updateView.getText().toString();
                if (value != "0") {
                    String mText = "message_text" + i;
                    outState.putString(mText, updateView.getText().toString());
                }
                i++;
            }
        }
    }

    /*
        Skeleton state methods for testing purposes
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

    // method for sending intent to ShoppingItems list with request code
    public void launchAddItemList(View view) {
        Intent additem = new Intent(this, ShoppingItems.class);
        startActivityForResult(additem, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Chesks for matching request code and result ok code (-1)
        if(requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String item = data.getStringExtra(ShoppingItems.EXTRA_MESSAGE);

                // do while loop that checks for the next empty space on the list
                // empty spaces are marked with an invisible "0" to avoid
                // null pointer errors
                int i = 0, check = 0, viewID = 0;
                String value;
                do {
                    i++;
                    String getID = "shoppingItem_" + i;
                    viewID = getResources().getIdentifier(getID, "id", getPackageName());
                    updateView = (TextView) findViewById(viewID);

                    value = updateView.getText().toString();
                    if (value.equals("0")) {
                        check = 1;
                    }
                } while (check != 1);

                // sets new item to the list and makes it visible
                updateView.setText(item);
                updateView.setVisibility(View.VISIBLE);
            }
        }
    }
}
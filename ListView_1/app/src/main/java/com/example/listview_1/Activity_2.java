package com.example.listview_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_2 extends AppCompatActivity {

    int i;
    EditText editTextTop;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        editTextTop = findViewById(R.id.editTextTopic);
        editText = findViewById(R.id.editText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            i = extras.getInt("object");
            editTextTop.setText(MainActivity.list[i]);
            editText.setText(MainActivity.list_znach[i]);
        }
    }

    public void onClickBtnAddResult(View view) {
        Intent data = new Intent();
        data.putExtra("Topic", String.valueOf(editTextTop.getText()));
        data.putExtra("Text", String.valueOf(editText.getText()));
        data.putExtra("id", String.valueOf(i));
        setResult(RESULT_OK, data);
        finish();
    }
}

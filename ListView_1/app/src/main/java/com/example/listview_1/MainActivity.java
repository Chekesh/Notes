package com.example.listview_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static String[] list = {"В магазин", "Дела", "Работа", "Не забыть", "Поговорить"};
    static int in = 1;

    static String[] list_znach = {"Cходить в магазин купить:\n 1) Молоко \n 2) Макакронны \n 3) Сахар \n 4) Чай \n", "Дела", "Работа", "ЗАбыть", "не поговорить"};

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ListView = findViewById(R.id.list);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        ListView.setAdapter(adapter);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Activity_2.class);
                intent.putExtra("object", i);
                mStartForResult.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String Topic = intent.getStringExtra("Topic");
                        String Text = intent.getStringExtra("Text");
                        String i = intent.getStringExtra("id");
                        list[Integer.parseInt(i)] = String.valueOf(Topic);
                        list_znach[Integer.parseInt(i)] = String.valueOf(Text);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    }
                }
            });
}
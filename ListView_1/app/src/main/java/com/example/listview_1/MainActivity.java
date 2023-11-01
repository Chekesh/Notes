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

    // Массив элементов для списка
    static String[] list = {"В магазин", "Дела", "Работа", "Не забыть", "Поговорить"};

    // Массив для хранения дополнительной информации о элементах списка
    static String[] list_znach = {
            "Cходить в магазин купить:\n 1) Молоко \n 2) Макакронны \n 3) Сахар \n 4) Чай \n",
            "Дела",
            "Работа",
            "ЗАбыть",
            "не поговорить"
    };

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Нахождение ListView в макете
        ListView ListView = findViewById(R.id.list);

        // Создание ArrayAdapter для ListView
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        // Установка адаптера для ListView
        ListView.setAdapter(adapter);

        // Установка слушателя нажатия на элемент списка
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Создание намерения для запуска Activity_2 и передача данных в него
                Intent intent = new Intent(MainActivity.this, Activity_2.class);
                intent.putExtra("object", i);
                mStartForResult.launch(intent); // Запуск Activity_2 и ожидание результата
            }
        });
    }

    // Регистрация для получения результата от Activity_2
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Обработка результата от Activity_2
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Извлечение данных из возвращённого намерения
                        Intent intent = result.getData();
                        String Topic = intent.getStringExtra("Topic");
                        String Text = intent.getStringExtra("Text");
                        String i = intent.getStringExtra("id");

                        // Обновление массивов новыми данными
                        list[Integer.parseInt(i)] = String.valueOf(Topic);
                        list_znach[Integer.parseInt(i)] = String.valueOf(Text);

                        // Уведомление адаптера о изменении данных
                        adapter.notifyDataSetChanged();
                    } else {
                        // Показ сообщения toast в случае ошибки
                        Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    }
                }
            });
}

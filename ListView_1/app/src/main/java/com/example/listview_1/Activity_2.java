package com.example.listview_1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class Activity_2 extends AppCompatActivity {

    int i;  // Индекс элемента списка
    EditText editTextTop;  // Поле для ввода темы
    EditText editText;     // Поле для ввода текста

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Нахождение полей ввода в макете Activity_2
        editTextTop = findViewById(R.id.editTextTopic);
        editText = findViewById(R.id.editText);

        // Получение данных из предыдущей активности
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            i = extras.getInt("object");  // Получение индекса выбранного элемента
            editTextTop.setText(MainActivity.list[i]);  // Установка текста в поле для темы
            editText.setText(MainActivity.list_znach[i]);  // Установка текста в поле для текста
        }
    }

    // Метод, вызываемый при нажатии кнопки "Добавить результат"
    public void onClickBtnAddResult(View view) {
        Intent data = new Intent();

        // Помещение данных в Intent для возврата обратно в предыдущую активность
        data.putExtra("Topic", String.valueOf(editTextTop.getText()));  // Добавление темы
        data.putExtra("Text", String.valueOf(editText.getText()));       // Добавление текста
        data.putExtra("id", String.valueOf(i));                         // Добавление индекса

        // Установка результата как "RESULT_OK" и передача данных обратно
        setResult(RESULT_OK, data);
        finish();  // Завершение текущей активности и возврат к предыдущей
    }
}

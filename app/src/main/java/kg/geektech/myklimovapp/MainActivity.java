package kg.geektech.myklimovapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import kg.geektech.myklimovapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int mCounter;

    // имя файла настройки
    public static final String APP_PREFERENCES = "mySettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        buttonCounterClick();
    }

    private void buttonCounterClick() {
        binding.buttonCounter.setOnClickListener(v -> {
            // Выводим на экран
            String numberString = String.valueOf(mCounter++);
            binding.textViewInfo.setText(numberString);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            mCounter = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
            // Выводим на экран данные из настроек
            String numberString = String.valueOf(mCounter++);
            binding.textViewInfo.setText(numberString);
            Log.e("TAG", "buttonCrowsCounterClick: " + mCounter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, mCounter);
        editor.apply();
    }

}
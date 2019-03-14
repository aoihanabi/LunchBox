package com.apps.wag.lunchbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button goRecipebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goRecipebook = (Button) findViewById(R.id.btnGoRecipeBook);
        goRecipebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeBook();
            }
        });
    }

    private void openRecipeBook() {
        Intent inten = new Intent(this, MyRecipeBook.class);
        startActivity(inten);
    }
}

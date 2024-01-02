package maen.com.example.assigmenttow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button btnImage,btntow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnImage = findViewById(R.id.btnImage);
        btntow = findViewById(R.id.btntow);
    }

    public void onClickImage(View view) {

        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

    public void onClickCat(View view) {

        Intent intent2 = new Intent(this, cats.class);
        startActivity(intent2);
    }
}

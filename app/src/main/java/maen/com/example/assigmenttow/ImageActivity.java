package maen.com.example.assigmenttow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class ImageActivity extends AppCompatActivity {

    private RequestQueue queue;
    private Button btnrun;
    private TextView output;
    private EditText number;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        queue = Volley.newRequestQueue(this);
        btnrun = findViewById(R.id.btnrun);
        output= findViewById(R.id.outputcat);
        number = findViewById(R.id.number);

        btnrun.setOnClickListener(view -> {
            String inputNumber = number.getText().toString().trim();

            if (!inputNumber.isEmpty()) {
                Integer parsedNumber = Integer.parseInt(inputNumber);

                if (parsedNumber != null && parsedNumber >= 1 && parsedNumber <= 5000) {
                    fetchDataFromApi(parsedNumber);
                } else {
                    output.setText("Invalid input. Enter a number between 1 and 5000.");
                }
            } else {
                output.setText("Please enter a number.");
            }
        });
    }

    private void fetchDataFromApi(int number) {
        String url = "https://jsonplaceholder.typicode.com/photos/" + number;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String imageUrl = response.getString("url");

                        ImageView image = findViewById(R.id.image);
                        Picasso.get().load(imageUrl).into(image);

                        String title = response.getString("title");
                        output.setText("Title: " + title);
                    } catch (JSONException exception) {
                        Log.d("Error", exception.toString());
                        Log.d("volley_error", exception.toString());
                    }
                },
                error -> {
                    Log.e("Volley Error", error.toString());
                    output.setText("Error: " + error.getMessage());
                }
        );

        queue.add(jsonObjectRequest);
    }


}


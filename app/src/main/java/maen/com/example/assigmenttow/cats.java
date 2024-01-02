package maen.com.example.assigmenttow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class cats extends AppCompatActivity {

    private RequestQueue queue;
    private Button btnrun;
    private TextView outputcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image); 

        queue = Volley.newRequestQueue(this);
        btnrun = findViewById(R.id.btnrun);
        outputcat = findViewById(R.id.outputcat);

        btnrun.setOnClickListener(view -> {
            fetchDataFromCatFactApi();
        });
    }

    private void fetchDataFromCatFactApi() {
        String url = "https://catfact.ninja/fact";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String catFact = response.getString("fact");

                        outputcat.setText("Cat Fact: " + catFact);
                    } catch (JSONException exception) {
                        Log.d("Error", exception.toString());
                        Log.d("volley_error", exception.toString());
                    }
                },
                error -> {
                    Log.e("Volley Error", error.toString());
                    outputcat.setText("Error: " + error.getMessage());
                }
        );

        queue.add(jsonObjectRequest);
    }
}

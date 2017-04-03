package softwareacademy.superhero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import softwareacademy.superhero.services.SuperheroIntentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, SuperheroIntentService.class));
    }
}

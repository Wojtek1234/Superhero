package softwareacademy.superhero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import softwareacademy.superhero.utils.ViewsUtils;

public class MainActivity extends AppCompatActivity {


    private AppCompatTextView intentServiceView, bindServiceView;



    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();

        intentServiceView = ViewsUtils.findView(this, R.id.intent_value);
        bindServiceView = ViewsUtils.findView(this, R.id.bind_value);

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}

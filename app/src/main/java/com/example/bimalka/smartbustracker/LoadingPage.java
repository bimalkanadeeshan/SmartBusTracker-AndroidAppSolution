package com.example.bimalka.smartbustracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class LoadingPage extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.about:
                return true;
            case R.id.contactUs:
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        /*boolean _active = true;
        int _splashTime = 2500;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 = new Intent(LoadingPage.this, HomePage.class);
                startActivity(i3);
            }
        }, _splashTime);*/
    }
    public void selectOption(View view){
        Switch swtOption=(Switch) findViewById(R.id.swtOption);
        if(swtOption.isChecked()){
            Intent intent=new Intent(getApplicationContext(),WebLoadingPage.class);
            startActivity(intent);
        }else{
            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }
    }
}

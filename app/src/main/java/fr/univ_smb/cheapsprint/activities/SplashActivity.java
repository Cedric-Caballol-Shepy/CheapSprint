package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fr.univ_smb.cheapsprint.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Temps d'attente pour le splash
        Thread chrono = new Thread() {
            @Override
            public void run() {
                //super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        };
        chrono.start();
        //Animation
        //Animation animation = AnimationUtils.loadAnimation(this, )
        //ImageView image = (ImageView) findViewById(R.id.imageViewSplash);
        //image.animate().rotationY(360).setDuration(2000).start();
    }
}

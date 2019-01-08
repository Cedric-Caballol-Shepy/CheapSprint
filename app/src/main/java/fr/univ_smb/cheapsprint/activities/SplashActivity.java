package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.receivers.MyReceiverBroadcast;

public class SplashActivity extends Activity {
    private MyReceiverBroadcast myReceiverBroadcast;
    private IntentFilter intentFilterNetWork;
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

                    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String action = intent.getAction();
                            if (action.equals("ENDACTIVITY"))
                                finish();
                        }
                    };
                    registerReceiver(broadcastReceiver, new IntentFilter("ENDACTIVITY"));

                    // Listen Network
                    myReceiverBroadcast = new MyReceiverBroadcast();
                    intentFilterNetWork = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                    registerReceiver(myReceiverBroadcast, intentFilterNetWork);
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

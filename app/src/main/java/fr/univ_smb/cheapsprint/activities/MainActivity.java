package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.receivers.MyReceiverBroadcast;

public class MainActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private MyReceiverBroadcast myReceiverBroadcast;
    private IntentFilter intentFilterNetWork,
            intentFilterGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listen Network and GPS
        myReceiverBroadcast = new MyReceiverBroadcast();
        intentFilterNetWork = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilterGPS = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
        registerReceiver(myReceiverBroadcast, intentFilterNetWork);
        registerReceiver(myReceiverBroadcast, intentFilterGPS);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Get picture taked
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bitmap bitmap = (Bitmap)data.getExtras().get("data");
    }

    ///////////////////////////////////////////////
    // BUTTON EVENTS
    //////////////////////////////////////////////
    public void btn_scan_list_clicked(View view) {
        Intent intent = new Intent(this, ShoppingActivity.class);
        startActivity(intent);
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }*/

    }

    public void btn_scan_article_clicked(View view) {
        Toast.makeText(this, "Button not ready", Toast.LENGTH_SHORT).show();
    }

    public void btn_tools_clicked(View view) {
        Toast.makeText(this, "Button not ready", Toast.LENGTH_SHORT).show();
    }


    public void btn_submit_clicked(View view) {
        Intent intent = new Intent(this, CollaborativeActivity.class);
        startActivity(intent);
    }
}

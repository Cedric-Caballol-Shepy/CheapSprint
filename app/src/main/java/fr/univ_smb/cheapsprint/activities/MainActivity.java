package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fr.univ_smb.cheapsprint.R;
import fr.univ_smb.cheapsprint.receivers.MyReceiverBroadcast;

public class MainActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        intent.putExtra("NOMLISTE", "");
        startActivity(intent);

    }

    public void btn_my_lists_clicked(View view){
        Intent intent = new Intent(this, MyListsActivity.class);
        startActivity(intent);
    }

    public void btn_scan_article_clicked(View view) {
        //Toast.makeText(this, "Button not ready", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void btn_tools_clicked(View view) {
        Toast.makeText(this, "Button not ready", Toast.LENGTH_SHORT).show();
        /**Intent intent = new Intent(this, .class);
        startActivity(intent);*/
    }


    public void btn_submit_clicked(View view) {
        Intent intent = new Intent(this, CollaborativeActivity.class);
        startActivity(intent);
    }

    public void btn_exit_clicked(View view) {
        moveTaskToBack(true);
        Intent intent = new Intent("ENDACTIVITY");
        sendBroadcast(intent);
    }
}

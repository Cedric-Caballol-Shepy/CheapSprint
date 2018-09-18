package fr.univ_smb.cheapsprint.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyReceiverBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO Display a dialog when not network or gps disabled
        if (checkNetwork(context))
            Toast.makeText(context, "Network enabled", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Network disabled", Toast.LENGTH_SHORT).show();
        if (checkGPS(context)) Toast.makeText(context, "GPS enabled", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "GPS disabled", Toast.LENGTH_SHORT).show();
    }

    /**
     * Check if network is available
     *
     * @param context
     * @return true or false
     */
    private boolean checkNetwork(Context context) {
        NetworkInfo internet = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return internet != null && internet.isAvailable() && internet.isConnectedOrConnecting();
    }

    /**
     * Check if GPS is available
     *
     * @param context
     * @return true or false
     */
    private boolean checkGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}

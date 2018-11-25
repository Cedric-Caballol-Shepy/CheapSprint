package fr.univ_smb.cheapsprint.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(result.getText()).setTitle("Code barre");
        onBackPressed();
    }
}

package com.tvs.maintenance.util.ui.barcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.vision.barcode.Barcode;
import com.tvs.maintenance.R;

import java.util.List;


public class BarcodeReaderActivity extends Activity implements BarcodeReaderFragment.BarcodeReaderListener {
    private static final String KEY_AUTO_FOCUS = "key_auto_focus";
    private static final String KEY_USE_FLASH = "key_use_flash";
    public static String KEY_CAPTURED_BARCODE = "key_captured_barcode";
    public static String KEY_CAPTURED_RAW_BARCODE = "key_captured_raw_barcode";
    AlertDialog.Builder dialog;
    CountDownTimer cdt;
    private boolean autoFocus = false;
    private boolean useFlash = false;
    private BarcodeReaderFragment mBarcodeReaderFragment;

    public static Intent getLaunchIntent(Context context, boolean autoFocus, boolean useFlash) {
        Intent intent = new Intent(context, BarcodeReaderActivity.class);
        intent.putExtra(KEY_AUTO_FOCUS, autoFocus);
        intent.putExtra(KEY_USE_FLASH, useFlash);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new AlertDialog.Builder(BarcodeReaderActivity.this);
        dialog.setTitle("¿Necesitas más tiempo?");
        dialog.setMessage("Superaste el tiempo máximo para escanear el código del chasis.\n" +
                "¿Deseas escanear de nuevo?");
        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                reloadTimer();
            }
        });
        dialog.setNegativeButton("Regresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                setResult(-1);
                BarcodeReaderActivity.this.finish();
            }
        });
        dialog.setCancelable(false);
        dialog.create();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barcode_reader);
        cdt = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("Info", "seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                showAlertDialog();
            }

        };
        cdt.start();
        final Intent intent = getIntent();
        if (intent != null) {
            autoFocus = intent.getBooleanExtra(KEY_AUTO_FOCUS, false);
            useFlash = intent.getBooleanExtra(KEY_USE_FLASH, false);
        }
        mBarcodeReaderFragment = attachBarcodeReaderFragment();
    }

    private void reloadTimer() {
        if (cdt != null) {
            cdt.start();
        }
    }

    private void showAlertDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    private BarcodeReaderFragment attachBarcodeReaderFragment() {
        final FragmentManager supportFragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        BarcodeReaderFragment fragment = BarcodeReaderFragment.newInstance(autoFocus, useFlash);
        fragment.setListener(this);
        fragmentTransaction.replace(R.id.fm_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void onScanned(Barcode barcode) {
        if (mBarcodeReaderFragment != null) {

            mBarcodeReaderFragment.pauseScanning();
        }
        if (cdt != null) {
            cdt.cancel();
        }
        if (barcode != null) {
            mBarcodeReaderFragment.playBeep();
            Intent intent = new Intent();
            intent.putExtra(KEY_CAPTURED_BARCODE, barcode);
            intent.putExtra(KEY_CAPTURED_RAW_BARCODE, barcode.rawValue);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {

    }

    @Override
    public void onBackPressed() {
        if (cdt != null) {
            cdt.cancel();
        }
        setResult(RESULT_FIRST_USER);
        BarcodeReaderActivity.this.finish();
        //super.onBackPressed();
    }
}

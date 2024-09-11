package com.tvs.maintenance.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.tvs.maintenance.R;
import com.tvs.maintenance.util.RequestPermissionHandler;
import com.tvs.maintenance.util.constants.Constantes;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity implements RequestPermissionHandler.RequestPermissionListener {
    public final int REQUEST_PERMISSION = 772;
    RequestPermissionHandler rph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar pb = findViewById(R.id.pb_splash);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    requestPermisos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        //ProgressBarAnimation anim = new ProgressBarAnimation(pb,1, 100);
        //anim.setDuration(5000);
        // pb.startAnimation(anim);
    }

    public void requestPermisos() {
        rph = RequestPermissionHandler.getInstance();
        rph.requestPermission(SplashActivity.this, Constantes.LIST_OF_PERMISSION, REQUEST_PERMISSION, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        rph.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }

    private void goToLogin() {
        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    public void onSuccess() {
        goToLogin();
    }

    @Override
    public void onFailed() {
        AlertDialog.Builder adb = new AlertDialog.Builder(SplashActivity.this);
        adb.setMessage("Se requiere aceptar los permisos para que la aplicación funcione correctamente");
        adb.setTitle("TVS Perú");
        adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                rph.requestPermission(SplashActivity.this, Constantes.LIST_OF_PERMISSION, REQUEST_PERMISSION, SplashActivity.this);
            }
        });
        adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SplashActivity.this.finish();
            }
        });
        adb.create();
        adb.show();
    }
}

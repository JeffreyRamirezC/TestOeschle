package com.tvs.maintenance.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tvs.maintenance.R;
import com.tvs.maintenance.fragments.OrdenTrabajoFragment;
import com.tvs.maintenance.fragments.OrdenTrabajoListFragment;
import com.tvs.maintenance.fragments.VehicleIntroDocFragment;
import com.tvs.maintenance.util.constants.UserEntries.OrdenTrabajoFichaIngreso;
import com.tvs.maintenance.util.ui.barcode.BarcodeReaderActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    public final int TAG_READ_CHASIS=289;
    public final String TAG_NEW_ORDEN_TRABAJO="OrdenTrabajoList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorBackground));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return false;
    }
    public void addNewOrdenTrabajo(){
        Fragment fragment=new VehicleIntroDocFragment();
        getSupportActionBar().setTitle("Nueva Orden de Trabajo");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, TAG_NEW_ORDEN_TRABAJO);
        ft.commit();
    }
    public void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        String TAG = "";
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_validate_chasis:
                getSupportActionBar().setTitle(getString(R.string.menu_validate_chasis));
                Intent i=new Intent(MainActivity.this, BarcodeReaderActivity.class);
                startActivityForResult(i,TAG_READ_CHASIS);
                break;
             case R.id.nav_inspeccion:
                    fragment=new OrdenTrabajoListFragment();
                 getSupportActionBar().setTitle(getString(R.string.menu_inspeccion));
                 getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                 ft.replace(R.id.content_frame, fragment, TAG);
                 ft.commit();
                 break;
            case R.id.nav_validate_client:
                getSupportActionBar().setTitle(getString(R.string.menu_validate_client));
                addNewOrdenTrabajo();
                break;

            case R.id.nav_logout:
                MainActivity.this.finish();

                break;
        }

        //replacing the fragment


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}

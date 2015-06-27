package edu.ulima.estacionapp.ui.main;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.ulima.estacionapp.R;
import edu.ulima.estacionapp.Servicios.UserController;
import edu.ulima.estacionapp.ui.items.EmpresaListaReservasFragment;
import edu.ulima.estacionapp.ui.items.MapActivity;


public class MainActivity extends ActionBarActivity {

    UserController instance;
    String NAME;
    String EMAIL;
    int PROFILE;
    private Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=UserController.getInstance();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        fm =getSupportFragmentManager();
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        startUser();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getResources().getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
                //setTitle("Cerraron :c");
            }



        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private void startUser(){
        int type =instance.getUsuario().getType();
        Fragment fragment = null;
        switch (type){
            case 0:
                toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_cliente)));
                NAME = instance.getUsuario().getNombre();
                EMAIL = instance.getUsuario().getCorreo();
                PROFILE = R.drawable.login_icon;
                String TITLES0[] = {"Ver Reservas","Nuevo Auto","Logout"};
                int ICONS0[] = {R.drawable.ic_home, R.drawable.ic_events,R.drawable.ic_mail};
                mAdapter = new MenuAdapter(TITLES0,ICONS0,NAME,EMAIL,PROFILE,this,fm,Drawer);
                fragment = new MapActivity();

                 fm.beginTransaction().replace(R.id.container, fragment).commit();


                break;
            case 1:
                toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_business)));
                String TITLES1[] = {"Estacionamiento","Gestionar Reservas","Registrar Promocion(es)","Logout"};
                int ICONS1[] = {R.drawable.ic_home, R.drawable.ic_events,R.drawable.ic_mail,R.drawable.ic_shop};
                mAdapter = new MenuAdapter(TITLES1,ICONS1,NAME,EMAIL,PROFILE,this,fm,Drawer);
                fragment = new EmpresaListaReservasFragment();
                fm.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case 2:
                //toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_business)));
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
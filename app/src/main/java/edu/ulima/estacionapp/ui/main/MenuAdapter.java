package edu.ulima.estacionapp.ui.main;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.ulima.estacionapp.R;
import edu.ulima.estacionapp.Servicios.UserController;
import edu.ulima.estacionapp.ui.items.ClienteVerReservasActivity;
import edu.ulima.estacionapp.ui.items.RegEstacionamientoActivity;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mNavTitles[];
    private int mIcons[];
    private String name;
    private int profile;
    private String email;
    static Context context;
    static FragmentManager fm;
    static DrawerLayout Drawer;
    static private View vista;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        TextView textView;

        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        Context contxt;

        public ViewHolder(View itemView,int ViewType,Context c) {
            super(itemView);
            contxt = c;
            vista = itemView;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            }
            else{
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                Holderid = 0;
            }
        }

        @Override
        public void onClick(View v) {

            Intent intent=null;
            //Fragment fragment = null;
            int position = getPosition();
            String title="";
            Bundle args = new Bundle();
            switch(position) {
                case 0:
                    //fragment = new Fragment_items();
                    //posicion del perfil
                    break;
                case 1:
                    if(UserController.getInstance().getUsuario().getType()==0)
                    //intent = new Intent(contxt,ClienteVerReservasActivity.class);
                    Toast.makeText(contxt,"Proximamente.",Toast.LENGTH_SHORT).show();
                    else
                        intent = new Intent(contxt,RegEstacionamientoActivity.class);
                    break;
                case 2:
                    Toast.makeText(contxt,"Proximamente.",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(contxt,"Proximamente.",Toast.LENGTH_SHORT).show();
                    break;
            }
            if(intent !=null)
                v.getContext().startActivity(intent);
                //v.startActivities(intent);
            /*if(fm!=null)
                fm.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();*/
            Drawer.closeDrawers();
        }
    }

    MenuAdapter(String Titles[], int Icons[], String Name, String Email, int Profile, Context passedContext, FragmentManager fm, DrawerLayout Drawer){
        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
        this.context = passedContext;
        this.fm = fm;
        this.Drawer = Drawer;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
            ViewHolder vhItem = new ViewHolder(v,viewType,context);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);
            ViewHolder vhHeader = new ViewHolder(v,viewType,context);
            return vhHeader;
        }
        return null;

    }
    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position -1]);

        }
        else{

            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
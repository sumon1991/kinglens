package com.king.king_lens.Grid_List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.king.king_lens.R;

import java.util.ArrayList;
import java.util.List;

public class Gridlist_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;


    ImageButton gridlistbtn;
    ImageButton filterbtn;
    ImageButton  firebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlist_);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        getProductList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();



        gridlistbtn=(ImageButton)findViewById(R.id.listicon);
        filterbtn=(ImageButton)findViewById(R.id.filter);
        firebtn=(ImageButton)findViewById(R.id.fire);


        //onclick listbtn

        gridlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();
            }

        });




        //onclick  filter button


        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Filter_Activity.class);
                startActivity(i);
            }
        });



        //setting background dim when showing popup
      final RelativeLayout back_dim_layout = (RelativeLayout) findViewById(R.id.bac_dim_layout);
        //onclick firebutton

        firebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Popup_Activity.class);
               // startActivity(i);

                LayoutInflater layoutInflater =
                        (LayoutInflater)getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_popup_, null);

                final PopupWindow popupWindow = new PopupWindow(
                       popupView, ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAsDropDown(firebtn,50,30);

                back_dim_layout.setVisibility(View.VISIBLE);
            }
        });





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }


    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
        //pseudo code to get product, replace your code to get real product here
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.w1, "Title 1", "This is description 1"));
        productList.add(new Product(R.drawable.w2, "Title 2", "This is description 2"));
        productList.add(new Product(R.drawable.w3, "Title 3", "This is description 3"));
        productList.add(new Product(R.drawable.w4, "Title 4", "This is description 4"));
        productList.add(new Product(R.drawable.w5, "Title 5", "This is description 5"));
        productList.add(new Product(R.drawable.w1, "Title 6", "This is description 6"));
        productList.add(new Product(R.drawable.w2, "Title 7", "This is description 7"));
        productList.add(new Product(R.drawable.w3, "Title 8", "This is description 8"));
        productList.add(new Product(R.drawable.w4, "Title 9", "This is description 9"));
        productList.add(new Product(R.drawable.w5, "Title 10", "This is description 10"));

        return productList;
    }


    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();



        }
    };






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gridlist_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
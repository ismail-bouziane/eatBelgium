package com.helb.eatBelgium.Controlers.Activities;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.helb.eatBelgium.Common.Common;
import com.helb.eatBelgium.Controlers.Fragments.ContactFragment;
import com.helb.eatBelgium.Controlers.Fragments.OrdersFragment;
import com.helb.eatBelgium.Controlers.Fragments.PanierFragment;
import com.helb.eatBelgium.Controlers.Fragments.PlatsFragment;
import com.helb.eatBelgium.R;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        //FOR DESIGN
        private Toolbar toolbar;
        private DrawerLayout drawerLayout;


        //For fragments
        private Fragment fragmentPlats;
        private Fragment fragmentPanier;
        private Fragment fragmentOrders;
        private Fragment fragmentContact;

        //for datas
      //  private static final int FRAGMENTS_PLATS=0;
        private static final int FRAGMENTS_PANIER=0;
        private static final int FRAGMENTS_ORDERS=1;
        private static final int FRAGMENTS_CONTACT=2;
        private static final int FRAGMENTS_PLATS=3;


        TextView txtFullName;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home_activity); // activity_home_drawer_layout


            this.showFirstFragment();
            // 6 - Configure all views

            this.configureToolBar();

            this.configureDrawerLayout();

            this.configureNavigationView();

         //   txtFullName = findViewById(R.id.txtFullName);
          // txtFullName.setText((Common.currentUser.getName()));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
       // fragmentTransaction.replace(R.id.activity_home_drawer_layout,new PlatsFragment());
        fragmentTransaction.commit();
        }

        @Override
        public void onBackPressed() {
            // 5 - Handle back click to close menu
            if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }


        @Override
        public boolean onNavigationItemSelected(MenuItem item) {

            // 4 - Handle Navigation Item Click
            int id = item.getItemId();

            switch (id){
               // case R.id.activity_home_drawer_plats :
                 //   this.showFragment(FRAGMENTS_PLATS);
                case R.id.activity_home_drawer_panier :
                    this.showFragment(FRAGMENTS_PANIER);
                    break;
                case R.id.activity_home_drawer_orders:
                    this.showFragment(FRAGMENTS_ORDERS);
                    break;
                case R.id.activity_home_drawer_contact:
                    this.showFragment(FRAGMENTS_CONTACT);
                    break;
                case R.id.activity_home_drawer_plats :
                    this.showFragment(FRAGMENTS_PLATS);
                default:
                    break;
            }

            this.drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }

        // ---------------------
        // CONFIGURATION
        // ---------------------

        // 1 - Configure Toolbar

        private void configureToolBar(){
            this.toolbar = (Toolbar) findViewById(R.id.activity_home_toolbar);
            setSupportActionBar(toolbar);
        }

        // 2 - Configure Drawer Layout
        private void configureDrawerLayout(){
            this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_home_drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        // 3 - Configure NavigationView
        private void configureNavigationView(){
            NavigationView navigationView = (NavigationView) findViewById(R.id.activity_home_nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
        //Fragments
        private void showFragment(int fragmentIdentifier) {
            switch (fragmentIdentifier) {
               // case FRAGMENTS_PLATS:
                  //  this.showPlatsFragment();
                case FRAGMENTS_PANIER:
                    this.showPanierFragment();
                    break;
                case FRAGMENTS_ORDERS:
                    this.showOrdersFragment();
                    break;
                case FRAGMENTS_CONTACT:
                    this.showContactFragment();
                    break;
                case FRAGMENTS_PLATS:
                    this.showPlatsFragment();
                default:
                    break;
            }
        }
    private void showFirstFragment(){

        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_plat_layout);
        if (visibleFragment == null){
            // 1.1 - Show News Fragment
            this.showFragment(FRAGMENTS_PLATS);
            // 1.2 - Mark as selected the menu item corresponding to NewsFragment
          //  this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }
   // Create each fragment page and show it
    private void showPlatsFragment(){
        if (this.fragmentPlats == null) this.fragmentPlats = PlatsFragment.newInstance();
        this.startTransactionFragment(this.fragmentPlats);
    }
    private void showPanierFragment(){
        if (this.fragmentPanier == null) this.fragmentPanier = PanierFragment.newInstance();
        this.startTransactionFragment(this.fragmentPanier);
    }

    private void showOrdersFragment(){
        if (this.fragmentOrders == null) this.fragmentOrders = OrdersFragment.newInstance();
        this.startTransactionFragment(this.fragmentOrders);
    }

    private void showContactFragment(){
        if (this.fragmentContact == null) this.fragmentContact = ContactFragment.newInstance();
        this.startTransactionFragment(this.fragmentContact);
    }

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_home_frame_layout, fragment).commit();
        }
    }
   
}


package com.example.myrecyclerview;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Hero> list = new ArrayList<>();
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        list = new ArrayList<>();
        if (savedInstanceState == null) {
            list.addAll(HeroesData.getListData());
            setTitleActionBar("Mode List");
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Hero> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setTitleActionBar(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                mode = selectedMode;
                title = "Mode List";
                showRecyclerList();
                break;

            case R.id.action_grid:
                mode = selectedMode;
                title = "Mode Grid";
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                mode = selectedMode;
                title = "Mode CardView";
                showRecyclerCardView();
                break;

            case R.id.menu1:
                Toast.makeText(this, "Menu1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu2:
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
        }

        setTitleActionBar(title);
    }

    private void setTitleActionBar(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter();
        listHeroAdapter.setContext(this);
        listHeroAdapter.setHeroes(list);
        rvCategory.setAdapter(listHeroAdapter);
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this,2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter();
        gridHeroAdapter.setContext(this);
        gridHeroAdapter.setHeroes(list);
        rvCategory.setAdapter(gridHeroAdapter);
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardHeroAdapter cardViewHeroAdapter = new CardHeroAdapter();
        cardViewHeroAdapter.setContext(this);
        cardViewHeroAdapter.setHeroes(list);
        rvCategory.setAdapter(cardViewHeroAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setSearch(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }

    private void setSearch(final Menu menu){

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        if (searchManager != null) {
            searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

        }

    }


}

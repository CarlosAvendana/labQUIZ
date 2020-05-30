package com.example.labotorio_quiz.Actividades;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.labotorio_quiz.AccesoDatos.AsyncResponse;
import com.example.labotorio_quiz.AccesoDatos.NetManager;
import com.example.labotorio_quiz.Adaptador.EstudianteAdapter;
import com.example.labotorio_quiz.Helper.RecyclerItemTouchHelper;
import com.example.labotorio_quiz.Logic.Estudiante;
import com.example.labotorio_quiz.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EstudianteAdapter.EstudianteAdapterListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private RecyclerView mRecyclerView;
    private EstudianteAdapter mAdapter;
    private List<Estudiante> carreraList = new ArrayList<>();
    //private ModelData model;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.parseColor("#0288D1"));
        }
    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Estudiante aux;
            aux = (Estudiante) getIntent().getSerializableExtra("addCarrera");
            if (aux == null) {
                aux = (Estudiante) getIntent().getSerializableExtra("editCarrera");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Estudiante carrera : carreraList) {
                        if (carrera.getId() == aux.getId()) {
                            carrera.setNombre(aux.getNombre());
                            carrera.setApellido(aux.getApellido());
                            carrera.setEdad(aux.getEdad());

                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new Carrera Object
                carreraList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " Agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onContactSelected(Estudiante carrera) { //TODO get the select item of recycleView
        Toast.makeText(getApplicationContext(), "Seleccionado: " + carrera.getNombre(), Toast.LENGTH_LONG).show();
    }

    public void goToAddUpdCarrera() {
        Intent intent = new Intent(this, AddUpdEstudiante.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }

    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof EstudianteAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = carreraList.get(viewHolder.getAdapterPosition()).getNombre();
                String delete = carreraList.get(viewHolder.getAdapterPosition()).getApellido();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());
                NetManager net = new NetManager("http://192.168.100.10:8084/GestionAcademica/Server_Movil_Carrera?codigo=" + delete, new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {

                    }
                });
                net.execute(NetManager.DELETE);


                Toast.makeText(getApplicationContext(), name + " removido!", Toast.LENGTH_LONG).show();

            }
        } else {
            //If is editing a row object
            Estudiante aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdEstudiante.class);
            intent.putExtra("editable", true);
            intent.putExtra("carrera", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds carreraList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

}
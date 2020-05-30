package com.example.labotorio_quiz.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labotorio_quiz.AccesoDatos.AsyncResponse;
import com.example.labotorio_quiz.AccesoDatos.NetManager;
import com.example.labotorio_quiz.Logic.Estudiante;
import com.example.labotorio_quiz.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class AddUpdEstudiante extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText idFld;
    private EditText nombreFld;
    private EditText apellidoFld;
    private EditText edadFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_estudiante);

        fBtn = findViewById(R.id.addUpdEstudianteBtn);

        idFld = findViewById(R.id.idAddUpdCar);
        nombreFld = findViewById(R.id.nombreAddUpdCar);
        apellidoFld = findViewById(R.id.apellidoAddUpdCar);
        edadFld = findViewById(R.id.edadAddUpdCar);

        idFld.setText("");
        nombreFld.setText("");
        apellidoFld.setText("");
        edadFld.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Estudiante aux = (Estudiante) getIntent().getSerializableExtra("carrera");
                idFld.setText(aux.getId());
                idFld.setEnabled(false);
                nombreFld.setText(aux.getNombre());
                apellidoFld.setText(aux.getApellido());
                edadFld.setText(aux.getEdad());

                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCarrera();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCarrera();
                    }
                });
            }
        }

    }



    public void addCarrera() {
        if (validateForm()) {
            //do something
            Estudiante car = new Estudiante(Integer.parseInt(idFld.getText().toString()),
                    nombreFld.getText().toString(),
                    apellidoFld.getText().toString(),
                    Integer.parseInt(edadFld.getText().toString())
            );

            String cod = idFld.getText().toString();
            String nom = nombreFld.getText().toString();
            String tit = apellidoFld.getText().toString();

            JSONObject carreer = new JSONObject();
            try {
                carreer.put("codigo", cod);
                carreer.put("nombre", nom);
                carreer.put("titulo", tit);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NetManager net = new NetManager("http://192.168.100.10:8084/GestionAcademica/Server_Movil_Carrera", new AsyncResponse() {
                @Override
                public void processFinish(String output) {

                }
            });
            net.execute(NetManager.PUT, carreer.toString());

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCarrera() {
        if (validateForm()) {
            Estudiante car = new Estudiante(Integer.parseInt(idFld.getText().toString()),
                    nombreFld.getText().toString(),
                    apellidoFld.getText().toString(),
                    Integer.parseInt(edadFld.getText().toString())
            );

            String cod = idFld.getText().toString();
            String nom = nombreFld.getText().toString();
            String tit = apellidoFld.getText().toString();

            JSONObject carreer = new JSONObject();
            try {
                carreer.put("codigo", cod);
                carreer.put("nombre", nom);
                carreer.put("titulo", tit);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NetManager net = new NetManager("http://192.168.100.10:8084/GestionAcademica/Server_Movil_Carrera", new AsyncResponse() {
                @Override
                public void processFinish(String output) {

                }
            });
            net.execute(NetManager.POST, carreer.toString());

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.idFld.getText())) {
            idFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.nombreFld.getText())) {
            nombreFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellidoFld.getText())) {
            apellidoFld.setError("Titulo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


}
package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class detEstudioMenuActivity extends Activity {

    ListViewAdapter adapter;

    String[] titulo = new String[]{
            "Ingresar",
            "Consultar",
            "Actualizar",
            "Eliminar",
    };

    int[] imagenes = {
            R.drawable.ingresar,
            R.drawable.consultar,
            R.drawable.actualizar,
            R.drawable.depurar
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_estudio_menu);

        final ListView lista = (ListView) findViewById(R.id.list_detEstudio);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent in = new Intent(detEstudioMenuActivity.this, detEstudioInsertarActivity.class);
                        startActivity(in);
                        break;

                    case 1:
                        Intent in2 = new Intent(detEstudioMenuActivity.this, detEstudioConsultarActivity.class);
                        startActivity(in2);
                        break;

                    case 2:
                        Intent in3 = new Intent(detEstudioMenuActivity.this, detEstudioActualizarActivity.class);
                        startActivity(in3);
                        break;

                    case 3:
                        Intent in4 = new Intent(detEstudioMenuActivity.this, detEstudioEliminarActivity.class);
                        startActivity(in4);
                        break;
                }
            }

        });
    }
}

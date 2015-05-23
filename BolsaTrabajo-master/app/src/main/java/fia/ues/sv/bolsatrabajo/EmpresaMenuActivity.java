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


public class EmpresaMenuActivity extends Activity {

    ListViewAdapter adapter;

    String[] titulo = new String[]{
            "Ingresar Empresa",
            "Consultar Empresa",
            "Actualizar Empresa",
            "Eliminar Empresa",
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
        setContentView(R.layout.activity_empresa_menu);

        final ListView lista = (ListView) findViewById(R.id.list_Empresa);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent in = new Intent(EmpresaMenuActivity.this, EmpresaInsertarActivity.class);
                        startActivity(in);
                        break;

                    case 1:
                        Intent in2 = new Intent(EmpresaMenuActivity.this, EmpresaConsultarActivity.class);
                        startActivity(in2);
                        break;

                    case 2:
                        Intent in3 = new Intent(EmpresaMenuActivity.this, EmpresaActualizarActivity.class);
                        startActivity(in3);
                        break;

                    case 3:
                        Intent in4 = new Intent(EmpresaMenuActivity.this, EmpresaEliminarActivity.class);
                        startActivity(in4);
                        break;
                }
            }

        });
    }
}

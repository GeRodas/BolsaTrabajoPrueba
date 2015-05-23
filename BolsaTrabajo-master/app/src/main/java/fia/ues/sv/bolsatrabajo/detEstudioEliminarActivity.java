package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class detEstudioEliminarActivity extends Activity {

    EditText editId_detalleest;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_estudio_eliminar);
        controlhelper=new ControlBD (this);
        editId_detalleest = (EditText)findViewById(R.id.editId_detalleest);
    }

    public void eliminardetEstudio(View v){

        String regEliminadas;

        detEstudio detestudio = new detEstudio();
        detestudio.setId_detalleest(Integer.parseInt(editId_detalleest.getText().toString()));

        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(detestudio);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}

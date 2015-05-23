package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class detEstudioActualizarActivity extends Activity {

    ControlBD helper;
    EditText editId_detalleest;
    EditText editId_empleado;
    EditText editId_especializacion;
    EditText editId_institutoestudio;
    EditText editAnygraduacion;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_estudio_actualizar);

        helper = new ControlBD(this);

        editId_detalleest = (EditText) findViewById(R.id.editId_detalleest);
        editId_empleado = (EditText) findViewById(R.id.editId_empleado);
        editId_especializacion = (EditText) findViewById(R.id.editId_especializacion);
        editId_institutoestudio = (EditText) findViewById(R.id.editId_institutoestudio);
        editAnygraduacion = (EditText) findViewById(R.id.editAnygraduacion);
    }


    public void actualizardetEstudio(View v) {

        detEstudio detestudio = new detEstudio();
        detestudio.setId_detalleest(Integer.parseInt(editId_detalleest.getText().toString()));
        detestudio.setAnygraduacion(Integer.parseInt(editAnygraduacion.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(detestudio);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editId_detalleest.setText("");
        editId_empleado.setText("");
        editId_especializacion.setText("");
        editId_institutoestudio.setText("");
        editAnygraduacion.setText("");
    }
}
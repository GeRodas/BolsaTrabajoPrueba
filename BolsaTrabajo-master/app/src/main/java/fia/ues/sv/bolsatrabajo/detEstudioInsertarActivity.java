package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class detEstudioInsertarActivity extends Activity {

    ControlBD helper;
    EditText editId_detalleest;
    EditText editId_empleado;
    EditText editId_especializacion;
    EditText editId_institutoestudio;
    EditText editAnygraduacion;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_estudio_insertar);

        helper = new ControlBD(this);

        editId_detalleest = (EditText) findViewById(R.id.editId_detalleest);
        editId_empleado = (EditText) findViewById(R.id.editId_empleado);
        editId_especializacion = (EditText) findViewById(R.id.editId_especializacion);
        editId_institutoestudio = (EditText) findViewById(R.id.editId_institutoestudio);
        editAnygraduacion = (EditText) findViewById(R.id.editAnygraduacion);
    }


    public void insertardetEstudio(View v) {

        String id_detalleest = editId_detalleest.getText().toString();
        String id_empleado = editId_empleado.getText().toString();
        String id_especializacion = editId_especializacion.getText().toString();
        String id_institutoestudio = editId_institutoestudio.getText().toString();
        String anygraduacion = editAnygraduacion.getText().toString();
        String regInsertados;

        detEstudio detestudio = new detEstudio();
        detestudio.setId_detalleest(Integer.parseInt(id_detalleest));
        detestudio.setId_empleado(Integer.parseInt(id_empleado));
        detestudio.setId_especializacion(Integer.parseInt(id_especializacion));
        detestudio.setId_institutoestudio(Integer.parseInt(id_institutoestudio));
        detestudio.setAnygraduacion(Integer.parseInt(anygraduacion));

        helper.abrir();
        regInsertados=helper.insertar(detestudio);
        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }


    public void limpiarTexto(View v) {
        editId_detalleest.setText("");
        editId_empleado.setText("");
        editId_especializacion.setText("");
        editId_institutoestudio.setText("");
        editAnygraduacion.setText("");
    }
}
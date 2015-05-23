package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class detEstudioConsultarActivity extends Activity {

    ControlBD helper;
    EditText editId_detalleest;
    EditText editId_empleado;
    EditText editId_especializacion;
    EditText editId_institutoestudio;
    EditText editAnygraduacion;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_estudio_consultar);
        helper = new ControlBD(this);

        editId_detalleest = (EditText) findViewById(R.id.editId_detalleest);
        editId_empleado = (EditText) findViewById(R.id.editId_empleado);
        editId_especializacion = (EditText) findViewById(R.id.editId_especializacion);
        editId_institutoestudio = (EditText) findViewById(R.id.editId_institutoestudio);
        editAnygraduacion = (EditText) findViewById(R.id.editAnygraduacion);
    }


    public void consultardetEstudio(View v) {

        helper.abrir();
        detEstudio detestudio = helper.consultardetEstudio(Integer.parseInt(editId_detalleest.getText().toString()));
        helper.cerrar();

        if(detestudio == null)
            Toast.makeText(this, "Detalle de estudio con ID: " + editId_detalleest.getText().toString() + ", no encontrado", Toast.LENGTH_LONG).show();
        else{
            editId_empleado.setText(detestudio.getId_empleado());
            editId_especializacion.setText(detestudio.getId_especializacion());
            editId_institutoestudio.setText(detestudio.getId_institutoestudio());
            editAnygraduacion.setText(detestudio.getAnygraduacion());
        }
    }

    public void limpiarTexto(View v) {
        editId_detalleest.setText("");
        editId_empleado.setText("");
        editId_especializacion.setText("");
        editId_institutoestudio.setText("");
        editAnygraduacion.setText("");
    }
}

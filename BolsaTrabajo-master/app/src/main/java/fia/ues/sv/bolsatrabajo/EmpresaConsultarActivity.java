package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EmpresaConsultarActivity extends Activity {

    ControlBD helper;
    EditText editId_empresa;
    EditText editNombre;
    EditText editNit;
    EditText editDir_empresa;
    EditText editTel_empresa;
    EditText editCantoferta_empresa;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_consultar);
        helper = new ControlBD(this);

        editId_empresa = (EditText) findViewById(R.id.editId_empresa);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editNit = (EditText) findViewById(R.id.editNit);
        editDir_empresa = (EditText) findViewById(R.id.editDir_empresa);
        editTel_empresa = (EditText) findViewById(R.id.editTel_empresa);
        editCantoferta_empresa = (EditText) findViewById(R.id.editCantoferta_empresa);
    }


    public void consultarEmpresa(View v) {

        helper.abrir();
        Empresa empresa = helper.consultarEmpresa(Integer.parseInt(editId_empresa.getText().toString()));
        helper.cerrar();

        if(empresa == null)
            Toast.makeText(this, "Empresa con ID: " + editId_empresa.getText().toString() + ", no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(empresa.getNombre_empresa());
            editNit.setText(empresa.getNit_empresa());
            editDir_empresa.setText(empresa.getDir_empresa());
            editTel_empresa.setText(empresa.getTel_empresa());
            editCantoferta_empresa.setText(String.valueOf(empresa.getCantoferta_empresa()));
        }
    }

    public void limpiarTexto(View v){
        editId_empresa.setText("");
        editNombre.setText("");
        editNit.setText("");
        editDir_empresa.setText("");
        editTel_empresa.setText("");
        editCantoferta_empresa.setText("");
    }
}
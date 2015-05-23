package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EmpresaActualizarActivity extends Activity {

    ControlBD helper;
    EditText Id_empresa;
    EditText editNombre;
    EditText editNit;
    EditText editDir_empresa;
    EditText editTel_empresa;
    EditText editCantoferta_empresa;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_actualizar);

        helper = new ControlBD(this);

        Id_empresa = (EditText) findViewById(R.id.editId_empresa);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editNit = (EditText) findViewById(R.id.editNit);
        editDir_empresa = (EditText) findViewById(R.id.editDir_empresa);
        editTel_empresa = (EditText) findViewById(R.id.editTel_empresa);
        editCantoferta_empresa = (EditText) findViewById(R.id.editCantoferta_empresa);
    }


    public void actualizarEmpresa(View v) {

        Empresa empresa = new Empresa();
        empresa.setId_empresa(Integer.parseInt(Id_empresa.getText().toString()));
        empresa.setNombre_empresa(editNombre.getText().toString());
        empresa.setDir_empresa(editDir_empresa.getText().toString());
        empresa.setTel_empresa(editTel_empresa.getText().toString());
        empresa.setCantoferta_empresa(Integer.parseInt(editCantoferta_empresa.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(empresa);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        Id_empresa.setText("");
        editNombre.setText("");
        editDir_empresa.setText("");
        editTel_empresa.setText("");
        editCantoferta_empresa.setText("");
    }
}
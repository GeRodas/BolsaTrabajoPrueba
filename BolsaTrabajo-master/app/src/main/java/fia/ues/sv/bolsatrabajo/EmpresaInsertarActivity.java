package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EmpresaInsertarActivity extends Activity {

    ControlBD helper;
    EditText Id_empresa;
    EditText editNombre;
    EditText editNit;
    EditText editDir_empresa;
    EditText editTel_empresa;
    EditText editCantoferta_empresa;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_insertar);

        helper = new ControlBD(this);

        Id_empresa = (EditText) findViewById(R.id.editId_empresa);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editNit = (EditText) findViewById(R.id.editNit);
        editDir_empresa = (EditText) findViewById(R.id.editDir_empresa);
        editTel_empresa = (EditText) findViewById(R.id.editTel_empresa);
        editCantoferta_empresa = (EditText) findViewById(R.id.editCantoferta_empresa);
    }


    public void insertarEmpresa(View v) {

        String id_empresa = Id_empresa.getText().toString();
        String nombre_empresa = editNombre.getText().toString();
        String nit_empresa = editNit.getText().toString();
        String dir_empresa = editDir_empresa.getText().toString();
        String tel_empresa = editTel_empresa.getText().toString();
        String cantoferta_empresa = editCantoferta_empresa.getText().toString();
        String regInsertados;

        Empresa empresa = new Empresa();
        empresa.setId_empresa(Integer.parseInt(id_empresa));
        empresa.setNombre_empresa(nombre_empresa);
        empresa.setNit_empresa(nit_empresa);
        empresa.setDir_empresa(dir_empresa);
        empresa.setTel_empresa(tel_empresa);
        empresa.setCantoferta_empresa(Integer.parseInt(cantoferta_empresa));

        helper.abrir();
        regInsertados=helper.insertar(empresa);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v) {
        Id_empresa.setText("");
        editNombre.setText("");
        editNit.setText("");
        editDir_empresa.setText("");
        editTel_empresa.setText("");
        editCantoferta_empresa.setText("");
    }
}
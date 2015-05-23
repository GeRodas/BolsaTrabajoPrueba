package fia.ues.sv.bolsatrabajo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EmpresaEliminarActivity extends Activity {

    EditText editId_empresa;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_eliminar);
        controlhelper=new ControlBD (this);
        editId_empresa = (EditText)findViewById(R.id.editId_empresa);
    }

    public void eliminarEmpresa(View v){

        String regEliminadas;

        Empresa empresa = new Empresa();
        empresa.setId_empresa(Integer.parseInt(editId_empresa.getText().toString()));

        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(empresa);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}

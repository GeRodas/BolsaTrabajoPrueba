package fia.ues.sv.bolsatrabajo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class RS07003Activity extends ListActivity {

    String[] menu={"Empresas","Detalle Estudio"};
    String[] activities={"EmpresaMenuActivity","detEstudioMenuActivity"};

    ControlBD BDhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu));

        BDhelper = new ControlBD(this);
    }


    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){

        super.onListItemClick(l, v, position, id);
        if(position!=2){
            String nombreValue = activities[position];
            try{
                Class<?>
                        clase = Class.forName("fia.ues.sv.bolsatrabajo."+nombreValue);
                Intent inte = new Intent(this,clase);
                this.startActivity(inte);

            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }
    }
}
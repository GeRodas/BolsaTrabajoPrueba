package fia.ues.sv.bolsatrabajo;

/**
 * Created by Eduardo on 15/05/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.sql.Ref;
import java.util.Objects;

public class ControlBD {

    private final Context context;
    private SQLiteDatabase db;
    private DatabaseHelper DBHelper;

    public static String[] camposReferencia={"ID_REFERENCIA","ID_EMPLEADO","ID_EMPRESA","NOMBRE_REFERENCIA","TELEFONO_REFERENCIA"};
    public static String[] camposGradoEspecializacion={"ID_ESPECIALIZACION","ID_INSTITUTOESTUDIO","NOMBRE_ESPECIALIZACION","DURACION_ESPECIALIZACION"};

    private static final String[] camposEmpresa = new String[]{"id_empresa", "nombre_empresa", "nit_empresa", "dir_empresa", "tel_empresa", "cantoferta_empresa"};
    private static final String[] camposdetEstudio = new String[]{"id_detalleest", "id_empleado", "id_especializacion", "id_institutoestudio", "anygraduacion"};



    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String BD_BOLSATRABAJO="bolsatrabajo.s3db";
        private static final int vers = 1;

        //SCRIPT SQLite CREACION BD DIVIDIDO POR INSTRUCCIONES
        private static final String sqlCreateApl="create table APLICACION(ID_APLICACION integer not null, ID_EMPLEADO integer not null, ID_OFERTALABORAL integer not null, ID_EMPRESA integer, FECHA_APLICACION  varchar(30), ESTADO_APLICACION varchar(20), primary key (ID_APLICACION,ID_EMPLEADO,ID_OFERTALABORAL,ID_EMPRESA));";
        private static final String sqlCreateCar="create table CARGO(ID_CARGO integer not null, NOMBRE_CARGO varchar(60) not null, DESCRIPCION_CARGO varchar(140) not null, primary key (ID_CARGO));";
        private static final String sqlCreateDetEst="create table detestudio(id_detalleest INTEGER NOT NULL, id_empleado INTEGER NOT NULL,id_especializacion INTEGER NOT NULL,id_institutoestudio INTEGER, anygraduacion INTEGER, primary key (id_detalleest, id_empleado));";
        private static final String sqlCreateEmpl="create table EMPLEADO(ID_EMPLEADO integer not null, NOMBRE_EMPLEADO varchar(50) not null, DUI_EMPLEADO integer not null, SEXO_EMPLEADO varchar(1) not null, EDAD_EMPLEADO integer not null, DIRECCION_EMPLEADO varchar(100) not null, TELEFONO_EMPLEADO integer not null, CANTAPLICACIONES_EMPLEADO integer, CANTREFERENCIAS_EMPLEADO integer,primary key (ID_EMPLEADO));";
        private static final String sqlCreateEmp= "create table empresa(id_empresa INTEGER NOT NULL  PRIMARY KEY, nombre_empresa VARCHAR(50) NOT NULL, nit_empresa VARCHAR(25) NOT NULL, dir_empresa VARCHAR(50) NOT NULL,tel_empresa VARCHAR(10), cantoferta_empresa INTEGER NOT NULL);";
        private static final String sqlCreateExpLab="create table EXPERIENCIALABORAL(ID_EXPERIENCIALABORAL integer not null, ID_EMPLEADO  integer not null, ID_EMPRESA integer not null, ID_CARGO integer not null, DURACION_EXPERIENCIALABORAL integer not null, primary key (ID_EXPERIENCIALABORAL,ID_EMPLEADO));";
        private static final String sqlCreateGraEsp="create table GRADOESPECIALIZACION(ID_ESPECIALIZACION integer not null, ID_INSTITUTOESTUDIO  integer, NOMBRE_ESPECIALIZACION varchar(50) not null, DURACION_ESPECIALIZACION integer,primary key (ID_ESPECIALIZACION,ID_INSTITUTOESTUDIO));";
        private static final String sqlCreateInsEst="create table INSTITUTOESTUDIO(ID_INSTITUTOESTUDIO integer not null, NOMBRE_INSTITUTOESTUDIO varchar(100) not null, MUNICIPIO_INSTITUTOESTUDIO varchar(30) not null, DEPARTAMENTO_INSTITUTOESTUDIO varchar(30) not null, primary key (ID_INSTITUTOESTUDIO));";
        private static final String sqlCreateOfeLab="create table OFERTALABORAL(ID_OFERTALABORAL integer not null, ID_EMPRESA integer not null, ID_CARGO integer not null, FECHAPUBLICACION_OFERTALABORAL varchar(30) not null, FECHAEXPIRACION_OFERTALABORAL varchar(30) not null, primary key (ID_OFERTALABORAL,ID_EMPRESA));";
        private static final String sqlCreateRef="create table REFERENCIA(ID_REFERENCIA integer not null, ID_EMPLEADO integer, ID_EMPRESA integer, NOMBRE_REFERENCIA varchar(50) not null, TELEFONO_REFERENCIA varchar(10) not null, primary key (ID_REFERENCIA,ID_EMPLEADO));";

        DatabaseHelper(Context context){
            super(context,BD_BOLSATRABAJO,null,vers);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(sqlCreateApl);
                db.execSQL(sqlCreateCar);
                db.execSQL(sqlCreateDetEst);
                db.execSQL(sqlCreateEmpl);
                db.execSQL(sqlCreateEmp);
                db.execSQL(sqlCreateExpLab);
                db.execSQL(sqlCreateGraEsp);
                db.execSQL(sqlCreateInsEst);
                db.execSQL(sqlCreateOfeLab);
                db.execSQL(sqlCreateRef);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Para cuando se quiera actualizar la BD
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }
    public void cerrar(){
        DBHelper.close();
    }
    /*******************************************METODOS DE CRUD PARA CADA TABLA***************************************************************/
    //Aqui cada uno se divierte :)

    /*****************************************************************************************************************************************/

    public String insertar(Referencia referencia){

        String regInsertados="Referencia Insertada N�= " ;
        long cont=0;

        ContentValues refe=new ContentValues();
        refe.put("ID_REFERENCIA",referencia.getId_referencia());
        refe.put("ID_EMPLEADO",referencia.getId_empleado());
        refe.put("ID_EMPRESA",referencia.getId_empresa());
        refe.put("NOMBRE_REFERENCIA",referencia.getNombre_referencia());
        refe.put("TELEFONO_REFERENCIA",referencia.getTelefono_referencia());
        cont=db.insert("REFERENCIA", null, refe);

        if(cont==-1|| cont==0){
            regInsertados="Error al Insertar La referencia, ya existe esa referencia.";

        }
        else {
            regInsertados+=cont;
        }

        return regInsertados;
    }

    public String eliminar(Referencia referencia){

        String regDelete="Referencias Eliminadas= ";
        int cont=0;
        cont=db.delete("REFERENCIA","ID_REFERENCIA='"+referencia.getId_referencia()+"'",null);
        return regDelete+=cont;

        }

    public String modificar(Referencia referencia){
        if(verificarIntegridad(referencia,3)){
            String id[]={String.valueOf(referencia.getId_referencia()),String.valueOf(referencia.getId_empleado())};
            ContentValues refe=new ContentValues();
            refe.put("NOMBRE_REFERENCIA",referencia.getNombre_referencia());
            refe.put("TELEFONO_REFERENCIA",referencia.getTelefono_referencia());
            refe.put("ID_EMPRESA", referencia.getId_empresa());
            db.update("REFERENCIA",refe,"ID_REFERENCIA= ? AND ID_EMPLEADO= ?",id);
            return "Se Actualiz� la Referencia";

        }
        else{
            return "La referencia No existe o no esta asociada a ese empleado";
        }

    }

    public Referencia consultarReferencia(String idReferencia){
        String[] id={idReferencia};
        Cursor cursor=db.query("REFERENCIA",camposReferencia,"ID_REFERENCIA= ?",id,null,null,null);

        if(cursor.moveToFirst()){
            Referencia referencia=new Referencia();
            referencia.setId_referencia(cursor.getInt(0));
            referencia.setId_empleado(cursor.getInt(1));
            referencia.setId_empresa(cursor.getInt(2));
            referencia.setNombre_referencia(cursor.getString(3));
            referencia.setTelefono_referencia(cursor.getString(4));
            return referencia;
        }else {

            return null;
        }
    }


    public String insertar(GradoEspecializacion especializacion){
        String regInsertados="Especializacion Insertada N�= " ;
        long cont=0;

        ContentValues cv=new ContentValues();
        cv.put("ID_ESPECIALIZACION", especializacion.getId_especializacion());
        cv.put("ID_INSTITUTOESTUDIO", especializacion.getId_institutoEstudio());
        cv.put("NOMBRE_ESPECIALIZACION", especializacion.getNombre_especializacion());
        cv.put("DURACION_ESPECIALIZACION", especializacion.getDuracion_especializacion());

        cont=db.insert("GRADOESPECIALIZACION", null, cv);

        if(cont==-1|| cont==0){
            regInsertados="Error al Insertar La Especializacion, ya existe esa Especializacion.";

        }
        else {
            regInsertados+=cont;
        }

        return regInsertados;

    }

    public String eliminar(GradoEspecializacion especializacion){

        String regDelete="Filas Eliminadas= ";
        int cont=0;
        cont=db.delete("GRADOESPECIALIZACION","ID_ESPECIALIZACION='"+especializacion.getId_especializacion()+"'",null);
        return regDelete+=cont;

    }

    public String modificar(GradoEspecializacion especializacion){
        if(/*verificarIntegridad(especializacion,4)*/true){         //Ahorita esto esta de prueba para poder correrlo
            String id[]={String.valueOf(especializacion.getId_especializacion())};
            ContentValues refe=new ContentValues();
            refe.put("ID_INSTITUTOESTUDIO", especializacion.getId_institutoEstudio());
            refe.put("NOMBRE_ESPECIALIZACION", especializacion.getNombre_especializacion());
            refe.put("DURACION_ESPECIALIZACION", especializacion.getDuracion_especializacion());
            db.update("GRADOESPECIALIZACION",refe,"ID_ESPECIALIZACION= ? ",id);
            return "Se Actualiz� la Especializacion ";

        }
        else{
            return "La Especializacion No existe o no esta asociada a ese Insitituo";
        }
    }

    public GradoEspecializacion consultarGradoEspecializacion(String idEspec){

        String[] id={idEspec};
        Cursor cursor=db.query("GRADOESPECIALIZACION",camposGradoEspecializacion,"ID_ESPECIALIZACION= ? ",id,null,null,null);

        if(cursor.moveToFirst()){
            GradoEspecializacion espec=new GradoEspecializacion();
            espec.setId_especializacion(cursor.getInt(0));
            espec.setId_institutoEstudio(cursor.getInt(1));
            espec.setNombre_especializacion(cursor.getString(2));
            espec.setDuracion_especializacion(cursor.getInt(3));

            return espec;
        }else {

            return null;
        }
    }


    // ---- RS07003 ------
    public String insertar(Empresa empresa) {
        String regInsertados = "Registro ingresados Nº= ";
        long contador = 0;

        ContentValues emp = new ContentValues();
        emp.put("id_empresa", empresa.getId_empresa());
        emp.put("nombre_empresa", empresa.getNombre_empresa());
        emp.put("nit_empresa", empresa.getNit_empresa());
        emp.put("dir_empresa", empresa.getDir_empresa());
        emp.put("tel_empresa", empresa.getTel_empresa());
        emp.put("cantoferta_empresa", empresa.getCantoferta_empresa());
        contador = db.insert("empresa", null, emp);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }



    public String insertar(detEstudio detestudio) {
        String regInsertados = "Registro ingresados Nº= ";
        long contador = 0;
        ContentValues det = new ContentValues();
        det.put("id_detalleest", detestudio.getId_detalleest());
        det.put("id_empleado", detestudio.getId_empleado());
        det.put("id_especializacion", detestudio.getId_especializacion());
        det.put("id_institutoestudio", detestudio.getId_institutoestudio());
        det.put("anygraduacion", detestudio.getAnygraduacion());
        contador = db.insert("detestudio", null, det);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }



    public String actualizar(Empresa empresa) {
        String[] id = {String.valueOf(empresa.getId_empresa())};
        abrir();
        Cursor c2 = db.query("empresa", null, "id_empresa = ?", id, null, null, null);
        if(c2.moveToFirst()){
            ContentValues cv = new ContentValues();
            cv.put("nombre_empresa", empresa.getNombre_empresa());
            cv.put("dir_empresa", empresa.getDir_empresa());
            cv.put("tel_empresa", empresa.getTel_empresa());
            cv.put("cantoferta_empresa", empresa.getCantoferta_empresa());
            db.update("empresa", cv, "id_empresa = ?", id);
            return "Registro actualizo exitosamente!";
        }
        return "Registro con ID: " + empresa.getId_empresa() + ", no existe";
    }



    public String actualizar(detEstudio detestudio) {
       String[] id = {String.valueOf(detestudio.getId_detalleest())};
        abrir();
        Cursor c2 = db.query("detestudio", null, "id_detalleest = ?", id, null, null, null);
        if(c2.moveToFirst()){
            ContentValues cv = new ContentValues();
            cv.put("anygraduacion", detestudio.getAnygraduacion());
            db.update("detestudio", cv, "id_detalleest = ?", id);
            return "Registro actualizo exitosamente!";
        }
        return "Registro con ID: " + detestudio.getId_detalleest() + ", no existe";
    }



    public String eliminar(Empresa empresa) {

        String regAfectados="Registros afectados = ";
        int contador=0;
        contador+=db.delete("empresa", "id_empresa ='" + empresa.getId_empresa() + "'", null);
        regAfectados+=contador;
        return regAfectados;
    }




    public String eliminar(detEstudio detestudio) {
        String regAfectados="Registros afectados = ";
        int contador=0;
        contador+=db.delete("detestudio", "id_detalleest ='" + detestudio.getId_detalleest() + "'", null);
        regAfectados+=contador;
        return regAfectados;
    }



    public Empresa consultarEmpresa(int id_empresa) {
        String[] id = {String.valueOf(id_empresa)};
        Cursor cursor = db.query("empresa", camposEmpresa, "id_empresa = ?", id, null, null, null);

        if(cursor.moveToFirst()){
            Empresa empresa = new Empresa();
            empresa.setId_empresa(cursor.getInt(0));
            empresa.setNombre_empresa(cursor.getString(1));
            empresa.setNit_empresa(cursor.getString(2));
            empresa.setDir_empresa(cursor.getString(3));
            empresa.setTel_empresa(cursor.getString(4));
            empresa.setCantoferta_empresa(cursor.getInt(5));

            return empresa;
        }else {
            return null;
        }
    }
    //  --- RS07003 ---



    public detEstudio consultardetEstudio(int id_detalleest) {

        String[] id = {String.valueOf(id_detalleest)};
        Cursor cursor = db.query("detestudio", camposdetEstudio, "id_detalleest = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            detEstudio detestudio = new detEstudio();
            detestudio.setId_detalleest(cursor.getInt(0));
            detestudio.setId_empleado(cursor.getInt(1));
            detestudio.setId_especializacion(cursor.getInt(2));
            detestudio.setId_institutoestudio(cursor.getInt(3));
            detestudio.setAnygraduacion(cursor.getInt(4));
            return detestudio;
        }else {
            return null;
        }
    }






    public boolean verificarIntegridad(Object dato,int relacion) {


        switch (relacion) {
            case 1: {
                //Verificar que al insertar una relacion, exista el empleado y la empresa de referencia y ademas que no exista esa referencia
                Referencia referencia=(Referencia)dato;
                String id[]={String.valueOf(referencia.getId_empleado())};
                String id2[]={String.valueOf(referencia.getId_empresa())};

                Cursor cursor1=db.query("EMPLEADO",null,"ID_EMPLEADO=?",id,null,null,null);
                Cursor cursor2=db.query("EMPRESA",null,"ID_EMPRESA=?",id2,null,null,null);

                if(cursor1.moveToFirst()&cursor2.moveToFirst()){ //aqui solo unse un "&"
                    return true;

                }

                return false;
            }

            case 2:{
                //verificar que al ingresar una especializacion exista el instituto de estudio
                GradoEspecializacion especializacion=(GradoEspecializacion)dato;
                String id[]={String.valueOf(especializacion.getId_institutoEstudio())};


                Cursor cursor1=db.query("INSTITUTOESTUDIO",null,"ID_INSTITUTOESTUDIO=?",id,null,null,null);


                if(cursor1.moveToFirst()){ //aqui solo unse un "&"
                    return true;

                }

                return false;
            }
            case 3:
            {
                //verificar que al actualizar la Referencia exista y el empleado este asociado a esa referencia
                Referencia referencia=(Referencia)dato;
                String id[]={String.valueOf(referencia.getId_empleado())};
                String id2[]={String.valueOf(referencia.getId_referencia())};

                Cursor cursor1=db.query("REFERENCIA",null,"ID_EMPLEADO=?",id,null,null,null);
                Cursor cursor2=db.query("REFERENCIA",null,"ID_REFERENCIA=?",id2,null,null,null);

                if(cursor1.moveToFirst()&cursor2.moveToFirst()){ //aqui solo unse un "&"
                    return true;

                }

                return false;


            }
            case 4:
            {
                //verificar que al actualizar un gradoEspecializacion exista el instituto de estudio
                GradoEspecializacion especializacion=(GradoEspecializacion)dato;
                String id[]={String.valueOf(especializacion.getId_institutoEstudio())};


                Cursor cursor1=db.query("INSTITUTOESTUDIO",null,"ID_INSTITUTOESTUDIO=?",id,null,null,null);


                if(cursor1.moveToFirst()){ //aqui solo unse un "&"
                    return true;

                }

                return false;
            }

            default: {
                return false;
            }


        }

        }
    }


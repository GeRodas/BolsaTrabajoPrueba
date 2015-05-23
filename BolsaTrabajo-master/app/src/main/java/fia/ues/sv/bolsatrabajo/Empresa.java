package fia.ues.sv.bolsatrabajo;

/**
 * Created by EdRodas
 */
public class Empresa {

    private int id_empresa;
    private String nombre_empresa;
    private String nit_empresa;
    private String dir_empresa;
    private String tel_empresa;
    private int cantoferta_empresa;


    public Empresa(){
    }

    public Empresa(int id_empresa, String nombre_empresa, String nit_empresa, String dir_empresa, String tel_empresa, int cantoferta_empresa) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.nit_empresa = nit_empresa;
        this.dir_empresa = dir_empresa;
        this.tel_empresa = tel_empresa;
        this.cantoferta_empresa = cantoferta_empresa;
    }


    public int getId_empresa() {
        return id_empresa;
    }
    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }
    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getNit_empresa() {
        return nit_empresa;
    }
    public void setNit_empresa(String nit_empresa) {
        this.nit_empresa = nit_empresa;
    }

    public String getDir_empresa() {
        return dir_empresa;
    }
    public void setDir_empresa(String dir_empresa) {
        this.dir_empresa = dir_empresa;
    }

    public String getTel_empresa() {
        return tel_empresa;
    }
    public void setTel_empresa(String tel_empresa) {
        this.tel_empresa = tel_empresa;
    }

    public int getCantoferta_empresa() {
        return cantoferta_empresa;
    }
    public void setCantoferta_empresa(int cantoferta_empresa) {this.cantoferta_empresa = cantoferta_empresa;}
}


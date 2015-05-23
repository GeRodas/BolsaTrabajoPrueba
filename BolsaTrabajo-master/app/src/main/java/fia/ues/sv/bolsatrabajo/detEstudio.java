package fia.ues.sv.bolsatrabajo;

/**
 * Created by EdRodas
 */
public class detEstudio {

    private int id_detalleest;
    private int id_empleado;
    private int id_especializacion;
    private int id_institutoestudio;
    private int anygraduacion;


    public detEstudio(){
    }


    public detEstudio(int id_detalleest, int id_empleado, int id_especializacion, int id_institutoestudio, int anygraduacion) {
        this.id_detalleest = id_detalleest;
        this.id_empleado = id_empleado;
        this.id_especializacion = id_especializacion;
        this.id_institutoestudio = id_institutoestudio;
        this.anygraduacion = anygraduacion;
    }


    public int getId_detalleest() {
        return id_detalleest;
    }
    public void setId_detalleest(int id_detalleest) {
        this.id_detalleest = id_detalleest;
    }

    public int getId_empleado() {return id_empleado;}
    public void setId_empleado(int id_empleado) {this.id_empleado = id_empleado;}

    public int getId_especializacion() {return id_especializacion;}
    public void setId_especializacion(int id_especializacion){this.id_especializacion = id_especializacion;}

    public int getId_institutoestudio() {return id_institutoestudio;}
    public void setId_institutoestudio(int id_institutoestudio) {this.id_institutoestudio = id_institutoestudio;}

    public int getAnygraduacion() {return anygraduacion;}
    public void setAnygraduacion(int anygraduacion) {this.anygraduacion = anygraduacion;}

}



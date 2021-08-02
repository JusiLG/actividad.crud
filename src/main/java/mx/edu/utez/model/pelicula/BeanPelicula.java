package mx.edu.utez.model.pelicula;

public class BeanPelicula {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha_estreno;
    private int recaudacion;
    private int estado;

    public BeanPelicula(int id, String nombre, String descripcion, String fecha_estreno, int recaudacion, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_estreno = fecha_estreno;
        this.recaudacion = recaudacion;
        this.estado = estado;
    }

    public BeanPelicula() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_estreno() {
        return fecha_estreno;
    }

    public void setFecha_estreno(String fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }

    public int getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

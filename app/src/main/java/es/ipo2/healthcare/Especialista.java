package es.ipo2.healthcare;

/**
 * Created by Diego on 3/6/17.
 */

public class Especialista {

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
    private String especialidad;
    private String sexo;
    private String consulta;
    private String edificio;
    private boolean operar;

    public Especialista(String nombre, String apellidos, String dni, String email, String telefono,
                        String especialidad, String sexo, String consulta, String edificio, boolean operar) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.sexo = sexo;
        this.consulta = consulta;
        this.edificio = edificio;
        this.operar = operar;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public boolean isOperar() {
        return operar;
    }

    public void setOperar(boolean operar) {
        this.operar = operar;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}

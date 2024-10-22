package py.una.pol.sd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {

  @Id
  private Long cedula;
  private String nombre;
  private String apellido;

  protected Persona() {}

  public Persona(Long cedula, String nombre, String apellido) {
    this.cedula = cedula;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  @Override
  public String toString() {
    return String.format(
        "Persona[cedula=%d, nombre='%s', apellido='%s']",
        cedula, nombre, apellido);
  }

public Long getCedula() {
    return cedula;
}

public String getNombre() {
    return nombre;
}

public String getApellido() {
    return apellido;
}

public void setCedula(Long cedula) {
    this.cedula = cedula;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

 
  
}

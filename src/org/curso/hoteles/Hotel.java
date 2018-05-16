package org.curso.hoteles;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hotel {
	
    private int id;

    private String nombre;

    private Double precio;

    private String ubicacion;

    private String pais;
    
    private int estrellas;
    
    private String descripcion;

    private String imagen;

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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", ubicacion=" + ubicacion + ", pais="
				+ pais + ", estrellas=" + estrellas + ", descripcion=" + descripcion + ", imagen=" + imagen + "]";
	}
	
}
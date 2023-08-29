package Integrador1;

public class FacturaProducto {
	private int idFactura;
	private int idProducto;
	private int cantidad;
	
	public FacturaProducto(int idf, int idp, int cant) {
		this.idFactura= idf;
		this.idProducto= idp;
		this.cantidad= cant;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}
	

}

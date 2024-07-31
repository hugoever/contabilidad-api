package py.edu.ucsa.contabilidad.api.web.dto;

public class ErrorDTO {
	private String mensajeError;
	
	public ErrorDTO(String mensajeError) {
		this.mensajeError=mensajeError;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}
}

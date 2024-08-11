package py.edu.ucsa.contabilidad.api.web.dto;

public class ErrorDto {
	private String mensajeError;
	
	public ErrorDto(String mensajeError) {
		this.mensajeError=mensajeError;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}
}

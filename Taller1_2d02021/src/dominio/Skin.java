package dominio;

public class Skin {
	private String nombreSkin;
	private String calidadSkin;
	
	public Skin(String nombreSkin,String calidadSkin) {
		this.nombreSkin = nombreSkin;
		this.calidadSkin = calidadSkin;
	}

	public String getNombreSkin() {
		return nombreSkin;
	}

	public void setNombreSkin(String nombreSkin) {
		this.nombreSkin = nombreSkin;
	}

	public String getCalidadSkin() {
		return calidadSkin;
	}

	public void setCalidadSkin(String calidadSkin) {
		this.calidadSkin = calidadSkin;
	}
	
	

}

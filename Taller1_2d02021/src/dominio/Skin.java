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
	
	public int tipoSkin(String calidadSkin) {
		if(calidadSkin.equalsIgnoreCase("M")) {
			return 3250;
		}
		if(calidadSkin.equalsIgnoreCase("D")){
			return 2750;
		}
		if(calidadSkin.equalsIgnoreCase("L")) {
			return 1820;
		}
		if(calidadSkin.equalsIgnoreCase("E")) {
			return 1350;
		}
		if(calidadSkin.equalsIgnoreCase("N")) {
			return 975;
		}
		else {
			return 0;
		}
	}
	
	

}

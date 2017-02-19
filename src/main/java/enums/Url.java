package enums;

public enum Url {

	BASE_URL("https://www.brighttalk.com/");
	
	String url;
	
	Url(String URL){
		this.url = URL;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setURL(String URL){
		this.url = URL;
	}
}

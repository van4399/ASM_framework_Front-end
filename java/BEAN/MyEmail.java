package BEAN;

public class MyEmail {
	private String user="kieuanhvan439999@gmail.com";
	private String password="uzvfdzhhcszshnel";
	private String code;

	
	public MyEmail(String code) {

		this.code = code;
	}

	public final String getUser() {
		return user;
	}

	public final String getPassword() {
		return password;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getTagHtmlCode() {
		return "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'>\r\n"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Document</title>\r\n"
				+ "<style>body{margin: 0;padding:0;}header{text-align: center; color:red;background-color: rgb(67, 239, 251);border-radius: 10%;\r\n"
				+ "            overflow: hidden;}header>h1{padding:80px; }</style>\r\n"
				+ "</head><body><header><h1>Hello Bro</h1></header><article><h2 style='text-align: center;'>Đây là mã xác nhận của bạn</h2>\r\n"
				+ "<p style='text-align: center;'>"
				+ this.code
				+ "</p></article><br><br>\r\n"
				+ "    <footer><h2 style='text-align: center;'>Thank's you</h2><div style='width: 100%;text-align: center ;'><img style='width: 20%;' src='https://genzrelax.com/wp-content/uploads/2022/03/anh-gai-xinh-deo-mat-kinh-1.jpg' alt=''>\r\n"
				+ "</div></footer></body></html>";
	}


	
}

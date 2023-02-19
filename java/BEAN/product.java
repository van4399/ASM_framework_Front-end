package BEAN;

public class product {
	private int id;
	private String nameString;
	private float price;
	private int rate;
	private boolean status;
	private String desc;
	private String images;
	private String video;
	private int amount;
	private String imageMain ;
	private int category;
	
	public product() {
		
	}
	public product(int id, String nameString, float price, int rate, boolean status, String desc, String images,
			String video) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.price = price;
		this.rate = rate;
		this.status = status;
		this.desc = desc;
		this.images = images;
		this.video = video;
		setImageMain(images);
	}
	
	public product(int id, String nameString, float price, int rate, boolean status, String desc, String images,
			String video, int amount) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.price = price;
		this.rate = rate;
		this.status = status;
		this.desc = desc;
		this.images = images;
		this.video = video;
		this.amount = amount;
		setImageMain(images);
	}
	
	public product(int id, String nameString, float price, int rate, boolean status, String desc, String images,
			String video, int amount , int category) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.price = price;
		this.rate = rate;
		this.status = status;
		this.desc = desc;
		this.images = images;
		this.video = video;
		this.amount = amount;
		this.category = category;
		setImageMain(images);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {

		this.images = images;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "product [id=" + id + ", nameString=" + nameString + ", price=" + price + ", rate=" + rate + ", status="
				+ status + ", desc=" + desc + ", images=" + images + ", video=" + video + ", amount=" + amount + "]";
	}
	public String getImageMain() {
		
		return imageMain;
	}
	public void setImageMain(String imageName) {
		this.imageMain = 	imageName.split(",").clone()[0];
	}
	
	
}

import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/tv")


/* 
	Console class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	Console class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class TV extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private String mrebate;
	private String warranty;
	public TV(String id, String name, double price, String image, String retailer, String condition, double discount,
			String mrebate, String warranty, HashMap<String, String> accessories) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.retailer = retailer;
		this.condition = condition;
		this.discount = discount;
		this.mrebate = mrebate;
		this.warranty = warranty;
		this.accessories = accessories;
	}

	HashMap<String,String> accessories;
	
    public String getMrebate() {
		return mrebate;
	}

	public void setMrebate(String mrebate) {
		this.mrebate = mrebate;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	HashMap<String,String> getAccessories() {
		return accessories;
		}

	public TV(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

@WebServlet("/Phone")

/* 
	Phone class contains class variables name,price,image,retailer,condition,discount.

	Phone class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Phone class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class Phone extends HttpServlet {
    private String id;
    private String name;
    private double price;
    private String image;
    private String retailer;
    private String condition;
    private double discount;
    HashMap<String,String> accessories;
    private String mrebate;
	private String warranty;


    public Phone(String id, String name, double price, String image, String retailer, String condition, double discount,
			HashMap<String, String> accessories, String mrebate, String warranty) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.retailer = retailer;
		this.condition = condition;
		this.discount = discount;
		this.accessories = accessories;
		this.mrebate = mrebate;
		this.warranty = warranty;
	}

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

    public void setAccessories( HashMap<String,String> accessories) {
        this.accessories = accessories;
    }

    public Phone() {

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

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", retailer='" + retailer + '\'' +
                ", condition='" + condition + '\'' +
                ", discount=" + discount +
                ", accessories=" + accessories +
                '}';
    }
}

import java.io.IOException;
import java.io.*;

/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable {
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerpin;
	private String price;
	private String retailercity;
	private String retailername;
	private String retailerstate;
	

	private String productonsale;
	private String manufacturerrebate;
	private String userage, usergender;
	private String useroccupation;

	/*public Review(String productName, String userName, String productType, String productMaker, String reviewRating,
			String reviewDate, String reviewText, String retailerpin, String price, String retailercity) {
		this.productName = productName;
		this.userName = userName;
		this.productType = productType;
		this.productMaker = productMaker;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		this.retailerpin = retailerpin;
		this.price = price;
		this.retailercity = retailercity;
	}*/

	public Review(String productName, String userName, String productType, String productMaker, String reviewRating,
			String reviewDate, String reviewText, String retailerpin, String price, String retailercity,
			String retailername, String retailerstate, String productonsale, String manufacturerrebate,
			 String userage, String usergender, String useroccupation) {
		super();
		this.productName = productName;
		this.userName = userName;
		this.productType = productType;
		this.productMaker = productMaker;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		this.retailerpin = retailerpin;
		this.price = price;
		this.retailercity = retailercity;
		this.retailername = retailername;
		this.retailerstate = retailerstate;
		this.productonsale = productonsale;
		this.manufacturerrebate = manufacturerrebate;
	
		this.userage = userage;
		this.usergender = usergender;
		this.useroccupation = useroccupation;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
		this.productName = productName;
		this.retailerpin = retailerpin;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
	}

	public String getProductName() {
		return productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}

	public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	public String getRetailerpin() {
		return retailerpin;
	}

	public void setRetailerpin(String retailerpin) {
		this.retailerpin = retailerpin;
	}

	public String getRetailercity() {
		return retailercity;
	}

	public void setRetailercity(String retailercity) {
		this.retailercity = retailercity;
	}

	public String getRetailername() {
		return retailername;
	}

	public void setRetailername(String retailername) {
		this.retailername = retailername;
	}

	public String getRetailerstate() {
		return retailerstate;
	}

	public void setRetailerstate(String retailerstate) {
		this.retailerstate = retailerstate;
	}

	public String getProductonsale() {
		return productonsale;
	}

	public void setProductonsale(String productonsale) {
		this.productonsale = productonsale;
	}

	public String getManufacturerrebate() {
		return manufacturerrebate;
	}

	public void setManufacturerrebate(String manufacturerrebate) {
		this.manufacturerrebate = manufacturerrebate;
	}

	public String getUserage() {
		return userage;
	}

	public void setUserage(String userage) {
		this.userage = userage;
	}

	public String getUsergender() {
		return usergender;
	}

	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getUseroccupation() {
		return useroccupation;
	}

	public void setUseroccupation(String useroccupation) {
		this.useroccupation = useroccupation;
	}
}

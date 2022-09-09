
public class Product {
	
	private String nameProduct;
	private String priceProduct;
	
	public Product(String nameProduct, String priceProduct) {
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		
		
	}	
		
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getPriceProduct() {
		return priceProduct;
	}
	public void setPriceProduct(String priceProduct) {
		this.priceProduct = priceProduct;
	}
	@Override
	public String toString() {
		return nameProduct+"		"+priceProduct;
	}
}

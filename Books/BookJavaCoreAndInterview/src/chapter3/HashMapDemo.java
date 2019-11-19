import java.util.HashMap;

class Book
{
	private String ISBN;
	private String name;
	private float price;
	
	public Book(String ISBN,String name, float price)
	{
		this.ISBN = ISBN;
		this.name = name;
		this.price = price;
	}
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}


public class HashMapDemo {

	public static void main(String[] args) {
		HashMap hm = new HashMap();
		Book javaBook = new Book("123","Java",50.5f);
		Book dbBook = new Book("456","DB",90f);
		
		hm.put(javaBook.getISBN(),javaBook);
		hm.put(dbBook.getISBN(),dbBook);
		
		Book book = null;
		if(hm.containsKey("123"))
		{
			book = (Book)hm.get("123");
			System.out.println(book.getName() + "," + book.getPrice());
		}

	}

}

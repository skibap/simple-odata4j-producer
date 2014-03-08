package de.example.odata4j.simple;

public class Product {
	private String key; 
	private String description; 
	
	public Product(String key){
		this.key = key;
	}
	
	public Product(String key, String description){
		this.key = key;
		this.description = description;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

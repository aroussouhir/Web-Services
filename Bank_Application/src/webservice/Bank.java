package webservice;

public class Bank {

	private int id;
	String name;
	
	
	
	public Bank() {
		super();
	}

	


	public Bank(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}




	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

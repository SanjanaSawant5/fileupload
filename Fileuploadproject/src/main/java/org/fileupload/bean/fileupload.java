package org.fileupload.bean;

public class fileupload {
	
    private int id;
    private String firstname;
    private String lastname;
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public fileupload(int id, String photo) {
		 
		this.id = id;
		this.photo = photo;
	}

	public fileupload(String photo) {
		super();
		this.photo = photo;
	}

	public fileupload() {
		super();
		 
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	 
	
}

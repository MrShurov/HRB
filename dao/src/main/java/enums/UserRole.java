package enums;

public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	String role;
	
	UserRole(String role){
		this.role = role;
	}
	
	public String getRole(){
		return role;
	}


	@Override
	public String toString(){
		return this.role;
	}

	public String getName(){
		return this.name();
	}
}

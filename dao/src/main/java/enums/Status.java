package enums;

public enum Status {
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	LOCKED("Locked");

	private String status;

	private Status(final String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public String toString(){
		return this.status;
	}

	public String getName(){
		return this.name();
	}
}

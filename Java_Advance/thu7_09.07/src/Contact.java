public class Contact {
	static int id = 0;

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Contact.id = id;
	}

	String name, firstName, lastName, group, address, phone;
	int thisId;

	public Contact() {
		id++;
		thisId = id;
	}

	public Contact(String name, String group, String address, String phone) {
		this();
		this.name = name;
		this.group = group;
		this.address = address;
		this.phone = phone;
	}

	public Contact(String name, String firstName, String lastName, String group, String address, String phone) {
		this();
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.group = group;
		this.address = address;
		this.phone = phone;
	}

	public void display() {
		System.out.println(thisId + "\t" + name + "\t" + firstName + "\t" + lastName + "\t" + group + "\t" + address
				+ "\t" + phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	public String getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGroup() {
		return group;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public int getThisId() {
		return thisId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setThisId(int thisId) {
		this.thisId = thisId;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", group=" + group
				+ ", address=" + address + ", phone=" + phone + "]";
	}

}

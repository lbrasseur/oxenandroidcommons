package ar.com.oxen.android.sample.mvp;

public class Person {
	private String name = "Pepe";
	private String email = "pepe@sanchez.org";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + "]";
	}
}

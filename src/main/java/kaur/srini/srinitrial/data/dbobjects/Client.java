package kaur.srini.srinitrial.data.dbobjects;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Client {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	@NotBlank(message = "Last name is mandatory")
	private String lastName;
	@NotBlank(message = "Username is mandatory")
	private String username;
	@Email(message = "E-mail is not valid")
	@Size(min=5, message="E-mail is not valid")
	private String email;
	@NotBlank(message = "Address is mandatory")
	private String address;
	@ManyToOne
	@JoinColumn(name="srini_user_id", nullable=false)
	private SriniUser sriniUser;
	@ManyToOne
	@JoinColumn(name="country_code")
	@NotNull(message = "Country is mandatory")
	private Country country;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SriniUser getSriniUser() {
		return sriniUser;
	}
	public void setSriniUser(SriniUser sriniUser) {
		this.sriniUser = sriniUser;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, email, firstName, id, lastName, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(username, other.username);
	}
	
}

package student.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class City {
	private int idCity;
	private String name;
	private String postalCode;
	private Collection<District> districtsByIdCity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCity", nullable = false)
	public int getIdCity() {
		return idCity;
	}
	
	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	
	@Basic
	@Column(name = "Name", nullable = false, length = 100, unique = true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name = "PostalCode", nullable = false, length = 100, unique = true)
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public int hashCode() {
		int result = idCity;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		City city = (City) o;
		
		if (idCity != city.idCity) return false;
		if (name != null ? !name.equals(city.name) : city.name != null) return false;
		if (postalCode != null ? !postalCode.equals(city.postalCode) : city.postalCode != null) return false;
		
		return true;
	}
	
	@OneToMany(mappedBy = "cityByIdCity")
	public Collection<District> getDistrictsByIdCity() {
		return districtsByIdCity;
	}
	
	public void setDistrictsByIdCity(Collection<District> districtsByIdCity) {
		this.districtsByIdCity = districtsByIdCity;
	}
}

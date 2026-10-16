package com.tech.gateway.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_profile")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_profile_id")
	//@Column(name = "usr_profile_id", columnDefinition = "UUID")
	Long userProfileId;

	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "account_id", nullable = false)
	//private Account account;
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email_address", nullable = false)
	private String email;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "dob")
	private String dob;

	private String address;
	private String city;
	private String state;
	private String country;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "profile_image")
	private String profileImage;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	

	@Override
	public int hashCode() {
		return Objects.hash(userId, address, city, country, createdAt, dob, email, firstName, lastName, mobileNumber,
				profileImage, state, updatedAt, userProfileId, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(address, other.address)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(dob, other.dob)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(profileImage, other.profileImage) && Objects.equals(state, other.state)
				&& Objects.equals(updatedAt, other.updatedAt) && Objects.equals(userProfileId, other.userProfileId)
				&& Objects.equals(zipCode, other.zipCode);
	}

}
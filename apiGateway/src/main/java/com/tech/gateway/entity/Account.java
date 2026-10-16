package com.tech.gateway.entity;


import com.tech.gateway.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "account")
@Setter
@Getter
public class Account {
	@Id
	//@GeneratedValue(strategy = GenerationType.UUID)
	//@JdbcTypeCode(SqlTypes.BINARY)
	@UuidGenerator
	@Column(name = "account_id")
	private UUID accountId;

	@Column(name = "email_address", nullable = false, length = 255)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private AccountStatus status;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "accept_term_condition")
	private String acceptTermCondition;

	@UpdateTimestamp
	@Column(name = "accept_term_condition_date")
	private LocalDateTime acceptTermConditionDate;

	

	@Override
	public int hashCode() {
		return Objects.hash(acceptTermCondition, acceptTermConditionDate, accountId, createdAt, email, status,
				updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(acceptTermCondition, other.acceptTermCondition)
				&& Objects.equals(acceptTermConditionDate, other.acceptTermConditionDate)
				&& Objects.equals(accountId, other.accountId) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && status == other.status
				&& Objects.equals(updatedAt, other.updatedAt);
	}

}

package com.sensa.usermanagementservice.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity(name = "emergency_contacts")
@Getter
@Setter
@RequiredArgsConstructor
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @Column(name = "second_name", nullable = false)
    @NotBlank(message = "Second name cannot be blank")
    @Size(max = 50, message = "Second name cannot exceed 50 characters")
    private String secondName;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number format")
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(name = "relationship", nullable = false)
    @NotBlank(message = "Relationship cannot be blank")
    private String relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EmergencyContact that = (EmergencyContact) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @PrePersist
    @PreUpdate
    private void normalizeData() {
        this.name = name != null ? name.trim() : null;
        this.secondName = secondName != null ? secondName.trim() : null;
        this.email = email != null ? email.toLowerCase() : null;
    }
}

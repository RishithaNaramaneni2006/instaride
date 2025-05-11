package com.instaride.entity;

import com.instaride.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phoneNumber")
})
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role; // RIDER or DRIVER

    // For simplicity, managing saved places can be a JSON string or a separate
    // entity
    @Lob // For potentially long strings
    private String savedPlaces; // Example: Store as JSON: {"home": "address1", "work": "address2"}

    public User(String name, String email, String phoneNumber, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
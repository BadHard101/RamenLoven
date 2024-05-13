package com.example.rschir_buysell.models;

import com.example.rschir_buysell.models.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@Data
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotEmpty(message = "Почта не должна быть пустой")
    @Email(message = "Не корректный email")
    private String email;

    @Column
    @NotEmpty(message = "Телефон не должен быть пустым")
    @Pattern(regexp = "(^$|[0-9]{11})",
            message = "Некорректный номер телефона")
    private String phoneNumber;

    @Column
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    private String name;

    @Column
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;

    @Column
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            // regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            // минимум:
            // одну цифру,
            // одну строчную и одну прописную букву,
            // один специальный символ (@, #, $, %, ^, &, +, =, !)
            // быть не менее 8 символов
            message = "Пароль должен содержать минимум одну цифру, одну строчную и одну прописную букву, специальный символ и быть не менее 8 символов")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    // security

    public boolean hasAvatar() {
        return avatar != null;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    public boolean isEmployee() {
        return roles.contains(Role.ROLE_EMPLOYEE);
    }

    public boolean isDeliveryman() {
        return roles.contains(Role.ROLE_DELIVERYMAN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

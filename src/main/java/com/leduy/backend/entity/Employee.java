package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.constant.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends PrimaryEntity {

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "full_name", length = EntityProperties.LENGTH_NAME)
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "pass_word", length = EntityProperties.LENGTH_DESCRIPTION)
    private String passWord;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//    @Override
//    public String getPassword() {
//        return passWord;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}

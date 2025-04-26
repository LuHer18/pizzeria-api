package com.luher.luher_pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String userName;
    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "grantend_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime gratendDate;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

}

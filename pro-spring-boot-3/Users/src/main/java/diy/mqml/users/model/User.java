package diy.mqml.users.model;

import diy.mqml.users.model.converter.UserRoleConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Arrays;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="PEOPLE")
public class User {


    @Id
    @NotBlank(message = "Email can not be empty")
    @NotNull(message = "Email can not be null")
    private String email;

    @NotBlank(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    private String name;

    private String gravatarUrl;

    @NotBlank(message = "Password can not be empty")
    @NotNull(message = "Password can not be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Singular("role")
    @Convert(converter = UserRoleConverter.class)
    private List<UserRole> userRole;

    private boolean active;

    public void setUserRole(UserRole... userRoles){
        this.userRole = Arrays.asList(userRoles);
    }

    @PrePersist
    void setGravatarUrlFromEmail(){
        this.gravatarUrl = UserGravatar.getGravatarUrlFromEmail(this.email);
    }

    @PreUpdate
    void updateGravatarUrlFromEmail(){
        this.gravatarUrl = UserGravatar.getGravatarUrlFromEmail(this.email);
    }
}

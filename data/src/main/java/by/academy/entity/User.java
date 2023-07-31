package by.academy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String lastname;

    @Column(length = 20)
    private String firstname;

    @Column(length = 40, name = "middle_name")
    private String middleName;

    @Column(length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}

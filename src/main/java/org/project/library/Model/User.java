package org.project.library.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User {

    @Id
    private Integer userId ;

    @Column(nullable = false)
    private String userName ;

    @Column(nullable = true)
    private String userEmail ;

    @Column(nullable = false)
    private Integer userMobile ;
}

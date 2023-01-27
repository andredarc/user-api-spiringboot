package com.demo.userapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "publicPlace")
    private String publicPlace;

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "number")
    private String number;

    @Column(name = "city")
    private String city;

    @Column(name = "isPrincipal")
    private Boolean isPrincipal = false;
}

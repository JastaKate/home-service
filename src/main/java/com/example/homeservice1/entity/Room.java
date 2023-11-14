package com.example.homeservice1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "home_id", insertable = false, updatable = false)
    private long homeId;

    @ManyToOne
    @JoinColumn(name = "home_id", referencedColumnName = "id")
    @JsonBackReference
    private Home home;
}

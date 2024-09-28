package com.asset.management.AssetManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String serialNumber;
    private String condition;
    private boolean isAssigned;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Employee assignedTo;
}

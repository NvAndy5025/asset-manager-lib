package com.asset.management.AssetManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetReassignmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "current_employee_id")
    private Employee currentEmployee;

    @ManyToOne
    @JoinColumn(name = "new_employee_id")
    private Employee newEmployee;

    private LocalDateTime requestDate;
    private String status; // e.g., Pending, Approved, Rejected
}

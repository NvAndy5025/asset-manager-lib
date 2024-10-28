package com.asset.management.AssetManager.models;

import com.asset.management.AssetManager.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {

    private Long id;
    private String name;
    private String type;
    private String serialNumber;
    private String condition;
    private boolean isAssigned;

    private Long assignedToEmpId;


}

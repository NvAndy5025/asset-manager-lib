package com.asset.management.AssetManager.mapper;


import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.models.AssetDto;

public class AssetMapper {

    public static AssetDto toDto(Asset assetEntity){
        if(assetEntity ==null){
            return null;
        }

        return new AssetDto(assetEntity.getId(), assetEntity.getName(),assetEntity.getType(),assetEntity.getSerialNumber()
                , assetEntity.getCondition(), assetEntity.isAssigned(),getAssignedToEmpID(assetEntity));
    }

    private static Long getAssignedToEmpID(Asset asset){
        if(asset.isAssigned())
            return asset.getAssignedTo().getId();

        return null;
    }

    public static Asset toEntity(AssetDto assetDto){
        if(assetDto ==null){
            return null;
        }
        Asset assetEntity = new Asset();
        assetEntity.setId(assetDto.getId());
        assetEntity.setName(assetDto.getName());
        assetEntity.setType(assetDto.getType());
        assetEntity.setSerialNumber(assetDto.getSerialNumber());
        assetEntity.setCondition(assetDto.getCondition());
        assetEntity.setAssigned(assetDto.isAssigned());
        return assetEntity;
    }
}

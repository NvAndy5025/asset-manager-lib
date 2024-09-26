package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset updateAsset(Long id, Asset assetDetails) {
        return assetRepository.findById(id)
                .map(asset -> {
                    asset.setType(assetDetails.getType());
                    asset.setCondition(assetDetails.getCondition());
                    asset.setAssigned(assetDetails.isAssigned());
                    asset.setAssignedTo(assetDetails.getAssignedTo());
                    return assetRepository.save(asset);
                })
                .orElse(null);
    }
}

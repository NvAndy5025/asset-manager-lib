package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.AssetHistory;
import com.asset.management.AssetManager.repository.AssetHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetHistoryService {

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;

    public List<AssetHistory> getAllAssetHistories() {
        return assetHistoryRepository.findAll();
    }

    public Optional<AssetHistory> getHistoryById(Long id) {
        return assetHistoryRepository.findById(id);
    }

    public AssetHistory createAssetHistory(AssetHistory assetHistory) {
        return assetHistoryRepository.save(assetHistory);
    }
}

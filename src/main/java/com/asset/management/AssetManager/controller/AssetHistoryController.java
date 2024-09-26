package com.asset.management.AssetManager.controller;

import com.asset.management.AssetManager.entity.AssetHistory;
import com.asset.management.AssetManager.service.AssetHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-history")
public class AssetHistoryController {

    @Autowired
    private AssetHistoryService assetHistoryService;

    @GetMapping
    public List<AssetHistory> getAllAssetHistories() {
        return assetHistoryService.getAllAssetHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetHistory> getHistoryById(@PathVariable Long id) {
        return assetHistoryService.getHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssetHistory createAssetHistory(@RequestBody AssetHistory assetHistory) {
        return assetHistoryService.createAssetHistory(assetHistory);
    }
}

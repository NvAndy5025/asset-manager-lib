package com.asset.management.AssetManager.controller;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.models.AssetDto;
import com.asset.management.AssetManager.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping
    public List<AssetDto> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public AssetDto createAsset(@RequestBody AssetDto asset) {
        return assetService.createAsset(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody AssetDto assetDetails) {
        Asset updatedAsset = assetService.updateAsset(id, assetDetails);
        return updatedAsset != null ? ResponseEntity.ok(updatedAsset) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{assetId}/assign/{employeeId}")
    public ResponseEntity<String> assignAssetToEmployee(
            @PathVariable Long assetId, @PathVariable Long employeeId) {
        String result = assetService.assignAssetToEmployee(assetId, employeeId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByAssignations")
    public ResponseEntity<List<Long>> getAssetsByAssignations(@RequestParam boolean isAssigned){
        List<Long> assetsIds = assetService.findByIsAssigned(isAssigned);
        return ResponseEntity.ok(assetsIds);
    }
}

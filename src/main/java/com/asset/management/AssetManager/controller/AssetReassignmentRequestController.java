package com.asset.management.AssetManager.controller;

import com.asset.management.AssetManager.entity.AssetReassignmentRequest;
import com.asset.management.AssetManager.service.AssetReassignmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reassignments")
public class AssetReassignmentRequestController {

    @Autowired
    private AssetReassignmentRequestService reassignmentRequestService;

    @GetMapping
    public List<AssetReassignmentRequest> getAllReassignmentRequests() {
        return reassignmentRequestService.getAllReassignmentRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetReassignmentRequest> getRequestById(@PathVariable Long id) {
        return reassignmentRequestService.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssetReassignmentRequest createReassignmentRequest(@RequestBody AssetReassignmentRequest request) {
        return reassignmentRequestService.createReassignmentRequest(request);
    }

    @PutMapping("/{assetId}/reassign/{newEmployeeId}")
    public ResponseEntity<String> reassignAsset(@PathVariable Long assetId, @PathVariable Long newEmployeeId) {
        String result = reassignmentRequestService.reassignAsset(assetId, newEmployeeId);
        return ResponseEntity.ok(result);
    }

}

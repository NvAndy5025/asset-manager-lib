package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.AssetReassignmentRequest;
import com.asset.management.AssetManager.repository.AssetReassignmentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetReassignmentRequestService {

    @Autowired
    private AssetReassignmentRequestRepository reassignmentRequestRepository;

    public List<AssetReassignmentRequest> getAllReassignmentRequests() {
        return reassignmentRequestRepository.findAll();
    }

    public Optional<AssetReassignmentRequest> getRequestById(Long id) {
        return reassignmentRequestRepository.findById(id);
    }

    public AssetReassignmentRequest createReassignmentRequest(AssetReassignmentRequest request) {
        return reassignmentRequestRepository.save(request);
    }

    public AssetReassignmentRequest updateReassignmentRequest(Long id, AssetReassignmentRequest requestDetails) {
        return reassignmentRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(requestDetails.getStatus());
                    return reassignmentRequestRepository.save(request);
                })
                .orElse(null);
    }
}

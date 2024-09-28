package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.entity.AssetHistory;
import com.asset.management.AssetManager.entity.AssetReassignmentRequest;
import com.asset.management.AssetManager.entity.Employee;
import com.asset.management.AssetManager.repository.AssetHistoryRepository;
import com.asset.management.AssetManager.repository.AssetReassignmentRequestRepository;
import com.asset.management.AssetManager.repository.AssetRepository;
import com.asset.management.AssetManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetReassignmentRequestService {

    @Autowired
    private AssetReassignmentRequestRepository reassignmentRequestRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;

    // Get all reassignment requests
    public List<AssetReassignmentRequest> getAllReassignmentRequests() {
        return reassignmentRequestRepository.findAll();
    }

    // Get reassignment request by ID
    public Optional<AssetReassignmentRequest> getRequestById(Long id) {
        return reassignmentRequestRepository.findById(id);
    }

    // Create a reassignment request
    public AssetReassignmentRequest createReassignmentRequest(AssetReassignmentRequest request) {
        return reassignmentRequestRepository.save(request);
    }

    // Update reassignment request status
    public AssetReassignmentRequest updateReassignmentRequest(Long id, AssetReassignmentRequest requestDetails) {
        return reassignmentRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(requestDetails.getStatus());
                    return reassignmentRequestRepository.save(request);
                })
                .orElse(null);
    }

    // Reassign an asset to a new employee and update history
    public String reassignAsset(Long assetId, Long newEmployeeId) {
        Optional<Asset> assetOpt = assetRepository.findById(assetId);
        Optional<Employee> employeeOpt = employeeRepository.findById(newEmployeeId);

        if (assetOpt.isPresent() && employeeOpt.isPresent()) {
            Asset asset = assetOpt.get();
            Employee newEmployee = employeeOpt.get();

            // Check if the asset is already assigned
            if (asset.isAssigned()) {
                Employee currentEmployee = asset.getAssignedTo();

                asset.setAssignedTo(newEmployee);
                assetRepository.save(asset);

                AssetHistory assetHistory = new AssetHistory();
                assetHistory.setAsset(asset);
                assetHistory.setEmployee(newEmployee);
                assetHistory.setAction("Reassigned from " + currentEmployee.getName() + " to " + newEmployee.getName());
                assetHistoryRepository.save(assetHistory);

                return "Asset reassigned successfully!";
            } else {
                return "Asset is not assigned to any employee!";
            }
        } else {
            return "Asset or Employee not found!";
        }
    }
}

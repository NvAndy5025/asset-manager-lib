package com.asset.management.AssetManager.service;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.entity.AssetHistory;
import com.asset.management.AssetManager.entity.Employee;
import com.asset.management.AssetManager.repository.AssetHistoryRepository;
import com.asset.management.AssetManager.repository.AssetRepository;
import com.asset.management.AssetManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;

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

    public String assignAssetToEmployee(Long assetId, Long employeeId) {
        Optional<Asset> assetOpt = assetRepository.findById(assetId);
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);

        if (assetOpt.isPresent() && employeeOpt.isPresent()) {
            Asset asset = assetOpt.get();
            Employee employee = employeeOpt.get();

            if (!asset.isAssigned()) {
                asset.setAssigned(true);
                asset.setAssignedTo(employee);
                assetRepository.save(asset);

                // Create Asset History record
                AssetHistory assetHistory = new AssetHistory();
                assetHistory.setAsset(asset);
                assetHistory.setEmployee(employee);
                assetHistory.setAction("Assigned");
                assetHistoryRepository.save(assetHistory);

                return "Asset assigned successfully!";
            } else {
                return "Asset is already assigned!";
            }
        } else {
            return "Asset or Employee not found!";
        }
    }
}

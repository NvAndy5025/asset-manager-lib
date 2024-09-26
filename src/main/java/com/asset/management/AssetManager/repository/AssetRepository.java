package com.asset.management.AssetManager.repository;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByAssignedTo(Employee employee);

    List<Asset> findByIsAssigned(boolean isAssigned);
}

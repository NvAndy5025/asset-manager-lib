package com.asset.management.AssetManager.repository;

import com.asset.management.AssetManager.entity.Asset;
import com.asset.management.AssetManager.entity.AssetHistory;
import com.asset.management.AssetManager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetHistoryRepository extends JpaRepository<AssetHistory, Long> {
    List<AssetHistory> findByEmployee(Employee employee);

    List<AssetHistory> findByAsset(Asset asset);
}

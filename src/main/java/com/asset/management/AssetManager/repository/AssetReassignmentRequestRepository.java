package com.asset.management.AssetManager.repository;

import com.asset.management.AssetManager.entity.AssetReassignmentRequest;
import com.asset.management.AssetManager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetReassignmentRequestRepository extends JpaRepository<AssetReassignmentRequest, Long> {
    List<AssetReassignmentRequest> findByStatus(String status);

    List<AssetReassignmentRequest> findByCurrentEmployee(Employee employee);
}

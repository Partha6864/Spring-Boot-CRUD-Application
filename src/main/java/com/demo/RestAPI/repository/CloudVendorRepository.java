package com.demo.RestAPI.repository;

import com.demo.RestAPI.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {
    List<CloudVendor> findByVendorName(String vendorName);

    @Query("SELECT MAX(vendorId) FROM CloudVendor WHERE vendorId LIKE 'V%'")
    String findMaxVendorId();
}

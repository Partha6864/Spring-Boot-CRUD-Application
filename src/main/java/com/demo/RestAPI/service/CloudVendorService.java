package com.demo.RestAPI.service;

import com.demo.RestAPI.model.CloudVendor;

import java.util.List;
import java.util.Optional;

public interface CloudVendorService {
    CloudVendor createCloudVendor(CloudVendor cloudVendor);
    Optional<CloudVendor> getCloudVendorById(String cloudVendorId);
    List<CloudVendor> getAllCloudVendors();
    Optional<CloudVendor> getCloudVendorByName(String vendorName);
    CloudVendor updateCloudVendorById(String cloudVendorId, CloudVendor cloudVendor);
    CloudVendor updateCloudVendorByName(String vendorName, CloudVendor cloudVendor);
    void deleteCloudVendorById(String cloudVendorId);
    void deleteCloudVendorByName(String vendorName);
}

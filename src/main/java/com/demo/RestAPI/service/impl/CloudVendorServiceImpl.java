package com.demo.RestAPI.service.impl;

import com.demo.RestAPI.exception.CloudVendorAlreadyExistsException;
import com.demo.RestAPI.exception.CloudVendorNotFoundException;
import com.demo.RestAPI.model.CloudVendor;
import com.demo.RestAPI.repository.CloudVendorRepository;
import com.demo.RestAPI.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    @Override
    public CloudVendor createCloudVendor(CloudVendor cloudVendor) {
        // Check if a vendor with the same name, address, or phone number already exists
        List<CloudVendor> existingVendors = cloudVendorRepository.findByVendorName(cloudVendor.getVendorName());
        for (CloudVendor existingVendor : existingVendors) {
            if (existingVendor.getVendorAddress().equals(cloudVendor.getVendorAddress()) ||
                existingVendor.getVendorPhoneNumber().equals(cloudVendor.getVendorPhoneNumber())) {
                throw new CloudVendorAlreadyExistsException("Cloud Vendor already exists with the same details");
            }
        }
        return cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public Optional<CloudVendor> getCloudVendorById(String cloudVendorId) {
        if (!cloudVendorRepository.existsById(cloudVendorId)) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        return cloudVendorRepository.findById(cloudVendorId);
    }

    @Override
    public Optional<CloudVendor> getCloudVendorByName(String vendorName) {
        List<CloudVendor> vendor = cloudVendorRepository.findByVendorName(vendorName);
        if (vendor.isEmpty()) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        return vendor.stream().findFirst();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }

    @Override
    public CloudVendor updateCloudVendorById(String cloudVendorId, CloudVendor cloudVendor) {
        if (!cloudVendorRepository.existsById(cloudVendorId)) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        cloudVendor.setVendorId(cloudVendorId);
        return cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public CloudVendor updateCloudVendorByName(String vendorName, CloudVendor cloudVendor) {
        List<CloudVendor> vendor = cloudVendorRepository.findByVendorName(vendorName);
        if (vendor.isEmpty()) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        CloudVendor vendorToUpdate = vendor.get(0);
        cloudVendor.setVendorId(vendorToUpdate.getVendorId());
        return cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public void deleteCloudVendorById(String cloudVendorId) {
        if (!cloudVendorRepository.existsById(cloudVendorId)) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        cloudVendorRepository.deleteById(cloudVendorId);
    }

    @Override
    public void deleteCloudVendorByName(String vendorName) {
        List<CloudVendor> vendors = cloudVendorRepository.findByVendorName(vendorName);
        if (vendors.isEmpty()) {
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        }
        CloudVendor vendorToDelete = vendors.get(0);
        cloudVendorRepository.delete(vendorToDelete);
    }
}

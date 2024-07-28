package com.demo.RestAPI.controller;

import com.demo.RestAPI.model.CloudVendor;
import com.demo.RestAPI.response.ResponseHandler;
import com.demo.RestAPI.service.CloudVendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cloudvendor")
@Api(value = "CloudVendor Management System")
public class CloudVendorController {

    @Autowired
    private CloudVendorService cloudVendorService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new cloud vendor")
    public ResponseEntity<Object> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor) {
        CloudVendor createdVendor = cloudVendorService.createCloudVendor(cloudVendor);
        return ResponseHandler.responseBuilder("Cloud Vendor Created Successfully", HttpStatus.CREATED, createdVendor);
    }

    @GetMapping("/getById/{vendorId}")
    @ApiOperation(value = "Get cloud vendor by ID", notes = "Provide an ID to look up specific cloud vendor", response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetailsById(@PathVariable("vendorId") String vendorId) {
        Optional<CloudVendor> vendor = cloudVendorService.getCloudVendorById(vendorId);
        return ResponseHandler.responseBuilder("Requested Vendor Details are given here", HttpStatus.OK, vendor);
    }

    @GetMapping("/getByName/{vendorName}")
    @ApiOperation(value = "Get cloud vendor by name", notes = "Provide a name to look up specific cloud vendor", response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetailsByName(@PathVariable("vendorName") String vendorName) {
        Optional<CloudVendor> vendor = cloudVendorService.getCloudVendorByName(vendorName);
        return ResponseHandler.responseBuilder("Requested Vendor Details are given here", HttpStatus.OK, vendor);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all cloud vendors", notes = "Retrieve all cloud vendors", response = ResponseEntity.class)
    public ResponseEntity<Object> getAllCloudVendorDetails() {
        List<CloudVendor> vendors = cloudVendorService.getAllCloudVendors();
        return ResponseHandler.responseBuilder("All Cloud Vendors", HttpStatus.OK, vendors);
    }

    @PutMapping("/updateById/{vendorId}")
    @ApiOperation(value = "Update cloud vendor by ID", notes = "Update cloud vendor details by ID")
    public ResponseEntity<Object> updateCloudVendorById(@PathVariable("vendorId") String vendorId, @RequestBody CloudVendor cloudVendor) {
        CloudVendor updatedVendor = cloudVendorService.updateCloudVendorById(vendorId, cloudVendor);
        return ResponseHandler.responseBuilder("Cloud Vendor Updated Successfully by ID", HttpStatus.OK, updatedVendor);
    }

    @PutMapping("/updateByName/{vendorName}")
    @ApiOperation(value = "Update cloud vendor by name", notes = "Update cloud vendor details by name")
    public ResponseEntity<Object> updateCloudVendorByName(@PathVariable("vendorName") String vendorName, @RequestBody CloudVendor cloudVendor) {
        CloudVendor updatedVendor = cloudVendorService.updateCloudVendorByName(vendorName, cloudVendor);
        return ResponseHandler.responseBuilder("Cloud Vendor Updated Successfully by Name", HttpStatus.OK, updatedVendor);
    }

    @DeleteMapping("/deleteById/{vendorId}")
    @ApiOperation(value = "Delete cloud vendor by ID", notes = "Delete cloud vendor by ID")
    public ResponseEntity<Object> deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
        cloudVendorService.deleteCloudVendorById(vendorId);
        return ResponseHandler.messageOnlyResponseBuilder("Cloud Vendor Deleted Successfully by ID", HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{vendorName}")
    @ApiOperation(value = "Delete cloud vendor by name", notes = "Delete cloud vendor by name")
    public ResponseEntity<Object> deleteCloudVendorByName(@PathVariable("vendorName") String vendorName) {
        cloudVendorService.deleteCloudVendorByName(vendorName);
        return ResponseHandler.messageOnlyResponseBuilder("Cloud Vendor Deleted Successfully by Name", HttpStatus.OK);
    }
}

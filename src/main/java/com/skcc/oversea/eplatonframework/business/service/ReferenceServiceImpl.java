package com.skcc.oversea.eplatonframework.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skcc.oversea.reference.transfer.ReferenceDTO;
import com.skcc.oversea.reference.business.facade.IReferenceManagementSB;

import java.util.List;
import java.util.ArrayList;

/**
 * Reference Service Implementation for SKCC Oversea
 * 
 * Provides reference data operations
 * using Spring Boot and modern Java patterns.
 */
@Service
public class ReferenceServiceImpl implements ReferenceService {

    private static final Logger logger = LoggerFactory.getLogger(ReferenceServiceImpl.class);

    @Autowired
    private IReferenceManagementSB referenceManagementSB;

    @Override
    @Transactional(readOnly = true)
    public List<ReferenceDTO> findAll() {
        try {
            logger.info("Finding all references");

            // Get all reference types and their data
            List<ReferenceDTO> allReferences = new ArrayList<>();

            // Common reference types
            String[] referenceTypes = { "COUNTRY", "CURRENCY", "BRANCH", "ACCOUNT_TYPE", "TRANSACTION_TYPE" };

            for (String referenceType : referenceTypes) {
                try {
                    List<Object> referenceData = referenceManagementSB.getReferenceData(referenceType);
                    if (referenceData != null) {
                        for (Object data : referenceData) {
                            if (data instanceof ReferenceDTO) {
                                allReferences.add((ReferenceDTO) data);
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.warn("Error getting reference data for type: {}", referenceType, e);
                }
            }

            logger.info("Found {} references", allReferences.size());
            return allReferences;

        } catch (Exception e) {
            logger.error("Error finding all references", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ReferenceDTO findById(String referenceId) {
        try {
            logger.info("Finding reference by ID: {}", referenceId);

            Object reference = referenceManagementSB.getReferenceById(referenceId);
            if (reference instanceof ReferenceDTO) {
                logger.info("Found reference: {}", referenceId);
                return (ReferenceDTO) reference;
            }

            logger.warn("Reference not found: {}", referenceId);
            return null;

        } catch (Exception e) {
            logger.error("Error finding reference by ID: {}", referenceId, e);
            return null;
        }
    }

    @Override
    @Transactional
    public ReferenceDTO save(ReferenceDTO reference) {
        try {
            logger.info("Saving reference: {}", reference.getReferenceId());

            // Update the reference
            referenceManagementSB.updateReference(reference);

            // Set update date
            reference.setUpdateDate(java.time.LocalDateTime.now());

            logger.info("Reference saved successfully: {}", reference.getReferenceId());
            return reference;

        } catch (Exception e) {
            logger.error("Error saving reference: {}", reference.getReferenceId(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(String referenceId) {
        try {
            logger.info("Deleting reference by ID: {}", referenceId);

            referenceManagementSB.deleteReference(referenceId);

            logger.info("Reference deleted successfully: {}", referenceId);

        } catch (Exception e) {
            logger.error("Error deleting reference: {}", referenceId, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(String referenceId) {
        try {
            logger.debug("Checking if reference exists: {}", referenceId);

            boolean exists = referenceManagementSB.existsReference(referenceId);

            logger.debug("Reference exists: {} = {}", referenceId, exists);
            return exists;

        } catch (Exception e) {
            logger.error("Error checking if reference exists: {}", referenceId, e);
            return false;
        }
    }
}
package com.skax.eatool.reference.business.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skax.eatool.reference.transfer.ReferenceDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Reference Management Service Bean for SKCC Oversea
 * 
 * Provides reference data management operations
 * using Spring Boot and modern Java patterns.
 */
@Service
public class ReferenceManagementSBBean implements IReferenceManagementSB {

    private static final Logger logger = LoggerFactory.getLogger(ReferenceManagementSBBean.class);

    // In-memory storage for reference data (in production, this would be database)
    private final Map<String, List<Object>> referenceDataMap = new HashMap<>();
    private final Map<String, Object> referenceByIdMap = new HashMap<>();

    public ReferenceManagementSBBean() {
        initializeReferenceData();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object> getReferenceData(String referenceType) {
        try {
            logger.info("Getting reference data for type: {}", referenceType);

            List<Object> data = referenceDataMap.get(referenceType);
            if (data != null) {
                logger.info("Found {} references for type: {}", data.size(), referenceType);
                return data;
            }

            logger.warn("No reference data found for type: {}", referenceType);
            return new ArrayList<>();

        } catch (Exception e) {
            logger.error("Error getting reference data for type: {}", referenceType, e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Object getReferenceById(String referenceId) {
        try {
            logger.info("Getting reference by ID: {}", referenceId);

            Object reference = referenceByIdMap.get(referenceId);
            if (reference != null) {
                logger.info("Found reference: {}", referenceId);
                return reference;
            }

            logger.warn("Reference not found: {}", referenceId);
            return null;

        } catch (Exception e) {
            logger.error("Error getting reference by ID: {}", referenceId, e);
            return null;
        }
    }

    @Override
    @Transactional
    public void updateReference(Object reference) {
        try {
            if (reference instanceof ReferenceDTO) {
                ReferenceDTO refDTO = (ReferenceDTO) reference;
                logger.info("Updating reference: {}", refDTO.getReferenceId());

                // Update in both maps
                referenceByIdMap.put(refDTO.getReferenceId(), refDTO);

                // Update in type-based map
                String referenceType = refDTO.getReferenceType();
                List<Object> typeData = referenceDataMap.get(referenceType);
                if (typeData == null) {
                    typeData = new ArrayList<>();
                    referenceDataMap.put(referenceType, typeData);
                }

                // Remove old entry if exists
                typeData.removeIf(obj -> obj instanceof ReferenceDTO &&
                        ((ReferenceDTO) obj).getReferenceId().equals(refDTO.getReferenceId()));

                // Add new entry
                typeData.add(refDTO);

                logger.info("Reference updated successfully: {}", refDTO.getReferenceId());
            } else {
                logger.warn("Invalid reference object type: {}", reference.getClass().getName());
            }

        } catch (Exception e) {
            logger.error("Error updating reference", e);
        }
    }

    @Override
    @Transactional
    public void deleteReference(String referenceId) {
        try {
            logger.info("Deleting reference: {}", referenceId);

            Object reference = referenceByIdMap.get(referenceId);
            if (reference instanceof ReferenceDTO) {
                ReferenceDTO refDTO = (ReferenceDTO) reference;

                // Remove from both maps
                referenceByIdMap.remove(referenceId);

                String referenceType = refDTO.getReferenceType();
                List<Object> typeData = referenceDataMap.get(referenceType);
                if (typeData != null) {
                    typeData.removeIf(obj -> obj instanceof ReferenceDTO &&
                            ((ReferenceDTO) obj).getReferenceId().equals(referenceId));
                }

                logger.info("Reference deleted successfully: {}", referenceId);
            } else {
                logger.warn("Reference not found for deletion: {}", referenceId);
            }

        } catch (Exception e) {
            logger.error("Error deleting reference: {}", referenceId, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsReference(String referenceId) {
        try {
            logger.debug("Checking if reference exists: {}", referenceId);

            boolean exists = referenceByIdMap.containsKey(referenceId);

            logger.debug("Reference exists: {} = {}", referenceId, exists);
            return exists;

        } catch (Exception e) {
            logger.error("Error checking if reference exists: {}", referenceId, e);
            return false;
        }
    }

    /**
     * Initialize reference data with sample data
     */
    private void initializeReferenceData() {
        logger.info("Initializing reference data");

        // Country references
        List<Object> countries = new ArrayList<>();
        countries.add(new ReferenceDTO("KR", "COUNTRY", "Korea"));
        countries.add(new ReferenceDTO("US", "COUNTRY", "United States"));
        countries.add(new ReferenceDTO("JP", "COUNTRY", "Japan"));
        countries.add(new ReferenceDTO("CN", "COUNTRY", "China"));
        referenceDataMap.put("COUNTRY", countries);

        // Currency references
        List<Object> currencies = new ArrayList<>();
        currencies.add(new ReferenceDTO("KRW", "CURRENCY", "Korean Won"));
        currencies.add(new ReferenceDTO("USD", "CURRENCY", "US Dollar"));
        currencies.add(new ReferenceDTO("JPY", "CURRENCY", "Japanese Yen"));
        currencies.add(new ReferenceDTO("CNY", "CURRENCY", "Chinese Yuan"));
        referenceDataMap.put("CURRENCY", currencies);

        // Branch references
        List<Object> branches = new ArrayList<>();
        branches.add(new ReferenceDTO("SEOUL", "BRANCH", "Seoul Main Branch"));
        branches.add(new ReferenceDTO("BUSAN", "BRANCH", "Busan Branch"));
        branches.add(new ReferenceDTO("DAEJEON", "BRANCH", "Daejeon Branch"));
        referenceDataMap.put("BRANCH", branches);

        // Account type references
        List<Object> accountTypes = new ArrayList<>();
        accountTypes.add(new ReferenceDTO("SAVINGS", "ACCOUNT_TYPE", "Savings Account"));
        accountTypes.add(new ReferenceDTO("CHECKING", "ACCOUNT_TYPE", "Checking Account"));
        accountTypes.add(new ReferenceDTO("FIXED", "ACCOUNT_TYPE", "Fixed Deposit"));
        referenceDataMap.put("ACCOUNT_TYPE", accountTypes);

        // Transaction type references
        List<Object> transactionTypes = new ArrayList<>();
        transactionTypes.add(new ReferenceDTO("DEPOSIT", "TRANSACTION_TYPE", "Deposit"));
        transactionTypes.add(new ReferenceDTO("WITHDRAWAL", "TRANSACTION_TYPE", "Withdrawal"));
        transactionTypes.add(new ReferenceDTO("TRANSFER", "TRANSACTION_TYPE", "Transfer"));
        referenceDataMap.put("TRANSACTION_TYPE", transactionTypes);

        // Populate referenceByIdMap
        for (List<Object> dataList : referenceDataMap.values()) {
            for (Object data : dataList) {
                if (data instanceof ReferenceDTO) {
                    ReferenceDTO refDTO = (ReferenceDTO) data;
                    referenceByIdMap.put(refDTO.getReferenceId(), refDTO);
                }
            }
        }

        logger.info("Reference data initialized with {} total references", referenceByIdMap.size());
    }
}

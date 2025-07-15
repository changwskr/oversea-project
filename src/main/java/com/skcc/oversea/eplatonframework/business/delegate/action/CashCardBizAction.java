package com.skcc.oversea.eplatonframework.business.delegate.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.eplatonframework.transfer.EPlatonCommonDTO;
import com.skcc.oversea.eplatonframework.transfer.TPSVCINFODTO;
import com.skcc.oversea.foundation.logej.LOGEJ;
import com.skcc.oversea.foundation.constant.Constants;

/**
 * Cash Card Business Action for SKCC Oversea
 * 
 * Handles cash card related business operations
 * using Spring Boot dependency injection and transaction management.
 */
@Component
public class CashCardBizAction extends EPlatonBizAction {

  private static final Logger logger = LoggerFactory.getLogger(CashCardBizAction.class);

  @Autowired
  private com.skcc.oversea.eplatonframework.business.service.CashCardService cashCardService;

  /**
   * Execute cash card business logic
   */
  @Override
  protected EPlatonEvent executeBusinessLogic(EPlatonEvent event) {
    try {
      logger.info("Starting cash card business action execution");

      // Validate event
      if (!isValidCashCardEvent(event)) {
        setErrorInfo(event, "ECARD001", "Invalid cash card event data");
        return event;
      }

      // Get request type
      String requestType = event.getTPSVCINFODTO().getReqName();

      // Route to appropriate service method
      EPlatonEvent result = routeToCashCardService(event, requestType);

      logger.info("Cash card business action completed successfully");
      return result;

    } catch (Exception e) {
      logger.error("Error in cash card business action", e);
      setErrorInfo(event, "ECARD002", "Cash card business action failed: " + e.getMessage());
      return event;
    }
  }

  /**
   * Route to appropriate cash card service method
   */
  private EPlatonEvent routeToCashCardService(EPlatonEvent event, String requestType) {
    try {
      switch (requestType) {
        case "CREATE_CARD":
          // Extract card data from event and create
          Object requestData = event.getRequest();
          if (requestData instanceof com.skcc.oversea.eplatonframework.business.entity.CashCard) {
            com.skcc.oversea.eplatonframework.business.entity.CashCard card = (com.skcc.oversea.eplatonframework.business.entity.CashCard) requestData;
            com.skcc.oversea.eplatonframework.business.entity.CashCard createdCard = cashCardService
                .createCashCard(card);
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card created successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card created successfully");
          } else {
            setErrorInfo(event, "ECARD004", "Invalid card data for creation");
          }
          break;

        case "UPDATE_CARD":
          // Extract card data from event and update
          Object updateData = event.getRequest();
          if (updateData instanceof com.skcc.oversea.eplatonframework.business.entity.CashCard) {
            com.skcc.oversea.eplatonframework.business.entity.CashCard card = (com.skcc.oversea.eplatonframework.business.entity.CashCard) updateData;
            com.skcc.oversea.eplatonframework.business.entity.CashCard updatedCard = cashCardService
                .updateCashCard(card);
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card updated successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card updated successfully");
          } else {
            setErrorInfo(event, "ECARD005", "Invalid card data for update");
          }
          break;

        case "DELETE_CARD":
          // Extract card ID from event and delete
          Object deleteData = event.getRequest();
          if (deleteData instanceof Long) {
            Long cardId = (Long) deleteData;
            boolean deleted = cashCardService.deleteCashCard(cardId);
            if (deleted) {
              event.getTPSVCINFODTO().setErrorcode("I0000");
              event.getTPSVCINFODTO().setError_message("Card deleted successfully");
            } else {
              setErrorInfo(event, "ECARD006", "Card not found for deletion");
            }
          } else {
            setErrorInfo(event, "ECARD007", "Invalid card ID for deletion");
          }
          break;

        case "GET_CARD":
          // Extract card ID from event and get
          Object getData = event.getRequest();
          if (getData instanceof Long) {
            Long cardId = (Long) getData;
            com.skcc.oversea.eplatonframework.business.entity.CashCard card = cashCardService.getCashCardById(cardId);
            if (card != null) {
              EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
              responseDTO.setMessage("Card retrieved successfully");
              event.setResponse(responseDTO);
              event.getTPSVCINFODTO().setErrorcode("I0000");
              event.getTPSVCINFODTO().setError_message("Card retrieved successfully");
            } else {
              setErrorInfo(event, "ECARD008", "Card not found");
            }
          } else {
            setErrorInfo(event, "ECARD009", "Invalid card ID for retrieval");
          }
          break;

        case "GET_CARD_LIST":
          // Get all cards
          java.util.List<com.skcc.oversea.eplatonframework.business.entity.CashCard> cards = cashCardService
              .getAllCashCards();
          EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
          responseDTO.setMessage("Card list retrieved successfully");
          event.setResponse(responseDTO);
          event.getTPSVCINFODTO().setErrorcode("I0000");
          event.getTPSVCINFODTO().setError_message("Card list retrieved successfully");
          break;

        case "BLOCK_CARD":
          // Extract card ID and status from event and update
          Object blockData = event.getRequest();
          if (blockData instanceof Long) {
            Long cardId = (Long) blockData;
            com.skcc.oversea.eplatonframework.business.entity.CashCard blockedCard = cashCardService
                .updateCardStatus(cardId, "BLOCKED");
            if (blockedCard != null) {
              EPlatonCommonDTO blockResponseDTO = new EPlatonCommonDTO();
              blockResponseDTO.setMessage("Card blocked successfully");
              event.setResponse(blockResponseDTO);
              event.getTPSVCINFODTO().setErrorcode("I0000");
              event.getTPSVCINFODTO().setError_message("Card blocked successfully");
            } else {
              setErrorInfo(event, "ECARD010", "Card not found for blocking");
            }
          } else {
            setErrorInfo(event, "ECARD011", "Invalid card ID for blocking");
          }
          break;

        case "UNBLOCK_CARD":
          // Extract card ID and status from event and update
          Object unblockData = event.getRequest();
          if (unblockData instanceof Long) {
            Long cardId = (Long) unblockData;
            com.skcc.oversea.eplatonframework.business.entity.CashCard unblockedCard = cashCardService
                .updateCardStatus(cardId, "ACTIVE");
            if (unblockedCard != null) {
              EPlatonCommonDTO unblockResponseDTO = new EPlatonCommonDTO();
              unblockResponseDTO.setMessage("Card unblocked successfully");
              event.setResponse(unblockResponseDTO);
              event.getTPSVCINFODTO().setErrorcode("I0000");
              event.getTPSVCINFODTO().setError_message("Card unblocked successfully");
            } else {
              setErrorInfo(event, "ECARD012", "Card not found for unblocking");
            }
          } else {
            setErrorInfo(event, "ECARD013", "Invalid card ID for unblocking");
          }
          break;

        default:
          setErrorInfo(event, "ECARD003", "Unknown request type: " + requestType);
          break;
      }
    } catch (Exception e) {
      logger.error("Error in routeToCashCardService", e);
      setErrorInfo(event, "ECARD014", "Service operation failed: " + e.getMessage());
    }

    return event;
  }

  /**
   * Validate cash card event
   */
  private boolean isValidCashCardEvent(EPlatonEvent event) {
    if (event == null || event.getTPSVCINFODTO() == null) {
      return false;
    }

    String reqName = event.getTPSVCINFODTO().getReqName();
    return reqName != null && !reqName.trim().isEmpty();
  }

  /**
   * Pre-act processing for cash card
   */
  @Override
  public void preAct(EPlatonEvent event) {
    logger.debug("Pre-act processing for cash card business action");
    // Add any pre-processing logic here
  }

  /**
   * Post-act processing for cash card
   */
  @Override
  public void postAct(EPlatonEvent event) {
    logger.debug("Post-act processing for cash card business action");
    // Add any post-processing logic here
  }

}

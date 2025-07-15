package com.skcc.oversea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Cash Card Controller
 * 
 * Provides REST API endpoints for cash card management operations.
 */
@RestController
@RequestMapping("/api/cashcards")
@Tag(name = "Cash Card Management", description = "Cash card management operations")
public class CashCardController {

    @GetMapping
    @Operation(
        summary = "Get All Cash Cards",
        description = "Retrieves a list of all cash cards in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved cash cards"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> getAllCashCards() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cash cards retrieved successfully");
        response.put("data", new String[]{"Sample cash card data"});
        response.put("count", 1);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cardId}")
    @Operation(
        summary = "Get Cash Card by ID",
        description = "Retrieves a specific cash card by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved cash card"),
        @ApiResponse(responseCode = "404", description = "Cash card not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> getCashCardById(
            @Parameter(description = "ID of the cash card to retrieve", required = true)
            @PathVariable String cardId) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cash card retrieved successfully");
        response.put("cardId", cardId);
        response.put("cardNumber", "1234567890123456");
        response.put("status", "ACTIVE");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
        summary = "Create Cash Card",
        description = "Creates a new cash card in the system"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cash card created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> createCashCard(
            @Parameter(description = "Cash card data", required = true)
            @RequestBody Map<String, Object> cashCardData) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cash card created successfully");
        response.put("cardId", "CARD_" + System.currentTimeMillis());
        response.put("data", cashCardData);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{cardId}")
    @Operation(
        summary = "Update Cash Card",
        description = "Updates an existing cash card"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cash card updated successfully"),
        @ApiResponse(responseCode = "404", description = "Cash card not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> updateCashCard(
            @Parameter(description = "ID of the cash card to update", required = true)
            @PathVariable String cardId,
            @Parameter(description = "Updated cash card data", required = true)
            @RequestBody Map<String, Object> cashCardData) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cash card updated successfully");
        response.put("cardId", cardId);
        response.put("data", cashCardData);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cardId}")
    @Operation(
        summary = "Delete Cash Card",
        description = "Deletes a cash card from the system"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cash card deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Cash card not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, Object>> deleteCashCard(
            @Parameter(description = "ID of the cash card to delete", required = true)
            @PathVariable String cardId) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cash card deleted successfully");
        response.put("cardId", cardId);
        return ResponseEntity.ok(response);
    }
} 
package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.service.StockTradeService;
import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author ardaaksoydan
 */
@RestController
@Validated
@RequestMapping("/trades")
public class StockTradeRestController {

  private final StockTradeService service;

  public StockTradeRestController(StockTradeService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<StockTrade> createStockTrade(@RequestBody StockTrade stockTrade) {
    StockTrade newTrade = new StockTrade(stockTrade.getType(), stockTrade.getUserId(), stockTrade.getSymbol(),
                                         stockTrade.getShares(), stockTrade.getPrice(), stockTrade.getTimestamp());
    StockTrade insertTrade = service.insertStockTrade(newTrade);
    return ResponseEntity.status(HttpStatus.CREATED).body(insertTrade);
  }

  @GetMapping
  public ResponseEntity<List<StockTrade>> getAllStockTrades() {
    return ResponseEntity.ok(service.getAllStockTrades());
  }

  @GetMapping("/{id}")
  public ResponseEntity<StockTrade> getStockTradesById(@PathVariable @NotNull Integer id) {
    Optional<StockTrade> stockTrade = service.getStockTradeById(id);
    return stockTrade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }

  @RequestMapping(value = "/{ignoredId}", method = {RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
  public ResponseEntity<?> handleInvalidActions(@PathVariable @NotNull Integer ignoredId) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
  }

}
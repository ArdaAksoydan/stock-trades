package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ardaaksoydan
 */
@Service
public class StockTradeService {

  private final StockTradeRepository repository;

  public StockTradeService(StockTradeRepository repository) {
    this.repository = repository;
  }

  public StockTrade insertStockTrade(StockTrade stockTrade) {
    return repository.save(stockTrade);
  }

  public List<StockTrade> getAllStockTrades() {
    return repository.findAllByOrderByIdAsc();
  }

  public Optional<StockTrade> getStockTradeById(Integer id) {
    return repository.findById(id);
  }

}
package com.example.transaction.controller;

import com.example.transaction.models.Item;
import com.example.transaction.models.Transaction;
import com.example.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction() {
        return transactionService.createTransaction();
    }

    @PostMapping("/{transactionId}/add")
    public void addItemToTransaction(@PathVariable Long transactionId, @RequestBody Item item) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            transactionService.addItemToTransaction(transaction, item);
        }
    }

    @DeleteMapping("/{transactionId}/remove")
    public void removeItemFromTransaction(@PathVariable Long transactionId, @RequestBody Item item) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            transactionService.removeItemFromTransaction(transaction, item);
        }
    }

    @PostMapping("/{transactionId}/finalize")
    public double finalizeTransaction(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction != null) {
            return transactionService.calculateTotalPrice(transaction);
        }
        return 0;
    }
}


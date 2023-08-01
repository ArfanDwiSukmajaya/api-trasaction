package com.example.transaction.services;

import com.example.transaction.models.Item;
import com.example.transaction.models.Transaction;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }
    public Transaction createTransaction() {
        Transaction transaction = new Transaction();
        return transactionRepository.save(transaction);
    }
    public void addItemToTransaction(Transaction transaction, Item item) {
        transaction.getItems().add(item);
        transactionRepository.save(transaction);
    }

    public void removeItemFromTransaction(Transaction transaction, Item item) {
        transaction.getItems().remove(item);
        transactionRepository.save(transaction);
    }

    public double calculateTotalPrice(Transaction transaction) {
        double totalPrice = 0;
        for (Item item : transaction.getItems()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}

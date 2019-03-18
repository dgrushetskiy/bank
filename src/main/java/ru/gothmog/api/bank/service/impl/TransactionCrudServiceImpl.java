package ru.gothmog.api.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.api.bank.exception.NotFoundException;
import ru.gothmog.api.bank.exception.ResourceNotFoundException;
import ru.gothmog.api.bank.model.Transaction;
import ru.gothmog.api.bank.repository.TransactionRepository;
import ru.gothmog.api.bank.service.TransactionCrudService;

import java.util.List;

@Transactional
@Service
public class TransactionCrudServiceImpl implements TransactionCrudService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionCrudServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException("Transaction", "transactionId", transactionId));
        return transaction;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction transactionUpdate) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    transaction.setAmount(transactionUpdate.getAmount());
                    transaction.setDate(transactionUpdate.getDate());
                    transaction.setBusiness(transactionUpdate.getBusiness());
                    transaction.setName(transactionUpdate.getName());
                    transaction.setType(transactionUpdate.getType());
                    transaction.setAccount(transactionUpdate.getAccount());
                    return transactionRepository.save(transaction);
                }).orElseThrow(() -> new NotFoundException("transaction not found with id" + transactionId));
    }

    @Override
    public String deleteTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    transactionRepository.delete(transaction);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new NotFoundException("Transaction not found with id" + transactionId));
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}

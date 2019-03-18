package ru.gothmog.api.bank.service;

import ru.gothmog.api.bank.model.Transaction;

import java.util.List;


public interface TransactionCrudService {



    Transaction getTransactionById(Long transactionId);

    Transaction createTransaction(Transaction transaction);

    Transaction updateTransaction(Long transactionId, Transaction transactionUpdate);

    String deleteTransaction(Long transactionId);

    List<Transaction> getAllTransaction();
}

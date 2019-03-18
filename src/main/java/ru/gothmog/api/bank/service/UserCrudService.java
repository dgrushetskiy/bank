package ru.gothmog.api.bank.service;

import ru.gothmog.api.bank.model.User;

import java.util.List;


public interface UserCrudService {

    User createUser(Long transactionId, User user);

   // Iterable<User> createAllUser(Long transactionId, User user);

    User getUserByTransactionId(Long transactionId);

    User getUserById(Long userId);

    User updateUser(Long userId, User userUpdate);

    String deleteUser(Long userId);

    List<User> getAllUsers();
}

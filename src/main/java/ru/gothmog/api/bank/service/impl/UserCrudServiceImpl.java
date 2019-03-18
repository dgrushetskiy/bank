package ru.gothmog.api.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gothmog.api.bank.exception.NotFoundException;
import ru.gothmog.api.bank.exception.ResourceNotFoundException;
import ru.gothmog.api.bank.model.User;
import ru.gothmog.api.bank.repository.TransactionRepository;
import ru.gothmog.api.bank.repository.UserRepository;
import ru.gothmog.api.bank.service.UserCrudService;

import java.util.List;

@Transactional
@Service
public class UserCrudServiceImpl implements UserCrudService {

    private UserRepository userRepository;

    private TransactionRepository transactionRepository;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public User createUser(Long transactionId, User user) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> {
                    user.setTransaction(transaction);
                    return userRepository.save(user);
                }).orElseThrow(() -> new NotFoundException("Transaction not found!"));
    }

//    @Override
//    public Iterable<User> createAllUser(Long transactionId, User user) {
//        return null;
//    }

    @Override
    public User getUserByTransactionId(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new NotFoundException("Transaction not found!");
        }
        List<User> users = userRepository.findByTransactionId(transactionId);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    @Override
    public User getUserById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        return optionalUser;
    }

    @Override
    public User updateUser(Long userId, User userUpdate) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(userUpdate.getFirstName());
                    user.setLastName(userUpdate.getLastName());
                    user.setState(userUpdate.getState());
                    return userRepository.save(user);
                }).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public String deleteUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return "Deleted successfully!!" + user;
                }).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}

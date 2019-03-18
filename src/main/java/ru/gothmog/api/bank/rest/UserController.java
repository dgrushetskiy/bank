package ru.gothmog.api.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gothmog.api.bank.model.User;
import ru.gothmog.api.bank.service.TransactionCrudService;
import ru.gothmog.api.bank.service.UserCrudService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserCrudService userCrudService;

    private TransactionCrudService transactionCrudService;

    @Autowired
    public UserController(UserCrudService userCrudService, TransactionCrudService transactionCrudService) {
        this.userCrudService = userCrudService;
        this.transactionCrudService = transactionCrudService;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userCrudService.getAllUsers();
    }

    @GetMapping("/transactions/{transactionId}/users" )
    @ResponseBody
    public User getUserByTransactionId(@PathVariable Long transactionId) {
        return userCrudService.getUserByTransactionId(transactionId);
    }
}

package ru.gothmog.api.bank.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gothmog.api.bank.model.Transaction;
import ru.gothmog.api.bank.model.User;
import ru.gothmog.api.bank.service.TransactionCrudService;
import ru.gothmog.api.bank.service.UserCrudService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
@Service
public class ParseJson {
    @Autowired
    private TransactionCrudService transactionCrudService;
    @Autowired
    private UserCrudService userCrudService;

    FileReader fileReader;

    {
        try {
            fileReader = new FileReader("G:\\json_file\\data.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

  //  String json = new Scanner(new File(data.json)).useDelimiter("\\Z").next();;
    public void startJson(){

        JsonObject jsonObject = new JsonParser().parse(fileReader).getAsJsonObject();
        JsonArray array = jsonObject.getAsJsonArray("bank");
        for (int i = 0; i < array.size(); i++) {
            String firstName = array.get(i).getAsJsonObject().get("firstName").getAsString();
            System.out.println("firstName: " + firstName);
            String lastName = array.get(i).getAsJsonObject().get("lastName").getAsString();
            System.out.println("lastName: " + lastName);
            String state = array.get(i).getAsJsonObject().get("state").getAsString();
            System.out.println("state: " + state);
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setState(state);

            String amount = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("amount").getAsString();
            System.out.println("amount: " + amount);
            String date = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("date").getAsString();
            System.out.println("date : " + date);
            String business = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("business").getAsString();
            System.out.println("business : " + business);
            String name = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("name").getAsString();
            System.out.println("name : " + name);
            String type = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("type").getAsString();
            System.out.println("type : " + type);
            String account = array.get(i).getAsJsonObject().get("transaction").getAsJsonObject().get("account").getAsString();
            System.out.println("account : " + account);

            Transaction transaction = new Transaction();
            transaction.setAmount(new BigDecimal(amount));
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            transaction.setDate(dateTime);
            transaction.setBusiness(business);
            transaction.setName(name);
            transaction.setType(type);
            transaction.setAccount(Integer.parseInt(account));
            Transaction transactionLast = transactionCrudService.createTransaction(transaction);
            Long transactId = transactionLast.getId();
            System.out.println("transactId : " + transactId);
            //user.setTransaction(transaction);
            userCrudService.createUser(transactId,user);
        }
    }
}

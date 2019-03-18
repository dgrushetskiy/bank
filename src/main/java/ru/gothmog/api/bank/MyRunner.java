package ru.gothmog.api.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.gothmog.api.bank.util.ParseJson;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private ParseJson parseJson;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test");
      //  parseJson.startJson();
    }
}

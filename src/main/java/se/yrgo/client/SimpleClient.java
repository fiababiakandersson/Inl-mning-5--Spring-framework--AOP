package se.yrgo.client;

import java.util.*;

import org.springframework.context.support.*;

import se.yrgo.domain.*;
import se.yrgo.services.calls.*;
import se.yrgo.services.customers.*;
import se.yrgo.services.diary.*;

public class SimpleClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
        CallHandlingService callService = container.getBean(CallHandlingService.class);
        DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

        customerService.newCustomer(new Customer("CS03939", "Acme", "Good Customer"));

        Call newCall = new Call("Larry Wall called from Acme Corp");
        Action action1 = new Action("Call back Larry to ask how things are going", new GregorianCalendar(2016, 0, 0),
                "rac");
        Action action2 = new Action("Check our sales dept to make sure Larry is being tracked",
                new GregorianCalendar(2016, 0, 0), "rac");

        List<Action> actions = new ArrayList<Action>();
        actions.add(action1);
        actions.add(action2);

        try {
            callService.recordCall("CS03939", newCall, actions);
        } catch (CustomerNotFoundException e) {
            System.out.println("That customer doesn't exist");
        }

        System.out.println("Here are the outstanding actions:");
        Collection<Action> incompleteActions = diaryService.getAllIncompleteActions("rac");
        for (Action next : incompleteActions) {
            System.out.println(next);
        }

        container.close();
    }
}
package se.yrgo.services.calls;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.services.customers.*;
import se.yrgo.services.diary.*;

public class CallHandlingServiceImpl implements CallHandlingService {

    private CustomerManagementService customerService;
    private DiaryManagementService diaryService;

    // Constructor to inject dependencies
    public CallHandlingServiceImpl(CustomerManagementService customerService,
            DiaryManagementService diaryService) {
        this.customerService = customerService;
        this.diaryService = diaryService;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions)
            throws CustomerNotFoundException {

        // Step 1: Record the call for the customer
        customerService.recordCall(customerId, newCall);

        // Step 2: Record each action in the diary
        for (Action action : actions) {
            diaryService.recordAction(action);
        }
    }
}

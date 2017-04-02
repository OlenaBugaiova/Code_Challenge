package com.olenabugaiova.controller;

import com.olenabugaiova.backingbeans.StatisticsPerLastPeriod;
import com.olenabugaiova.entity.Second;
import com.olenabugaiova.entity.Transaction;
import com.olenabugaiova.service.SecondDataService;
import com.olenabugaiova.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by elena on 31.03.17.
 */
@RestController
public class TransactionsController {

    @Autowired
    private SecondDataService secondDataService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(path = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleTransaction(@RequestBody Transaction transaction) {

        boolean result = transactionService.insertTransaction(transaction);
        if(result) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @RequestMapping(path = "/statistics", method = RequestMethod.GET)
    public @ResponseBody StatisticsPerLastPeriod getStatistics () {

        List<Second> seconds = secondDataService.getDataForLastPeriod();
        StatisticsPerLastPeriod statistics;

        if(seconds != null && !seconds.isEmpty()) {
            long count = 0;
            double sum = 0.0;
            double max = seconds.get(0).getMax();
            double min = seconds.get(0).getMin();

            for(Second second: seconds){
                sum += second.getSum();
                count += second.getCount();
                if(max < second.getMax()) {
                    max = second.getMax();
                }
                if(min > second.getMin()) {
                    min = second.getMin();
                }
            }

            statistics = new StatisticsPerLastPeriod(sum, count, sum/count, max, min);

//          I know how to use stream but loop for each is more useful and more elegant here

/*          statistics = new DataPerMinute();

            statistics.setSum(seconds.stream().map(a -> a.getSum()).reduce((a,b) -> a + b).get());
            statistics.setCount(seconds.stream().map(a -> a.getCount()).reduce((a,b) -> a + b).get());
            statistics.setMax(seconds.stream().map(a -> a.getMax()).mapToDouble(Double::doubleValue).max().getAsDouble());
            statistics.setMin(seconds.stream().map(a -> a.getMin()).mapToDouble(Double::doubleValue).min().getAsDouble());
            statistics.setAvg(statistics.getSum()/statistics.getCount());*/
        } else {
            statistics = new StatisticsPerLastPeriod();
        }

        return statistics;
    }

    @Scheduled(fixedRate = 120000)
    public void deleteOldData() {
        transactionService.deleteOldData();
        secondDataService.deleteOldData();
    }
}

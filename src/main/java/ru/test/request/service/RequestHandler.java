package ru.test.request.service;

import org.springframework.stereotype.Component;
import ru.test.request.dao.RequestDao;
import ru.test.request.model.Request;
import ru.test.request.model.command.CreateRequest;

import java.math.BigDecimal;

@Component
public class RequestHandler {

    private final RequestDao requestDao;
    private final CommandSender commandSender;
    private final StockService stockService;

    public RequestHandler(RequestDao requestDao, CommandSender commandSender, StockService stockService) {
        this.requestDao = requestDao;
        this.commandSender = commandSender;
        this.stockService = stockService;
    }

    public void createRequest(CreateRequest command) {
        requestDao.insert(Request.from(command));
        var stock = stockService.getStockInfo(command.getStockCode());
        commandSender.checkAccount(command.getId(), command.getPersonId(),
                stock.getPrice().multiply(BigDecimal.valueOf(command.getStockCount())));
    }
}

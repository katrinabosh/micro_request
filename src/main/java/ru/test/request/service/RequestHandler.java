package ru.test.request.service;

import org.springframework.stereotype.Component;
import ru.test.request.dao.RequestDao;
import ru.test.request.model.Request;
import ru.test.request.model.command.CreateRequest;
import ru.test.request.model.event.AccountChanged;
import ru.test.request.model.event.AccountChecked;
import ru.test.request.model.event.StocksBought;

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
        var stock = stockService.getStockInfo(command.getStockCode());
        // todo обработать условия с отказами

        var request = Request.from(command);
        requestDao.insert(request);
        commandSender.requestCreated(request);

        commandSender.checkAccount(command.getId(), command.getPersonId(),
                stock.getPrice().multiply(BigDecimal.valueOf(command.getStockCount())));
    }

    public void listenAccountChecked(AccountChecked event) {
        var req = requestDao.getRequest(event.getRequestId());
        commandSender.buyStock(event.getRequestId(), req.getStockCode(), req.getStockCount(), event.getSum());
    }

    public void listenStocksBought(StocksBought event) {
        var req = requestDao.getRequest(event.getRequestId());
        commandSender.changeAccount(event.getRequestId(), req.getPersonId(), event.getSum());
    }

    public void listenAccountChanged(AccountChanged event) {
        requestDao.delete(event.getRequestId());
    }

}

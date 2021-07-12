package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.ShopController;
import Server.model.user.User;

import java.net.Socket;

public class ShopRequestHandler extends RequestHandler{
    public ShopRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
       String[] parts = request.split("\n");
       String token = parts[0];
       User user = DatabaseController.getUserByToken(token);
       ShopController shopController = new ShopController(user.getUsername());
       String methodName = parts[2];
       Object[] fields = fieldParser.getObjects(request);
       Object answer = "";

       if (methodName.equals("getUserMoney"))
           answer = shopController.getUserMoney();
       else if (methodName.equals("buyCardErrorHandler"))
           answer = shopController.buyCardErrorHandler((String) fields[0]);
       else if (methodName.equals("numberOfBoughtCards"))
           answer = shopController.numberOfBoughtCards((String) fields[0]);
       else if (methodName.equals("getCardsPrices"))
           answer = shopController.getCardsPrices();
       else if (methodName.equals("increaseMoneyCheat"))
           shopController.increaseMoneyCheat((Integer) fields[0]);

       return fieldParser.getAnswer(answer);
    }
}

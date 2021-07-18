package Client.ClientServer;

import Network.PortConfig;

import java.util.HashMap;

public class ClientShopServer extends ClientServer{

    public ClientShopServer() {
        super(PortConfig.SHOP_PORT.getPort(), "ShopController");
    }

    public int getUserMoney() {
        Object result = sendRequest.getMethodResult("getUserMoney");
        return (int) result;
    }

    public HashMap<String, Integer> getShopInventory() {
        Object result = sendRequest.getMethodResult("getShopInventory");
        return (HashMap<String, Integer>) result;
    }

    public HashMap<String, Boolean> getCardsStatus() {
        Object result = sendRequest.getMethodResult("getCardsStatus");
        return (HashMap<String, Boolean>) result;
    }

    public int buyCardErrorHandler(String cardName) {
        Object result = sendRequest.getMethodResult("buyCardErrorHandler", cardName);
        return (int) result;
    }

    public int numberOfBoughtCards(String cardName) {
        Object result = sendRequest.getMethodResult("numberOfBoughtCards", cardName);
        return (int) result;
    }

    public HashMap<String, Integer> getCardsPrices() {
        Object result = sendRequest.getMethodResult("getCardsPrices");
        return (HashMap<String, Integer>) result;
    }

    public void increaseMoneyCheat(int money) {
        sendRequest.getMethodResult("increaseMoneyCheat", money);
    }
}

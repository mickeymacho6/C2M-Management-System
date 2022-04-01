package InventoryPage;

public class Person {
    private String name;
    private String cardNumber;
    private String quantity;

    public Person(String name, String cardNumber, String quantity) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.quantity = quantity;
    }

    public Person(String name, String cardNumber, String quantity, String cardAge) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return("[Person] name = "+this.name+", cardNumber = "+this.cardNumber +", quantity = "+this.quantity);
    }
}

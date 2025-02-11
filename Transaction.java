public class Transaction {
    private int amount;
    private String category;
    private String date;
    private String type; // "収入" または "支出"

    public Transaction(int amount, String category, String date, String type) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    public int getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return date + " - " + category + " : " + type + " " + amount + "円";
    }
}

import java.util.ArrayList;
import java.util.List;

public class MoneyManager {
    private List<Transaction> transactions;

    public MoneyManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(int amount, String category, String date, String type) {
        transactions.add(new Transaction(amount, category, date, type));
    }

    public int getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equals("収入"))
                .mapToInt(Transaction::getAmount)
                .sum();
    }

    public int getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType().equals("支出"))
                .mapToInt(Transaction::getAmount)
                .sum();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void displayTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

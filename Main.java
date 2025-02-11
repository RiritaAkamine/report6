import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MoneyManager manager = new MoneyManager();
        Scanner scanner = new Scanner(System.in);
        String filename = "transactions.csv";

        // 既存のデータを読み込み
        manager.getTransactions().addAll(FileHandler.loadFromFile(filename));

        while (true) {
            System.out.println("\n1. 取引を追加");
            System.out.println("2. 取引を表示");
            System.out.println("3. 収支合計を表示");
            System.out.println("4. 終了");

            System.out.print("番号を入力してください: "); // ユーザーが入力すべきことを明示
            if (!scanner.hasNextInt()) { // 整数以外が入力された場合の対策
                System.out.println("無効な入力です。数値を入力してください。");
                scanner.next(); // 不正な入力をクリア
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // 改行を消す

            switch (choice) {
                case 1:
                    System.out.print("金額を入力: ");
                    while (!scanner.hasNextInt()) { // 数値が入力されるまで待つ
                        System.out.println("無効な入力です。数値を入力してください。");
                        scanner.next();
                    }
                    int amount = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("カテゴリーを入力: ");
                    String category = scanner.nextLine();

                    System.out.print("日付を入力 (yyyy-MM-dd): ");
                    String date = scanner.nextLine();

                    System.out.print("収入 or 支出を入力: ");
                    String type = scanner.nextLine();

                    manager.addTransaction(amount, category, date, type);
                    FileHandler.saveToFile(manager.getTransactions(), filename);
                    break;

                case 2:
                    manager.displayTransactions();
                    break;

                case 3:
                    System.out.println("総収入: " + manager.getTotalIncome() + "円");
                    System.out.println("総支出: " + manager.getTotalExpense() + "円");
                    System.out.println("残高: " + (manager.getTotalIncome() - manager.getTotalExpense()) + "円");
                    break;

                case 4:
                    System.out.println("終了します。");
                    scanner.close();
                    return;

                default:
                    System.out.println("無効な選択です。1〜4の数字を入力してください。");
            }
        }
    }
}

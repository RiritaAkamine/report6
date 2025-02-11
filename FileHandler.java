import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DEFAULT_FILENAME = "transactions.csv";

    public static void saveToFile(List<Transaction> transactions, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.getDate() + "," + t.getCategory() + "," + t.getType() + "," + t.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("ファイルの保存中にエラーが発生しました: " + e.getMessage());
        }
    }

    public static List<Transaction> loadFromFile(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filename);

        // ファイルが存在しない場合は新規作成
        if (!file.exists()) {
            System.out.println("初回起動: データファイルが存在しません。新規作成します。");
            try {
                file.createNewFile(); // 空のファイルを作成
            } catch (IOException e) {
                System.out.println("ファイル作成時にエラーが発生しました: " + e.getMessage());
            }
            return transactions; // 空のリストを返す
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    transactions.add(new Transaction(
                            Integer.parseInt(parts[3]), parts[1], parts[0], parts[2]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("ファイルの読み込み中にエラーが発生しました: " + e.getMessage());
        }
        return transactions;
    }
}

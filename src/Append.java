import java.io.*;
public class Append {

    public void appendData(String[] data) {
        String fileName = "data.csv";
        File file = new File(fileName);

        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter pw = new PrintWriter(fw)) {

            if (!file.exists()) {
                file.createNewFile();
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                sb.append(data[i]);
                if (i < data.length - 1) {
                    sb.append(",");
                }
            }
            pw.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

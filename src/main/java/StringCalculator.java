import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private long result;
    private String delimiters;

    public StringCalculator() {
        delimiters = ",|\n";
        result = 0;
    }

    public long add(String numbers) throws Exception {
        if (numbers.startsWith("//")) {
            int endDel = numbers.indexOf("\n");
            String difDels = numbers.substring(2, endDel);
            updateDelimiters(difDels);
            numbers = numbers.substring(endDel + 1);
        }
        if (!numbers.isEmpty()) {
            List<String> numList = List.of(numbers.split(delimiters));
            findNegatives(numList);
            result = numList.stream()
                    .mapToLong(Long::parseLong)
                    .filter(num -> num < 1000)
                    .sum();
        }
        return result;
    }


    private void findNegatives(List<String> numList) throws Exception {
        List<String> negList = numList.stream()
                .filter(neg -> neg.startsWith("-"))
                .collect(Collectors.toList());
        if (!negList.isEmpty()) {
            String message = "negatives not allowed : ";
            message = message.concat(String.join(",", negList));
            throw new Exception(message);
        }
    }

    private void updateDelimiters(String delimiters) {
        if (delimiters.contains("[") && delimiters.contains("]")) {
            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(delimiters);
            while (matcher.find()) {
                this.delimiters = this.delimiters + "|" + matcher.group(1);
            }
        } else {
            this.delimiters = this.delimiters + "|" + delimiters;
        }
    }

}

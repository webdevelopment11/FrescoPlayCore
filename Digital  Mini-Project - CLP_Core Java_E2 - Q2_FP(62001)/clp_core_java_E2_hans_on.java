ublic class Main {
    static String originalString = "abcabc";

    private static int operationCount(String word, int input1, int input2) {

        String updatedWord = "";
        updatedWord = checkNextIteration(originalString, input1);
        int count = 1;
        boolean flagForInput2 = true;
        while (!originalString.equals(updatedWord)) {
            if (flagForInput2) {
                updatedWord = checkNextIteration(updatedWord, input2);
                flagForInput2 = false;
                count++;
            } else {
                updatedWord = checkNextIteration(updatedWord, input1);
                flagForInput2 = true;
                count++;
            }
        }
        return count;

    }

    private static String checkNextIteration(String word, int shift) {
        String part1 = word.substring(word.length() - shift);
        String part2 = word.substring(0, word.length() - shift);
        System.out.println(part1.concat(part2));
        return part1.concat(part2);
    }

    // arguments are passed using the text field below this editor
    public static void main(String[] args) {
        originalString = args[0];
        System.out.println("Original String: " + originalString);
        System.out.println(operationCount(originalString, 1, 2));
    }
}

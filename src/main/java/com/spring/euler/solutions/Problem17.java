package com.spring.euler.solutions;

public abstract class Problem17 {
    public static Integer run() {
        String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        int result = 11;
        for (int i = 0; i <= 9; i++) {
            if (i > 0) { result += (digits[i - 1].length() + 7) + ((digits[i - 1].length() + 10) * 99); }
            for (int j = 1; j < 100; j++) {
                if (j < 10) { result += digits[j - 1].length(); }
                else if (j % 10 == 0) { result += tens[(j / 10) - 1].length(); }
                else if (j < 20) { result += teens[(j / 10) - 1].length(); }
                else { result += tens[(j / 10) - 1].length() + digits[(j % 10) - 1].length(); }
            }
        }
        return result;
    }
}

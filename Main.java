import java.sql.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("1-st task");
        task1();
        System.out.println("2-nd task");
        task2();
        System.out.println("3-rd task");
        task3();
        System.out.println("4-th task");
        task4();
        System.out.println("5-rd task");
        task5();
        System.out.println("6-rd task");
        task6();
        System.out.println("7-rd task");
        task7();
        System.out.println("8-th task");
        task8();
        System.out.println("9-th task");
        task9();
        System.out.println("10-th task");
        task10();
    }
    static void task1(){
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
    }
    public static boolean sameLetterPattern(String s1, String s2){
        if (s1.length() != s2.length()){
            return false;
        }else if (s1.length() == 1){
            return true;
        }
        for (int i = 1; i < s1.length(); i++) {
            if (s1.charAt(i) - s1.charAt(i - 1) != s2.charAt(i) - s2.charAt(i - 1)){
                return false;
            }
        }
        return true;
    }

    static void task2(){
        System.out.println(spiderVsFly("E1", "E4"));
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
        System.out.println(spiderVsFly("A4", "E2"));
        System.out.println(spiderVsFly("A1", "G1"));
        System.out.println(spiderVsFly("F2", "H2"));
        System.out.println(spiderVsFly("A1", "H2"));
        System.out.println(spiderVsFly("A1", "G2"));
        System.out.println(spiderVsFly("G4", "H1"));
        System.out.println(spiderVsFly("E1", "D3"));
        System.out.println(spiderVsFly("E1", "C3"));
    }

    static class point{

        private String letter;
        private int value;

        public String getLetter() {
            return letter;
        }

        public int getValue() {
            return value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public point(String letter, int value) {
            this.letter = letter;
            this.value = value;
        }
    }

    public static String spiderVsFly(String startCoord, String finishCoord){
        String result = startCoord;
        point[] arr = new point[8];
        arr[0] = new point("A", 0);
        arr[1] = new point("B", 1);
        arr[2] = new point("C", 2);
        arr[3] = new point("D", 3);
        arr[4] = new point("E", 4);
        arr[5] = new point("F", 5);
        arr[6] = new point("G", 6);
        arr[7] = new point("H", 7);
        int value1 = 0;
        int value2 = 0;
        String letter1 = startCoord.substring(0,1);
        String letter2 = finishCoord.substring(0,1);
        for (point point : arr){
            if (letter1.equals(point.getLetter())){
                value1 = point.getValue();
            }
            if (letter2.equals(point.getLetter())){
                value2 = point.getValue();
            }
        }
        int offset = Math.abs(value1 - value2);
        int pos1 = startCoord.charAt(1) - '0';
        int pos2 = finishCoord.charAt(1) - '0';
        if (letter1.equals(letter2)){
            if (pos1 >= pos2){
                for (int i = pos1 - 1; i >= pos2; i--){
                    result += "-" + letter1 + i;
                }
            }else{
                for (int i = pos1 + 1; i <= pos2; i++){
                    result += "-" + letter1 + i;
                }
            }
        }else if((pos1 == 0) || (pos2 == 0)){
            if (pos1 == 0){
                pos1++;
                while (pos1 <= pos2){
                    result += "-" + letter2 + pos1;
                    pos1++;
                }
            }else{
                pos1--;
                while (pos1 > 0){
                    result += "-" + letter1 + pos1;
                    pos1--;
                }
                result += "-" + letter2 + pos2;
            }
        }else if(offset <= 2){
            if ((pos1 >= pos2) && pos1!= 1) {
                if ((offset == 1) && pos2 == 1){
                    pos1--;
                    while(pos1 >= pos2){
                        result += "-" + letter1 + pos1;
                        pos1--;
                    }
                    return result + "-" + letter2 + pos2;
                }else if(pos2 == 1){
                    pos1--;
                    while(pos1 > 0){
                        result+= "-" + letter1 + pos1;
                        pos1--;
                    }
                    result += "-A0-" + letter2 + pos2;
                    return result;
                }
                pos1--;
                while (pos1 >= pos2) {
                    result += "-" + letter1 + pos1;
                    if (pos1 == pos2) {
                        break;
                    }
                    pos1--;
                }
                for (point point : arr) {
                    double check = ((double) value1 + (double) value2) / 2;
                    if (check == point.getValue()) {
                        String tempLetter = point.getLetter();
                        result += "-" + tempLetter + pos2;
                    }
                }
                result += "-" + letter2 + pos2;
            } else{
                if ((pos1 < 2) && offset != 1){
                    result += "-A0";
                }else if(pos1 < 2){
                    while (pos1 <= pos2){
                        result += "-" + letter2 + pos1;
                        pos1++;
                    }
                }else{
                    for (point point : arr) {
                        double check = ((double) value1 + (double) value2) / 2;
                        if (check == point.getValue()) {
                            String tempLetter = point.getLetter();
                            result += "-" + tempLetter + pos1;
                        }
                    }
                }
                while (pos1 <= pos2){
                    result += "-" + letter2 + pos1;
                    pos1++;
                }
            }
        }else if((letter1.equals("A") || letter2.equals("A")) && offset >= 6) {
            if ((pos1 >= pos2) && pos1 != 1) {
                if (offset == 6 && pos2 == 1){
                    while (pos1 > 0){
                        result += "-" + letter1 + pos1;
                        pos1--;
                    }
                    result += "-A0-" + letter2 + pos2;
                    return result;
                }else if(pos2 == 1){
                    while (pos1 >= pos2){
                        result += "-" + letter1 + pos1;
                        pos1--;
                    }
                    result += "-" + letter2 + pos2;
                    return result;
                }
                pos1--;
                while (pos1 >= pos2) {
                    result += "-" + letter1 + pos1;
                    if (pos1 == pos2) {
                        break;
                    }
                    pos1--;
                }
                if (offset == 6) {
                    result += "-H" + pos1;
                }
                result += "-" + letter2 + pos1;
            }else{
                if (pos1 < 2 && offset != 7) {
                    result += "-A0";
                    while (pos1 <= pos2){
                        result += "-" + letter2 + pos1;
                        pos1++;
                    }
                    return result;
                }else if (pos1 < 2){
                    while (pos1 <= pos2){
                        result += "-" + letter2 + pos1;
                        pos1++;
                    }
                    return result;
                }
                while (pos1 <= pos2){
                    result += "-" + letter2 + pos1;
                    pos1++;
                }
            }
        }else {
            pos1--;
            while (pos1 > 0){
                result += "-" + letter1 + pos1;
                pos1--;
            }
            result += "-A0";
            pos1++;
            while (pos1 <= pos2){
                result+= "-" + letter2 + pos1;
                pos1++;
            }
        }
        return result;
    }
    static void task3(){
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));
        System.out.println(digitsCount(-32));
    }
    public static int digitsCount(long num){
        if (Math.abs(num) < 10) {
            return 1;
        }
        return 1 + digitsCount(num / 10);
    }

    static void task4(){
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
    }

    public static int totalPoints(String[] list, String key) {
        char[] keySyb = key.toCharArray();
        boolean check;
        int result = 0;
        int size;
        for (String word : list) {
            size = 0;
            char[] tempSyb = Arrays.copyOf(keySyb, keySyb.length);
            char[] wordSyb = word.toCharArray();
            for (char syb : wordSyb) {
                check = false;
                for (int i = 0; i < tempSyb.length; i++) {
                    if (syb == tempSyb[i]) {
                        check = true;
                        size++;
                        tempSyb[i] = '.';
                        break;
                    }
                }
                if (!check){
                    size = 0;
                }
            }
            switch (size){
                case 3:
                    result += 1;
                    break;
                case 4:
                    result += 2;
                    break;
                case 5:
                    result += 3;
                    break;
                case 6:
                    result += 54;
                    break;
            }
        }
        return result;
    }

    static void task5(){
        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(longestRun(new int[]{1, 2, 3, 10, 11, 15}));
        System.out.println(longestRun(new int[]{5, 4, 2, 1}));
        System.out.println(longestRun(new int[]{3, 5, 7, 10, 15}));

    }

    public static int longestRun(int[] arr){
        if (arr.length == 1){
            return 1;
        }
        int counter = 1, tempCounter1 = 1, tempCounter2 = 1;
        for (int i = 1; i < arr.length; i++){
            if ((arr[i] - arr[i - 1]) == 1){
                tempCounter1++;
            }else{
                tempCounter1 = 1;
            }
            if((arr[i] - arr[i - 1]) == -1){
                tempCounter2++;
            }
            else{
                tempCounter2 = 1;
            }
            if (tempCounter1 > counter){
                counter = tempCounter1;
            }
            if (tempCounter2 > counter){
                counter = tempCounter2;
            }
        }
        return counter;
    }

    static void task6(){
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
    }

    public static String takeDownAverage(String[] list){
        double sum = 0, num = 0, average = 0, average2 = 0;
        for(String str : list){
            if (str.contains("%")){
                str = str.replaceAll("%", "");
            }
            sum += Integer.parseInt(str);
            num++;
        }
        average = sum / (num);
        average2 = sum / (num + 1);
        return Math.round(((0.95 * average) - average2) * (num + 1)) + "%";
    }

    static void task7(){
        System.out.println(rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));
        System.out.println(rearrange("the4 t3o man5 Happ1iest of6 no7 birt2hday steel8!"));
        System.out.println(rearrange("is2 Thi1s T4est 3a"));
        System.out.println(rearrange("4of Fo1r pe6ople g3ood th5e the2"));
        System.out.println(rearrange(" "));
    }

    public static String rearrange(String str){
        String[] words = str.split(" ");
        ArrayList<String> sortWords = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < words.length + 1; i++){
            for (int j = 0; j < words.length; j++){
                if (words[j].contains(Integer.toString(i))){
                    words[j] = words[j].replaceFirst(Integer.toString(i), "");
                    sortWords.add(words[j]);
                }
            }
        }
        for (int i = 0; i < sortWords.size(); i++){
            result.append(sortWords.get(i)).append(" ");
        }
        return (sortWords.size() > 1) ? (result.deleteCharAt(result.length() - 1)).toString() : "";
    }

    static void task8(){
        System.out.println(maxPossible(9328, 456));
        System.out.println(maxPossible(523, 76));
        System.out.println(maxPossible(9132, 5564));
        System.out.println(maxPossible(8732, 91255));
    }

    public static int getMaxIndex(int[] arr){
        int result = 0;
        int max = arr[0];
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
                result = i;
            }
        }
        return result;
    }

    public static int maxPossible (int firstNum, int secondNum){
        int[] arr1 = parseNumToArray(firstNum);
        int[] arr2 =  parseNumToArray(secondNum);
        int result = 0;
        int helper = 1;
        for (int i = arr1.length - 1; i >= 0; i--){
            if (arr1[i] <= arr2[getMaxIndex(arr2)]){
                arr1[i] = arr2[getMaxIndex(arr2)];
                arr2[getMaxIndex(arr2)] = -1;
            }
        }
        for(int i = 0; i < arr1.length; i++){
            result += arr1[i] * helper;
            helper *= 10;
        }
        return result;
    }

    static void task10(){
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
        System.out.println(isNew(509));
        System.out.println(isNew(869));
        System.out.println(isNew(4036));
    }

    public static int[] parseNumToArray(int num){
        int[] arr = new int[Integer.toString(num).length()];
        int i = 0;
        while (num > 0){
            arr[i] = num % 10;
            num /= 10;
            i++;
        }
        return arr;
    }

    public static boolean isNew(int number){
        if (Integer.toString(number).length() == 1){
            return true;
        }
        int[] arr = parseNumToArray(number);
        if (arr[arr.length - 2] == 0){
            arr[arr.length - 2] = arr[arr.length - 1];
            arr[arr.length - 1] = 0;
        }
        for (int i = arr.length - 1; i > 0; i--){
            if ((arr[i] > arr[i - 1])){
                return false;
            }
        }
        return true;
    }

    static void task9() throws ParseException {
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
    }

    static class cityTime{

        private String name;
        private double GMT;

        public cityTime(String name, double GMT) {
            this.GMT = GMT;
            this.name = name;
        }

        public double getGMT() {
            return GMT;
        }

        public String getName() {
            return name;
        }

        public void set(String name, double GMT){
            this.name = name;
            this.GMT = GMT;
        }
    }

    public static String timeDifference(String city, String data, String newCity) throws ParseException {
        cityTime[] arr = new cityTime[11];
        data = data.replaceFirst(",", "");
        arr[0] = new cityTime("Los Angeles", -8);
        arr[1] = new cityTime("New York", -5);
        arr[2] = new cityTime("Caracas", -4.5);
        arr[3] = new cityTime("Buenos Aires", -3);
        arr[4] = new cityTime("London", 0);
        arr[5] = new cityTime("Rome", 1);
        arr[6] = new cityTime("Moscow", 3);
        arr[7] = new cityTime("Tehran", 3.5);
        arr[8] = new cityTime("New Delhi", 5.5);
        arr[9] = new cityTime("Beijing", 8);
        arr[10] = new cityTime("Canberra", 10);

        double offset;
        double time1 = 0;
        double time2 = 0;
        for (int i = 0; i <= 10; i++) {
            if (arr[i].getName().equals(city)) {
                time1 = arr[i].getGMT();
            }
            if (arr[i].getName().equals(newCity)) {
                time2 = arr[i].getGMT();
            }
        }
        offset = (time2 - time1) * 3600000;
        SimpleDateFormat parser = new SimpleDateFormat("MMMM dd yyyy HH:mm", Locale.US);
        Date newDate = parser.parse(data);
        newDate.setTime(newDate.getTime() + (int) offset);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d HH:mm");
        return formatter.format(newDate);
    }
}

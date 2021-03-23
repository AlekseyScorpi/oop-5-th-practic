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
        System.out.println(spiderVsFly("A2", "H3"));
        System.out.println(spiderVsFly("B2", "D4"));
        System.out.println(spiderVsFly("B4", "F2"));
        System.out.println(spiderVsFly("H3", "D4"));
    }

    static class branch{

        private String letter;
        private int xCoord;
        private int yCoord;

        public branch(String letter, int xCoord, int yCoord) {
            this.letter = letter;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public int getxCoord() {
            return xCoord;
        }

        public void setxCoord(int xCoord) {
            this.xCoord = xCoord;
        }

        public int getyCoord() {
            return yCoord;
        }

        public void setyCoord(int yCoord) {
            this.yCoord = yCoord;
        }
    }

    public static String spiderVsFly(String startCoord, String finishCoord){
        String result = startCoord;
        branch[] arr = new branch[8];
        arr[0] = new branch("A", 0, 2);
        arr[1] = new branch("B", 1, 1);
        arr[2] = new branch("C", 2, 0);
        arr[3] = new branch("D", 1, -1);
        arr[4] = new branch("E", 0, -2);
        arr[5] = new branch("F", -1, -1);
        arr[6] = new branch("G", -2, 0);
        arr[7] = new branch("H", -1, 1);
        int xCoord1 = 0, yCoord1 = 0;
        int xCoord2 = 0, yCoord2 = 0;
        String letter1 = startCoord.substring(0,1); //парсим строки
        String letter2 = finishCoord.substring(0,1);
        for (branch branch : arr){
            if (letter1.equals(branch.getLetter())){
                xCoord1 = branch.getxCoord();
                yCoord1 = branch.getyCoord();
            }
            if (letter2.equals(branch.getLetter())){
                xCoord2 = branch.getxCoord();
                yCoord2 = branch.getyCoord();
            }
        }
        int offset = 0;
        if (Math.abs(xCoord1 - xCoord2) >= Math.abs(yCoord1 - yCoord2)){
            offset = Math.abs(xCoord1 - xCoord2);
        }else {
            offset = Math.abs(yCoord1 - yCoord2);
        }
        if (((xCoord1 + xCoord2) == 0) && ((yCoord1 + yCoord2) == 0)){
            offset = 4;
        }
        int pos1 = startCoord.charAt(1) - '0';
        int pos2 = finishCoord.charAt(1) - '0';
        if (letter1.equals(letter2)){ //если совпадают буквы(одна и та же ветка)
            if (pos1 >= pos2){
                for (int i = pos1 - 1; i >= pos2; i--){
                    result += "-" + letter1 + i;
                }
            }else{
                for (int i = pos1 + 1; i <= pos2; i++){
                    result += "-" + letter1 + i;
                }
            }
        }else if((pos1 == 0) || (pos2 == 0)){ // ситуация когда одна из координат это центр
            if (pos1 == 0){                   // т.е просто спуск или подъём по линии
                pos1++;
                while (pos1 <= pos2){
                    result += "-" + letter2 + pos1;
                    pos1++;
                }
            }else {
                pos1--;
                while (pos1 > 0) {
                    result += "-" + letter1 + pos1;
                    pos1--;
                }
                result += "-" + letter2 + pos2;
            }
        }else if(offset <= 2){ //если смещение позволяет перемещаться по бокам
            if (offset == 1){ //значит мы очень близко
                if (pos1 >= pos2){ //спускаемся до pos2 и поворачиваем
                    pos1--;
                    while (pos1 >= pos2){
                        result += "-" + letter1 + pos1;
                        pos1--;
                    }
                    result += "-" + letter2 + pos2;
                }else{ //поворачиваем и поднимаемся
                    while (pos1 <= pos2){
                        result += "-" + letter2 + pos1;
                        pos1++;
                    }
                }
            }else{ //смещение точно 2
                if (pos1 >= pos2){
                    if (pos2 == 1){ // идём через центр
                        pos1--;
                        while (pos1 > 0){
                            result += "-" + letter1 + pos1;
                            pos1--;
                        }
                        result += "-A0-" + letter2 + pos2;
                    }else{ //спускаемся до pos2 и два раза поворачиваем
                        pos1--;
                        while (pos1 >= pos2){
                            result += "-" + letter1 + pos1;
                            pos1--;
                        }
                        for (branch branch : arr){ //поиск ветки, которая между
                            if(xCoord1 == 0 || yCoord1 == 0 || xCoord2 == 0 || yCoord2 == 0){ //если это узловые ветки
                                if ((branch.getxCoord() == ((xCoord1 + xCoord2) / 2)) &&
                                        (branch.getyCoord() == ((yCoord1 + yCoord2) / 2))){
                                    result += "-" + branch.getLetter() + pos2;
                                    break;
                                }
                            }else{ //если диагональные ветки
                                if ((branch.getxCoord() == (xCoord1 + xCoord2)) &&
                                        (branch.getyCoord() == (yCoord1 + yCoord2))){
                                    result += "-" + branch.getLetter() + pos2;
                                    break;
                                }
                            }
                        }
                        result += "-" + letter2 + pos2;
                    }
                }else{
                    if (pos1 == 1){ //спускаемся и поднимаемся по 2-ой ветке
                        result += "-A0";
                    }else{ //2 раза поворачиваем и поднимаемся по 2-ой ветке
                        for (branch branch : arr){
                            if(xCoord1 == 0 || yCoord1 == 0 || xCoord2 == 0 || yCoord2 == 0){ //если узловые ветки
                                if ((branch.getxCoord() == ((xCoord1 + xCoord2) / 2)) &&
                                        (branch.getyCoord() == ((yCoord1 + yCoord2) / 2))){
                                    result += "-" + branch.getLetter() + pos1;
                                    break;
                                }
                            }else{ //если диагональные ветки
                                if ((branch.getxCoord() == (xCoord1 + xCoord2)) &&
                                        (branch.getyCoord() == (yCoord1 + yCoord2))){
                                    result += "-" + branch.getLetter() + pos1;
                                    break;
                                }
                            }
                        }
                    }
                    while (pos1 <= pos2) {
                        result += "-" + letter2 + pos1;
                        pos1++;
                    }
                }
            }
        }else { //тупой проход через центр если смещение слишком велико
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

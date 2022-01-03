import java.io.*;
import java.util.InputMismatchException;

public class Lab9 {
    public static int lineCount = 0;
    public static int index = 0;
    public static void main(String[] args) {
        for(int i = 0; i<100000; i++){
            String inputFileDirectory = "/home/pooh/Documents/OOP/LAB9/src/input.txt";
            String outputFileDirectory = "/home/pooh/Documents/OOP/LAB9/src/output.txt";
            readAndWrite(inputFileDirectory,outputFileDirectory);
        }
    }
    public static void readAndWrite(String inputFileDirectory , String outputFileDirectory){
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileDirectory));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileDirectory))
        ) {
            while ((line = br.readLine()) != null) {

                if (line.length() == 0) continue;
                lineCount++;
                try {
                    String outLine = line + " = " + calculator(line,lineCount) + "\n";
                    System.out.print(outLine);
                    bw.append(outLine);
                }catch (ArithmeticException | InputMismatchException e){
                    e.printStackTrace();
                    System.out.println("Invalid input line: " + lineCount);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double calculator(String l, int lineCount) throws ArithmeticException,InputMismatchException{
        index = 0;
        if(l.length() == 0) throw new InputMismatchException("Empty line: " + lineCount);
        String noSpaceLine = l.replaceAll("\\s+","");
        double s1 = findOperand(noSpaceLine);
        double s2 = 0;
        char operator;
        while (index < noSpaceLine.length()){
            operator = findOperater(noSpaceLine);
            s2 = findOperand(noSpaceLine);
            s1 = calculate(s1,s2,operator);
        }
        if(!Double.isFinite(s1)) throw new ArithmeticException("Line: " + lineCount + " is infinity may caused by divide by zero.");

        return s1;
    }

    public static double calculate (double n1, double n2, char operator) throws InputMismatchException{
        if(operator == '+') return n1 + n2;
        if(operator == '-') return n1 - n2;
        if(operator == '*') return n1 * n2;
        if(operator == '/') return n1 / n2;
        throw new InputMismatchException("Invalid input line: " + lineCount);
    }
    public static double findOperand(String t) throws InputMismatchException{
        String tmp = "";
        if(!isOperand(t.charAt(index))){
            throw new InputMismatchException("Mismatch operator line: " + lineCount);
        }

        while (isEnd(t) && isOperand(t.charAt(index))){
            tmp = tmp + t.charAt(index);
            index = index + 1;
        }
        return Double.parseDouble(tmp);
    }

    public static char findOperater(String t) throws InputMismatchException{
        if(index == t.length()-1){
            throw new InputMismatchException("Invalid input line: " + lineCount);
        }
        if(isOperator(t.charAt(index))){
            index = index + 1;
            return t.charAt(index - 1);
        }else{
            throw new InputMismatchException("Invalid input line: " + lineCount);
        }
    }

    public static boolean isOperand(char c){
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static boolean isEnd(String t){
        return index != t.length();
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Assembler {
    static REG r0 = new REG(0);
    static REG r1 = new REG(1);
    static REG r2 = new REG(2);
    static REG r3 = new REG(3);
    static REG r4 = new REG(4);
    static REG r5 = new REG(5);
    static REG bp = new REG(6);
    static REG sp = new REG(7);

    static OP MOV = new OP(0);
    static OP XOR = new OP(1);
    static OP ADD = new OP(2);
    static OP SUB = new OP(3);
    static OP SHL = new OP(4);
    static OP SHR = new OP(5);
    static OP NOT = new OP(6);
    static OP OR = new OP(7);
    static OP JZ = new OP(8);
    static OP JS = new OP(9);
    static OP JMP = new OP(10);
    static OP WRT = new OP(11);
    static OP READ = new OP(12);
    static OP CMP = new OP(13);
    static OP JL = new OP(14);
    static HashMap<String, Integer> labels = new HashMap<String, Integer>();
    static List<instraction> instractions = new ArrayList<instraction>();
    static int funcCounter = 0;

    static StringBuilder BIN = new StringBuilder("000000000000000000000000111100001111111111111111");
    static int addressCounter = 3;




    public static String BinToHex(String Bin){
        int currentVal;
        StringBuilder newString = new StringBuilder();
        int h = 0;


        for (int i = 0; i < Bin.length(); i+=4) {
            h++;
            try {
                currentVal = Integer.parseInt(Bin.substring(i, i+4), 2);
                if (currentVal<10){
                    newString.append(currentVal);
                }
                else {
                    switch (currentVal){
                        case 10 -> newString.append("a");
                        case 11 -> newString.append("b");
                        case 12 -> newString.append("c");
                        case 13 -> newString.append("d");
                        case 14 -> newString.append("e");
                        case 15 -> newString.append("f");
                    }

                }
            }
            catch (Exception e){
                System.out.println("str_len"+newString.length());
                System.out.println("i"+i);
                System.out.println("h"+h);
            }

        }
        return newString.toString();
    }

    private static String bitsToHexConversion(String bitStream){
        int byteLength = 4;
        int bitStartPos = 0, bitPos = 0;
        String hexString = "";
        int sum = 0;

        // pad '0' to make input bit stream multiple of 4

        if(bitStream.length()%4 !=0){
            int tempCnt = 0;
            int tempBit = bitStream.length() % 4;
            while(tempCnt < (byteLength - tempBit)){
                bitStream = "0" + bitStream;
                tempCnt++;
            }
        }

        // Group 4 bits, and find Hex equivalent

        while(bitStartPos < bitStream.length()){
            while(bitPos < byteLength){
                sum = (int) (sum + Integer.parseInt("" + bitStream.charAt(bitStream.length()- bitStartPos -1)) * Math.pow(2, bitPos)) ;
                bitPos++;
                bitStartPos++;
            }
            if(sum < 10)
                hexString = Integer.toString(sum) + hexString;
            else
                hexString = (char) (sum + 55) + hexString;

            bitPos = 0;
            sum = 0;
        }
        return hexString;


    }
    public static void OpBase(OP op, REG reg1, Object arg2) {
        addressCounter += arg2 instanceof REG ? 1 : 2;
        instractions.add(new instraction(op, reg1, arg2));
    }

    public Assembler MOV(REG arg1, Object arg2) {
        OpBase(MOV, arg1, arg2);
        return this;
    }
    public Assembler XOR(REG arg1, Object arg2) {
        OpBase(XOR, arg1, arg2);
        return this;
    }
    public Assembler ADD(REG arg1, Object arg2) {
        OpBase(ADD, arg1, arg2);
        return this;
    }
    public Assembler SUB(REG arg1, Object arg2) {
        OpBase(SUB, arg1, arg2);
        return this;
    }
    public Assembler SHL(REG arg1, int amount) {
        for (int i = 0; i < amount; i++) OpBase(SHL, arg1, r0);
        return this;
    }
    public Assembler SHR(REG arg1, int amount) {
        for (int i = 0; i < amount; i++) OpBase(SHR, arg1, r0);
        return this;
    }
    public Assembler NOT(REG arg1) {
        OpBase(NOT, arg1, null);
        return this;
    }
    public Assembler OR(REG arg1, Object arg2) {
        OpBase(OR, arg1, arg2);
        return this;
    }
    public Assembler JZ(String label) {
        OpBase(JZ, r0, label);
        return this;
    }
    public Assembler JS(String label) {
        OpBase(JS, r0, label);
        return this;
    }
    public Assembler JMP(Object label) {
        OpBase(JMP, r0, label);
        return this;
    }
    public Assembler WRT(REG val, Object address) {
        OpBase(WRT, val, address);
        return this;
    }
    public Assembler READ(REG val, Object address) {
        OpBase(READ, val, address);
        return this;
    }
    public Assembler CMP(REG arg1, Object arg2) {
        OpBase(CMP, arg1, arg2);
        return this;
    }
    public Assembler JL(String label) {
        OpBase(JL, r0, label);
        return this;
    }
    public Assembler POP(REG arg1){
        ADD(sp, 1);
        READ(arg1, sp);
        return this;
    }
    public Assembler PUSH(REG arg1){
        WRT(arg1, sp);
        SUB(sp, 1);
        return this;
    }
    public Assembler CALL(String label){
        String Label = ""+funcCounter;
        System.out.println(""+funcCounter);
        funcCounter++;
        WRT(r0, r5);
        MOV(r0, Label);
        PUSH(r0);
        READ(r0, r5);
        JMP(label);
        Label(Label);
        return this;
    }
    public Assembler RET(){
        PUSH(r0);
        SUB(sp, 1);
        READ(r0, sp);
        JMP(r0);
        ADD(sp, 1);
        POP(r0);
        POP(r0);
        return this;
    }
    public Assembler Label(String name){
        labels.put(name, addressCounter);
        return this;
    }


    public void build(){

        System.out.println(labels);
        System.out.println(instractions.size());
        System.out.println(instractions);
        String str = "";
        BIN.replace(0, BIN.length(),bitsToHexConversion(BIN.toString()));
        for (int i = 0; i<instractions.size(); i++) {

            System.out.println(i);
            System.out.println(instractions.get(i));
            try {
                if (Objects.equals(instractions.get(i).op.getId(), new OP(10).getId())){
                    System.out.println("hi");
                }
                BIN.append(bitsToHexConversion(instractions.get(i).runInstruction(labels)));
            }
            catch (Exception e){
                System.out.println(instractions.get(i).op.getId());
                System.out.println(instractions.get(i).arg1.getId());
                System.out.println(instractions.get(i).arg2);
                System.out.println("gggggggggggggggggggggggggggggg");
            }
        }
        System.out.println(BIN.length());
        System.out.println(BIN);
        System.out.println(str);
//        System.out.println(BinToHex(BIN.toString()));
        //System.out.println(bitsToHexConversion(BIN.toString()));
    }




}

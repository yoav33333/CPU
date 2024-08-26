import java.util.HashMap;

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

    static StringBuilder BIN = new StringBuilder("000000000000000000000000111100001111111111111111");
    static int addressCounter = 3;

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
        if (arg2 instanceof REG) {
            BIN.append("00000" + ((REG) arg2).getId() + "0" + reg1.getId() + op.getId());
            addressCounter++;
        } else if (arg2 instanceof Integer) {
            String value = Integer.toBinaryString((Integer) arg2);
            StringBuilder sb = new StringBuilder("0000000000000000");
            sb.replace(sb.length() - value.length(), sb.length(), value);
            value = sb.toString();
            System.out.println("00000000" + "1" + reg1.getId() + op.getId()+value);
            BIN.append("00000000" + "1" + reg1.getId() + op.getId()+value);
            addressCounter += 2;
        }
        else {
            throw new IllegalArgumentException("Invalid argument type for " + op.getId() + " operation");
        }
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
        OpBase(JZ, r0, labels.get(label));
        return this;
    }
    public Assembler JS(String label) {
        OpBase(JS, r0, labels.get(label));
        return this;
    }
    public Assembler JMP(String label) {
        OpBase(JMP, r0, labels.get(label));
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
        OpBase(JL, r0, labels.get(label));
        return this;
    }
    public Assembler POP(REG arg1){
        ADD(sp, 4);
        READ(arg1, sp);
        return this;
    }
    public Assembler PUSH(REG arg1){
        WRT(arg1, sp);
        SUB(sp, 4);
        return this;
    }
    public Assembler Label(String name){
        labels.put(name, addressCounter);
        return this;
    }
    public void build(){
        System.out.println(bitsToHexConversion(BIN.toString()));
    }




}

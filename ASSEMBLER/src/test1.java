public class test1 {
    static REG r0 = new REG(0);
    static REG r1 = new REG(1);
    static REG r2 = new REG(2);
    static REG r3 = new REG(3);
    static REG r4 = new REG(4);
    static REG r5 = new REG(5);
    static REG bp = new REG(6);
    static REG sp = new REG(7);


    public static void main(String[] args) {
        // r0 -> n
        // r1 -> val1
        // r2 -> val2
        // r3 -> temp
        new Assembler()
                .MOV(r1, 6)
                .MOV(r2, 0)
                .MOV(r3, 1)
                .Label("loop")
                .CMP(r1, 0)
                .JZ("end")
                .MOV(r4, r2)
                .MOV(r2, r3)
                .ADD(r3, r4)
                .SUB(r1, 1)
                .JMP("loop")
                .Label("end")
                .build();
        fib(6);



    }
    public static void fib(int n) {
        int val1 = 0;
        int val2 = 1;

        while (n > 0) {
            int temp = val1;
            val1 = val2;
            val2 += temp;
            n--;
        }
        System.out.println(val1);
    }
}

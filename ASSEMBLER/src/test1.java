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
                .MOV(r1, 24)
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
                .OUT(r2)
                .build("test1");

        // rbp -> bp
        // rsp -> sp
        // rbx -> r1
        // rdi -> r2
        // rax -> r3
//        new Assembler()
//                .MOV(r2, 6)
//                .CALL("fib")
//                .OUT(r3)
//                .JMP("f")
//                .Label("fib")
//                .PUSH(bp)
//                .MOV(bp, sp)
//                .PUSH(r1)
//                .PUSH(r2)
//                .MOV(r3, r2)
//                .CMP(r3, 2)
//                .JZ("done")
//                .SUB(r3, 1)
//                .MOV(r2, r3)
//                .CALL("fib")
//                .MOV(r1, r3)
//                .MOV(r3, r2)
//                .SUB(r3, 1)
//                .MOV(r2, r3)
//                .CALL("fib")
//                .ADD(r3, r1)
//                .Label("done")
//                .POP(r2)
//                .POP(r1)
//                .POP(bp)
//                .RET()
//                .Label("f")
//                .build("rec");


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

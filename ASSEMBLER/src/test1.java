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
        new Assembler()
                .MOV(r0, 5)
                .CALL("fib")
                .JMP("f")
                .Label("fib")
                .PUSH(bp)
                .MOV(bp, sp)
                .SUB(r0, 1)
                .CMP(r0, 0)
                .JZ("f")
                .JMP("fib")
                .POP(bp)
                .RET()
                .Label("f")
                .build();



    }
}

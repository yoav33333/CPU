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
        new Assembler().CALL("i").JMP("done").Label("i")
                .MOV(r0, 40).RET().Label("done").build();
    }
}

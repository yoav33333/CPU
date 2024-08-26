import java.util.HashMap;

public class instraction {
    public OP op;
    public REG arg1;
    Object arg2;


    public instraction(OP op, REG arg1, Object arg2){
           this.op = op;
           this.arg1 = arg1;
           this.arg2 = arg2;
    }

    public String runInstruction(HashMap<String, Integer> map){
     //   try {
            if (arg2 instanceof REG) {
                return ("00000" + ((REG) arg2).getId() + "0" + arg1.getId() + op.getId());
            } else if (arg2 instanceof Integer) {
                String value = Integer.toBinaryString((Integer) arg2);
                StringBuilder sb = new StringBuilder("0000000000000000");
                sb.replace(sb.length() - value.length(), sb.length(), value);
                value = sb.toString();
                return ("00000000" + "1" + arg1.getId() + op.getId()+value);
            }
            else if (arg2 instanceof String){
                this.arg2 = map.get(arg2);
                return runInstruction(map);
            }
       // }
//        catch (Exception e){
//            System.out.println(this.op);
//            System.out.println(this.arg1);
//            System.out.println(this.arg2);
//        }
        return null;
    }
}

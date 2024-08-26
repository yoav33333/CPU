public class OP {
    String id;
    static String base = "0000";
    public OP(int id) {
        this.id = Integer.toBinaryString(id);
        StringBuilder sb = new StringBuilder(base);
        sb.replace(sb.length()-this.id.length(), sb.length(), this.id);
        this.id = sb.toString();
    }

    public String getId() {
        return id;
    }
}

public class REG {
    String id;
    static String base = "000";
    public REG(int id) {
        this.id = Integer.toBinaryString(id);
        StringBuilder sb = new StringBuilder(base);
        sb.replace(sb.length()-this.id.length(), sb.length(), this.id);
        this.id = sb.toString();
    }

    public String getId() {
        return id;
    }
}

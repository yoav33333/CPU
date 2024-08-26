public class IMM {
    static String value;
    static String base = "0000000000000000";
    public IMM(int value) {
        this.value = Integer.toBinaryString(value);
        StringBuilder sb = new StringBuilder(base);
        sb.replace(sb.length() - this.value.length(), sb.length(), this.value);
        this.value = sb.toString();
    }

    public static String getValue() {
        return value;
    }
}

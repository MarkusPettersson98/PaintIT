package Canvas;

public class Color {
    private final byte[] rgb = new byte[3];
    private final byte r = 0, g = 1, b = 2;

    public Color(byte r, byte g, byte b) {
        rgb[this.r] = r;
        rgb[this.g] = g;
        rgb[this.b] = b;
    }

    public byte[] getRGBValue() { return rgb; }

    public byte getRValue() { return rgb[r]; }
    public byte getGValue() { return rgb[g]; }
    public byte getBValue() { return rgb[b]; }

    public void setRValue(byte r) { rgb[this.r] = r; }
    public void setGValue(byte g) { rgb[this.g] = g; }
    public void setBValue(byte b) { rgb[this.b] = b; }
}

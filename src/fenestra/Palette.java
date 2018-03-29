package fenestra;
import java.awt.*;

public class Palette {
    private static Palette paletteInstance;
    
    public static Palette getPalette() {
        if(paletteInstance == null)
            paletteInstance = new Palette();
        return paletteInstance;
    }

    public final static Color mistyRose = new Color(253, 232, 233);
    public final static Color cameoPink = new Color(227, 186, 198);
    public final static Color pastelPurple = new Color(188, 158, 193);
    public final static Color cadet = new Color(89, 100, 117);
    public final static Color darkGunmetal = new Color(31, 34, 50);
    public final static Color deepTaupe = new Color(113, 91, 100);
    public final static Color halayaUbe = new Color(105, 56, 92);
    public final static Color middleRedPurple = new Color(35, 12, 51);
    public final static Color paynesGrey = new Color(84, 106, 123);
    public final static Color cyberGrape = new Color(83, 58, 123);
    public final static Color middleYellowRed = new Color(234, 186, 107);
    public final static Color vividCerulean = new Color(0, 171, 231);
    public final static Color desertSand = new Color(234, 210, 172);
    public final static Color aztecGold = new Color(192, 152, 88);

    private Palette() {

    }

    public Color[] getColors() {
        Color[] colors = new Color[9];
        colors[0] = mistyRose;
        colors[1] = cameoPink;
        colors[2] = pastelPurple;
        colors[3] = cadet;
        colors[4] = darkGunmetal;
        colors[5] = deepTaupe;
        colors[6] = halayaUbe;
        colors[7] = middleRedPurple;
        colors[8] = paynesGrey;
        return colors;
    }

    public int getColorLength() {
        return getColors().length;
    }

}

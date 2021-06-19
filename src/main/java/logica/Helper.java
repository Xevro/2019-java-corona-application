package logica;

import java.text.DecimalFormat;

/**
 *
 * @author louisdhont
 */
public class Helper {

    private static DecimalFormat formatter = new DecimalFormat("#,###;-#,###");

    public static String getalFormatten(int getal) {
        return formatter.format(getal);
    }
}

/**
 * Author: Tsiskomichelis Stelios
 * Created On: 28/1/2019
 * Project: barista-plugin
 * <p>
 * ClassName: WmGetSize
 * Role:
 * Description:
 */
package gr.aueb.android.barista.core.model;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class WmGetSize extends AbstractAdbCommand {

    private int width;
    private int height;

    public WmGetSize(String sessionToken) {
        super(sessionToken);
    }

    @Override
    public String getCommandString() {
        return "shell wm size";
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        resultLines.filter(new Predicate<String>() {
            @Override
            public boolean test(String line) {
                if (line.matches("Override size:(.*)")) {
                    return true;
                }
                return false;
            }
        }).forEach(new Consumer<String>() {
                    @Override
                    public void accept(String line) {
                        String sizeString = line.split(" ")[2];
                        //exract width (first number, left of x)
                        width = Integer.parseInt(sizeString.split("x")[0]);
                        //exract width (second number, right of x)
                        height = Integer.parseInt(sizeString.split("x")[1]);
                    }
                });
    }
}

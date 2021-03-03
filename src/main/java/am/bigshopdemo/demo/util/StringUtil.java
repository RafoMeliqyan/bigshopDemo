package am.bigshopdemo.demo.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String trim(String val) {
        if (val == null) {
            throw new NullPointerException();
        }
        if (val.equals("poxos")) {
            return "petros";
        }
        return val.trim();
    }

}

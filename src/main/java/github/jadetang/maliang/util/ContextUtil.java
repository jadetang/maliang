package github.jadetang.maliang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Tang Sicheng
 */
public class ContextUtil {

    public static final Logger log = LoggerFactory.getLogger(ContextUtil.class);


    private ContextUtil(){}

    public static String upperFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String lowerFirstLetter(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static String onlyFirstLetterIsUp(String str) {
        return upperFirstLetter(str.toLowerCase());
    }

    /**
     * Velocity context seems do not support static method
     * @return
     */
    public static ContextUtilWrapper newWrapper(){
        return new ContextUtilWrapper();
    }

    public static class ContextUtilWrapper {
        public ContextUtilWrapper() {
        }

        public String upperFirstLetter(String str) {
            return ContextUtil.upperFirstLetter(str);
        }

        public static String lowerFirstLetter(String str) {
            return ContextUtil.lowerFirstLetter(str);
        }

        public static String onlyFirstLetterIsUp(String str) {
            return ContextUtil.onlyFirstLetterIsUp(str);
        }

    }
}



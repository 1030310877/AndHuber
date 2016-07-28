package joe.andhuber.utils;

/**
 * Description
 * Created by chenqiao on 2016/7/27.
 */
public class FileTypeUtil {

    private static final String[] PICTURE_TYPE = {"jpg", "bmp", "jpeg", "png", "gif"};
    private static final String[] CODE_TYPE = {"java", "jsp", "c", "cpp", "xml", "gradle", "php", "html", "htm", "asp",
            "h", "m", "mm", "js", "py", "cs", "aspx", "rb", "json", "sql",
            "smali", "sqf", "stan", "ini", "lsl", "lua", "kotlin", "less", "go", "glsl",
            "elm", "erlang", "gams", "gauss", "golo", "nginx", "matlab", "r", "scala", "scheme",
            "zephir", "xl", "vhdl", "vim", "md"};

    public static boolean isPicture(String fileName) {
        String extension = FileUtils.getFileExtension(fileName).toLowerCase();
        for (String s : PICTURE_TYPE) {
            if (extension.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCode(String fileName) {
        String extension = FileUtils.getFileExtension(fileName).toLowerCase();
        for (String s : CODE_TYPE) {
            if (extension.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVoice(String fileName) {
        return false;
    }
}

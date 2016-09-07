package github.cjj.ecusthelper.util.util;

/**
 * Created on 2016/8/30
 *
 * @author chenjj2048
 */
public class Objects {
    /**
     * 保证 API < 19 时同样可以使用
     * Returns {@code o} if non-null, or throws {@code NullPointerException}.
     */
    public static <T> T requireNonNull(T o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return o;
    }

    /**
     * 保证 API < 19 时同样可以使用
     * Returns {@code o} if non-null, or throws {@code NullPointerException}
     * with the given detail message.
     */
    public static <T> T requireNonNull(T o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }
}

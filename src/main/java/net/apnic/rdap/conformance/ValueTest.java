package net.apnic.rdap.conformance;

/**
 * <p>ValueTest interface.</p>
 *
 * A ValueTest tests an arbitrary object.
 *
 * @author Tom Harrison <tomh@apnic.net>
 * @version 0.4-SNAPSHOT
 */
public interface ValueTest {
    /**
     * <p>run.</p>
     *
     * @param context a {@link net.apnic.rdap.conformance.Context} object.
     * @param proto a {@link net.apnic.rdap.conformance.Result} object.
     * @param content a {@link java.lang.Object} object.
     * @return a boolean.
     */
    boolean run(Context context, Result proto, Object content);
}

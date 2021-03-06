package net.apnic.rdap.conformance.attributetest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

import net.apnic.rdap.conformance.Result;
import net.apnic.rdap.conformance.Context;
import net.apnic.rdap.conformance.AttributeTest;

/**
 * <p>Status class.</p>
 *
 * @author Tom Harrison <tomh@apnic.net>
 * @version 0.4-SNAPSHOT
 */
public final class Status implements AttributeTest {
    private static final Set<String> STATUSES =
        Sets.newHashSet("validated",
                        "renew prohibited",
                        "update prohibited",
                        "transfer prohibited",
                        "delete prohibited",
                        "proxy",
                        "private",
                        "redacted",
                        "obscured",
                        "associated",
                        "active",
                        "inactive",
                        "locked",
                        "pending create",
                        "pending renew",
                        "pending transfer",
                        "pending update",
                        "pending delete");

    /**
     * <p>Constructor for Status.</p>
     */
    public Status() { }

    /** {@inheritDoc} */
    public boolean run(final Context context, final Result proto,
                       final Map<String, Object> data) {
        List<Result> results = context.getResults();

        Result nr = new Result(proto);
        nr.setCode("content");
        nr.addNode("status");
        nr.setDocument("rfc7483");
        nr.setReference("4.6");

        Result nr1 = new Result(nr);
        nr1.setInfo("present");

        Object value = data.get("status");
        if (value == null) {
            nr1.setStatus(Result.Status.Notification);
            nr1.setInfo("not present");
            results.add(nr1);
            return false;
        } else {
            nr1.setStatus(Result.Status.Success);
            results.add(nr1);
        }

        Result nr2 = new Result(nr);
        nr2.setInfo("is an array");

        List<Object> statusEntries;
        try {
            statusEntries = (List<Object>) value;
        } catch (ClassCastException e) {
            nr2.setStatus(Result.Status.Failure);
            nr2.setInfo("is not an array");
            results.add(nr2);
            return false;
        }

        nr2.setStatus(Result.Status.Success);
        results.add(nr2);

        boolean success = true;
        int i = 0;
        for (Object s : statusEntries) {
            Result r2 = new Result(nr);
            r2.addNode(Integer.toString(i++));
            r2.setReference("11.2.2");
            if (!STATUSES.contains((String) s)) {
                r2.setStatus(Result.Status.Failure);
                r2.setInfo("invalid: " + ((String) s));
                success = false;
            } else {
                r2.setStatus(Result.Status.Success);
                r2.setInfo("valid");
            }
            results.add(r2);
        }

        return success;
    }

    /**
     * <p>getKnownAttributes.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    public Set<String> getKnownAttributes() {
        return Sets.newHashSet("status");
    }
}

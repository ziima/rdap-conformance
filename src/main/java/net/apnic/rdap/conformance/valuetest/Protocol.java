package net.apnic.rdap.conformance.valuetest;

import net.apnic.rdap.conformance.Result;
import net.apnic.rdap.conformance.Context;
import net.apnic.rdap.conformance.ValueTest;

public class Protocol implements ValueTest
{
    public Protocol() { }

    public boolean run(Context context, Result proto,
                       Object arg_data)
    {
        Integer value = Utils.castToInteger(arg_data);

        Result nr = new Result(proto);
        nr.setDetails((value != null), "is integer", "not integer");
        context.addResult(nr);

        if (value != null) {
            Result cvr = new Result(proto);
            boolean res = cvr.setDetails((value == 3), "valid", "invalid");
            context.addResult(cvr);
            return res;
        } else {
            return false;
        }
    }
}

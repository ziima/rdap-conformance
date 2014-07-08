package net.apnic.rdap.conformance.attributetest;

import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.apnic.rdap.conformance.Utils;
import net.apnic.rdap.conformance.Result;
import net.apnic.rdap.conformance.Result.Status;
import net.apnic.rdap.conformance.Context;
import net.apnic.rdap.conformance.AttributeTest;
import net.apnic.rdap.conformance.valuetest.KeyTag;
import net.apnic.rdap.conformance.valuetest.Algorithm;
import net.apnic.rdap.conformance.valuetest.Digest;
import net.apnic.rdap.conformance.valuetest.DigestType;

public class DsData implements AttributeTest
{
    private Set<String> known_attributes = new HashSet<String>();

    public DsData() {}

    public boolean run(Context context, Result proto,
                       Map<String, Object> data)
    {
        return Utils.runTestList(
            context, proto, data, known_attributes, true,
            Arrays.asList(
                new ScalarAttribute("keyTag", new KeyTag()),
                new ScalarAttribute("algorithm", new Algorithm()),
                new ScalarAttribute("digest", new Digest()),
                new ScalarAttribute("digestType", new DigestType()),
                new Events(),
                new Links()
            )
        );
    }

    public Set<String> getKnownAttributes()
    {
        return known_attributes;
    }
}
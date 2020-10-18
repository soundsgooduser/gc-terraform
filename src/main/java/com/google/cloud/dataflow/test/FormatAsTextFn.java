package com.google.cloud.dataflow.test;

import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatAsTextFn extends SimpleFunction<KV<String, Long>, String> {

  private static final Logger LOG = LoggerFactory.getLogger(FormatAsTextFn.class);

  @Override
  public String apply(KV<String, Long> input) {
    String result = input.getKey() + ": " + input.getValue();
    LOG.info("format as text: " + result);
    return result;
  }
}

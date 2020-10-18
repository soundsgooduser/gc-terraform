package com.google.cloud.dataflow.test;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractWordsFn extends DoFn<String, String> {

  private static final Logger LOG = LoggerFactory.getLogger(ExtractWordsFn.class);

  @ProcessElement
  public void processElement(ProcessContext c) {
    LOG.info("process element" + c.element());
    // Split the line into words.
    String[] words = c.element().split("[^a-zA-Z']+");
    LOG.info("words: " + Arrays.stream(words).collect(Collectors.toList()));
    // Output each word encountered into the output PCollection.
    for (String word : words) {
      if (!word.isEmpty()) {
        c.output(word);
      }
    }
  }
}

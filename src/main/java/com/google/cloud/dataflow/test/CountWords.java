package com.google.cloud.dataflow.test;

import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountWords extends PTransform<PCollection<String>, PCollection<KV<String, Long>>> {

  private static final Logger LOG = LoggerFactory.getLogger(CountWords.class);

  @Override
  public PCollection<KV<String, Long>> expand(PCollection<String> lines) {
    LOG.info("start count words: " + lines.toString());
    // Convert lines of text into individual words.
    PCollection<String> words = lines.apply(ParDo.of(new ExtractWordsFn()));
    // Count the number of times each word occurs.
    PCollection<KV<String, Long>> wordCounts = words.apply(Count.<String>perElement());
    return wordCounts;
  }
}

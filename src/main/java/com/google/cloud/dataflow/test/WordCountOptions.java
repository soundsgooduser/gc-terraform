package com.google.cloud.dataflow.test;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface WordCountOptions extends PipelineOptions {

  @Description("Path of the file to read from")
  ValueProvider<String> getInputFile();

  void setInputFile(ValueProvider<String> value);

  @Description("Path of the file to write to")
  ValueProvider<String> getOutput();

  void setOutput(ValueProvider<String> value);
}

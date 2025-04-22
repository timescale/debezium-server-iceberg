package io.debezium.server.iceberg.tableoperator;

import org.apache.iceberg.FileFormat;
import org.apache.iceberg.PartitionKey;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.Schema;
import org.apache.iceberg.data.InternalRecordWrapper;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.io.FileAppenderFactory;
import org.apache.iceberg.io.FileIO;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.io.PartitionedWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartitionedAppendWriter extends PartitionedWriter<Record> {
  private final PartitionKey partitionKey;
  final InternalRecordWrapper wrapper;
  private static final Logger LOGGER = LoggerFactory.getLogger(PartitionedAppendWriter.class);

  public PartitionedAppendWriter(PartitionSpec spec, FileFormat format,
                                 FileAppenderFactory<Record> appenderFactory,
                                 OutputFileFactory fileFactory, FileIO io, long targetFileSize,
                                 Schema schema) {
    super(spec, format, appenderFactory, fileFactory, io, targetFileSize);
    this.partitionKey = new PartitionKey(spec, schema);
    LOGGER.info("HERE in constructor part-key is {}", this.partitionKey);
    this.wrapper = new InternalRecordWrapper(schema.asStruct());
  }

  @Override
  protected PartitionKey partition(Record row) {
    partitionKey.partition(wrapper.wrap(row));
    LOGGER.info("HERE part-key is {}", partitionKey);
    return partitionKey;
  }
}

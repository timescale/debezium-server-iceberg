package io.debezium.server.iceberg;

import org.apache.iceberg.CatalogUtil;
import org.apache.iceberg.catalog.Catalog;
import org.junit.jupiter.api.Test;

import java.util.Map;

class S3TablesCatalogTest {
  @Test
  void testS3TablesCatalogConstructor() {
    Catalog catalog = CatalogUtil.buildIcebergCatalog("iceberg", Map.of(
            "catalog-impl", "software.amazon.s3tables.iceberg.S3TablesCatalog",
            "warehouse", "arn:aws:s3tables:us-east-1:012345677901:bucket/iceberg-table-bucket",
            "client.region", "us-east-1"
    ), null);
  }
}

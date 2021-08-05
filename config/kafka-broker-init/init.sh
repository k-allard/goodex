#!/bin/sh -x

set -e

/bin/kafka-topics --create --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --replication-factor ${KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR} \
  --partitions 1 --topic ${TOPIC}

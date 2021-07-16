#!/bin/sh -x

set -e

/bin/kafka-topics --create --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --replication-factor ${KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR} \
  --partitions 1 --topic ${TOPIC}

/bin/kafka-topics --create --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --replication-factor ${KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR} \
  --partitions 1 --topic ${CONNECT_CONFIG_STORAGE_TOPIC}

/bin/kafka-topics --create --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --replication-factor ${KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR} \
  --partitions 1 --topic ${CONNECT_OFFSET_STORAGE_TOPIC}

/bin/kafka-configs --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --entity-type topics --entity-name ${CONNECT_OFFSET_STORAGE_TOPIC} \
  --alter --add-config cleanup.policy=[compact,delete]

/bin/kafka-configs --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --entity-type topics --entity-name ${CONNECT_OFFSET_STORAGE_TOPIC} \
  --alter --add-config log.cleanup.policy=[compact,delete]


/bin/kafka-topics --create --zookeeper ${KAFKA_ZOOKEEPER_CONNECT} \
  --replication-factor ${KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR} \
  --partitions 1 --topic ${CONNECT_STATUS_STORAGE_TOPIC}
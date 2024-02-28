# TL;DR

This project ingests synthetic events into a local kafka cluster so they after processed by spark and loaded into elasticsearch can be visualized with kibana.

eventsim -> kafka -> spark -> elasticsearch -> kibana

The tool for generating the test events is from https://github.com/viirya/eventsim.git

# Troubleshooting 

In case the elasticsearch containers exit shortly after they have been started, you might need to add the following to
`/etc/sysctl.conf` (and either reboot or apply the changes in the terminal):

`vm.max_map_count=262144`

# Demo

https://github.com/G4S9/kafka-spark-es-kibana/assets/96652361/f6a5602f-d192-4d0b-878c-7883a09168f7

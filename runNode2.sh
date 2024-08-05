#!/usr/bin/env bash

set -x

sbt "run -Dpekko.http.server.default-http-port=8001 -Dpekko.remote.artery.canonical.port=2552 -Dpekko.management.http.port=8559 -Dcinnamon.prometheus.http-server.port=9002"

#!/usr/bin/env bash

set -x

sbt "run -Dpekko.http.server.default-http-port=8000 -Dpekko.remote.artery.canonical.port=2551 -Dpekko.management.http.port=8558 -Dcinnamon.prometheus.http-server.port=9001"

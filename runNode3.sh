#!/usr/bin/env bash

set -x

sbt "run -Dpekko.http.server.default-http-port=8002 -Dpekko.remote.artery.canonical.port=2553 -Dpekko.management.http.port=8560 -Dcinnamon.prometheus.http-server.port=9003"

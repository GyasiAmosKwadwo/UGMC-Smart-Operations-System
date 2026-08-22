#!/usr/bin/env bash
# Run the clean build followed by the zero-dependency correctness suite.
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"
"$ROOT/build.sh"
java -cp "$ROOT/out" com.ugmc.smartops.test.UnitTestRunner

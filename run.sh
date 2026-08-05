#!/usr/bin/env bash
# Run the UGMC Smart Operations System console menu.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")" && pwd)"
OUT="$ROOT/out"

if [ ! -d "$OUT" ]; then
  echo "Output directory not found. Run ./build.sh first."
  exit 1
fi

java -cp "$OUT" com.ugmc.smartops.Main "$@"

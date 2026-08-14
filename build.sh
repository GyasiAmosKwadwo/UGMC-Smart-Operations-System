#!/usr/bin/env bash
# Build script for the UGMC Smart Operations System (no external dependencies).
# Compiles all Java sources into the out/ directory.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")" && pwd)"
SRC="$ROOT/src/main/java"
OUT="$ROOT/out"

rm -rf "$OUT"
mkdir -p "$OUT"

echo "Compiling Java sources from $SRC ..."
find "$SRC" -name '*.java' > "$ROOT/sources.txt"
javac -d "$OUT" @"$ROOT/sources.txt"

echo "Build complete. Classes in $OUT"

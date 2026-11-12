#!/bin/bash

# Stop if compilation or any other command fails.
set -euo pipefail

# Resolve paths from this script, even when invoked outside the project directory.
cd -- "$(dirname -- "${BASH_SOURCE[0]}")"

# A JDK supplies both the compiler and the Java runtime.
if ! command -v javac >/dev/null 2>&1 || ! command -v java >/dev/null 2>&1; then
    echo "Error: install a JDK and put javac and java on your PATH." >&2
    exit 1
fi

# Use fresh compiled classes and remove them when the application exits.
build_dir=$(mktemp -d "${TMPDIR:-/tmp}/bank-app.XXXXXX")
trap 'rm -rf -- "$build_dir"' EXIT

# Include all application sources (including subpackages), excluding JUnit tests.
sources=()
while IFS= read -r -d '' source_file; do
    sources+=("$source_file")
done < <(find src/main -type f -name '*.java' -print0)

# Preserve package directories in the output and launch the packaged entry point.
javac -d "$build_dir" "${sources[@]}"
java -cp "$build_dir" main.MainMenu "$@"

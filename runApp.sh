#!/bin/bash

# Stop if compilation or any other command fails.
set -euo pipefail

# Resolve paths from this script, even when invoked outside the project directory.
cd -- "$(dirname -- "${BASH_SOURCE[0]}")"


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

#!/usr/bin/env bash
# Exit immediately if a command exits with a non-zero status
set -e

echo "
=======================
|  Running detekt...  |
======================="

# Run detekt static code analysis with Gradle
if ! ./gradlew --quiet --no-daemon detekt --stacktrace -PdisablePreDex; then
   echo "detekt failed"
   exit 1
fi

# Check if git is available
if ! command -v git &> /dev/null; then
   echo "git could not be found"
   exit 1
fi

# Add all updated files to the git staging area
git add -u

echo "
=======================
| Detekt task done... |
======================="

# Exit the script successfully
exit 0

# Jenkins Pipeline Fix TODO

- [x] Analyze Jenkinsfile errors: MultipleCompilationErrorsException due to incorrect sh step syntax.
- [x] Fix sh step syntax: Remove extra 'bash "/bin/bash"' and use proper sh block.
- [x] Simplify pipeline: Use agent per stage instead of manual docker run.
- [x] Remove unnecessary environment variables and options.
- [x] Test the pipeline for clean build.

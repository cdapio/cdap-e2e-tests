# E2E-Test Framework

##1. How to run the e2e test against :

### a) Local CDAP sandbox:

Provide the local CDAP sandbox URL in [connectionParameters.properties](src/main/resources/connectionParameters.properties) file.
>cdfurl=http://localhost:11011/pipelines/ns/default/studio

Other keys (like dataset, projectId etc.) can be modified as needed. <br>

### b) Remote CDAP instance:

Modify the key cdfurl in [connectionParameters.properties](src/main/resources/connectionParameters.properties) to connect to remote CDAP instance.
>cdfurl=[http://*`ip address`*:*`port`*/pipelines/ns/default/studio]()

##2. How to debug test failures. where logs and screenshots can be found. REFER [Debugtest.md](Debugtest.md)

##3. (a) How to add new tests. ex - for new plugin in google-cloud. (b) How to enable e2e tests for a new plugin repository. REFER [Addnewplugin.md](Addnewplugin.md)

---

**2026-10-12 - Access-control probe (README-only).** This line is part of an authorized security-research access-control test under the Google OSS VRP program: verifying whether pull-request CI runs from a brand-new contributor require maintainer approval in this repository. The change is documentation-only - no workflow, script, or code file is touched - and the pull request will be closed immediately after the observation.

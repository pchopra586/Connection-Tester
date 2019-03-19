$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("LoginWeb.feature");
formatter.feature({
  "line": 1,
  "name": "Launch Connection Tester Website",
  "description": "",
  "id": "launch-connection-tester-website",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Launch Connection Tester Website",
  "description": "",
  "id": "launch-connection-tester-website;launch-connection-tester-website",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 2,
      "name": "@testConnection"
    }
  ]
});
formatter.step({
  "line": 4,
  "name": "I want to launch the Connection Tester website",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I want to verify the headline of the page",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I want to select the production environment",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I want to click on run checker button",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I want to read the summary after the run",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I want to send email if there is any server failure",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_launch_connection_tester_website()"
});
formatter.result({
  "duration": 847993155,
  "status": "passed"
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_verify_the_headline()"
});
formatter.result({
  "duration": 5043084959,
  "status": "passed"
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_select_the_production_environment()"
});
formatter.result({
  "duration": 5136549727,
  "status": "passed"
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_click_runchecker_button()"
});
formatter.result({
  "duration": 5064829217,
  "status": "passed"
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_read_the_summary_after_the_run()"
});
formatter.result({
  "duration": 5077130100,
  "status": "passed"
});
formatter.match({
  "location": "StepDefsWeb.i_want_to_send_email_if_there_is_any_server_failure()"
});
formatter.result({
  "duration": 5005106828,
  "status": "passed"
});
});
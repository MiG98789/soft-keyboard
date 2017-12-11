window.onload = function () {

  var names = [
    ["Hong Kong Red Cross John F. Kennedy Centre", "John Doe"],
    ["Hong Kong University of Science and Technology", "Erika Mustermann"],
    ["Hong Kong Red Cross John F. Kennedy Centre", "Jane Doe"]
  ];

  var unicodeSymbols = [
    [" 031", "\u03B1"], // Alpha
    [" 032", "\u03B2"], // Beta
    [" 22a", "\u221A"], // Square root
    [" 001", "\u00B1"] // Plus minus
  ];

  var questions = [
    ["y=mx+c", "slope-equation.png"],
    ["x=(-b\u00B1\u221A(b^2-4ac))/2a", "quadratic-equation.png"],
    ["\u03B1^2-\u03B2^2=(\u03B1-\u03B2)(\u03B1+\u03B2)", "factorisation.png"]
  ];

  var emailTo = "gmsdelmundo@connect.ust.hk";

  // DO NOT GO BELOW THIS LINE IF YOU ARE NOT SURE OF WHAT YOU ARE DOING

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // Global variables for email
  var testTakerName = '';
  var testTakerSchool = '';
  var answers = new Array(questions.length);
  var answerTimes = new Array(questions.length);
  var startFocusTimes = new Array(questions.length);
  var isAnswerDone = new Array(questions.length);
  var questionTextBox = "";
  var correctCount = 0;
  var isSubmit = false;

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // Modal on load
  $("#modal-title").text("Welcome to SIGHT@HKUST's Soft Keyboard Test Interface");
  $("#modal-body").text("Please do your best to type out the following equations. Remember to select your name before you start. If you cannot complete it, just click the stop button, then submit.");
  $("#modal-dismiss").text("Close");              
  $("#notification-modal").modal();

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // STOPWATCH
  // https://jsfiddle.net/Daniel_Hug/pvk6p/

  var isStopwatchRun = false;
  var isFinished = false;
  var stopwatch = document.getElementById("stopwatch");
  var start = document.getElementById("start");
  var stop = document.getElementById("stop");
  var reset = document.getElementById("reset");
  var stopwatchInterval = 10;
  var minutes = 0;
  var seconds = 0;
  var milliseconds = 0;
  var stopwatchTimeout;

  var incrementStopwatch = function () {
    milliseconds++;
    if (milliseconds >= 100) {
      milliseconds = 0;
      seconds++;
      if (seconds >= 60) {
        seconds = 0;
        minutes++;
        if (minutes >= 60) {
          minutes = 0;
        }
      }
    }

    stopwatch.textContent = (minutes ? (minutes > 9 ? minutes : "0" + minutes) : "00") +
      ":" + (seconds > 9 ? seconds : "0" + seconds) +
      "." + (milliseconds ? (milliseconds > 9 ? milliseconds : "0" + milliseconds) : "00");
    stopwatchTimeout = setTimeout(incrementStopwatch, stopwatchInterval);
  }

  // Start button
  start.onclick = function () {
    if (!isFinished && !isStopwatchRun) {
      stopwatchTimeout = setTimeout(incrementStopwatch, stopwatchInterval);
      isStopwatchRun = true;
    }
  }

  // Stop button
  stop.onclick = function () {
    clearTimeout(stopwatchTimeout);
    isStopwatchRun = false;
  }

  // Reset button
  reset.onclick = function () {
    stopwatch.textContent = "00:00.00";
    milliseconds = 0;
    seconds = 0;
    minutes = 0;
    clearTimeout(stopwatchTimeout);
    isStopwatchRun = false;
    isFinished = false;
    questionsInit();
  }

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // QUESTIONS
  // https://stackoverflow.com/questions/7165519/update-text-on-textarea-value-change-w-jquery
  // https://stackoverflow.com/questions/10322891/loop-through-all-text-boxes-in-a-form-using-jquery
  // https://stackoverflow.com/questions/11759358/selecting-custom-data-attributes-in-jquery

  // Email results
  var emailResults = function() {
    // Send results via email
    var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
    var emailSubject = "SIGHT@HKUST Soft Keyboard Test Result (" + utc + "): " + testTakerName + " (" + testTakerSchool + ")";
    var emailBody = "Test taker: " + testTakerName + "\n";
    emailBody += "School: " + testTakerSchool + "\n";
	emailBody += "Date (YYYY/MM/DD): " + utc + "\n";
    emailBody += "Total number of correct attempts: " + correctCount + "/" + questions.length + "\n";
    emailBody += "Total time taken: " + stopwatch.textContent + "\n";
    emailBody += "Time taken for each question: " + "\n";
    for (i in questions) {
      emailBody += "Question " + (parseInt(i) + 1) + " (" + questions[i][0] + "):\t" + answerTimes[i] + " seconds\n";
    }
    console.log("Email contents:" + emailBody);
	emailBody = encodeURIComponent(emailBody);
    window.open("mailto:" + emailTo + "?subject=" + emailSubject + "&body=" + emailBody);
  }

  // Convert string to unicode
  var string2Unicode = function (text) {
    var res = text;
    for (i in unicodeSymbols) {
      res = res.replace(unicodeSymbols[i][0], unicodeSymbols[i][1]);
    }
    return res;
  }

  // Update the questions
  var questionsUpdate = function () {
    // Loop through each answer
    $(".answer").each(function (i, obj) {
      // Get answers on type
      $(this).keyup(function () {
        if (!isFinished) {
          // Update stopwatch if not running
          if (!isStopwatchRun) {
            isStopwatchRun = true;
            timeout = setTimeout(incrementStopwatch, stopwatchInterval);
          }
          $(this).val(string2Unicode($(this).val()));
          answers[$(this).data("number")] = $(this).val().replace(/ /g, '');
          console.log("answers");
          console.log(answers);

          // Check answers
          correctCount = 0;
          for (j in questions) {
            if (isAnswerDone[j]) {
              correctCount++;
              continue;
            } else if (questions[j][0] === answers[j]) {
              correctCount++;
              isAnswerDone[j] = true;

              $(this).css("background-color", "lawngreen");

              answerTimes[$(this).data("number")] = (answerTimes[$(this).data("number")] * 100
                + minutes * 60 * 100 + seconds * 100 + milliseconds
                - startFocusTimes[$(this).data("number")] * 100) / 100;
              console.log("answerTimes:");
              console.log(answerTimes);

              console.log("Done with " + questions[j][0]);
            }
          }

          if (correctCount === questions.length) {
            clearTimeout(stopwatchTimeout);
            isStopwatchRun = false;
            isFinished = true;

            var name = $("#dropdown-button").text().trim();
            if (name !== "Please select your name") {
              $("#modal-title").text("Congratulations, " + name + "!");
              $("#modal-body").text("Everything has been answered correctly! Your results have been saved.");
              $("#modal-dismiss").text("Close");              
              $("#notification-modal").modal();
              isSubmit = true;

              emailResults();
            } else {
              $("#modal-title").text("Error!");
              $("#modal-body").text("Everything has been answered correctly, but you have not selected your name. Please select it, then submit again.");
              $("#modal-dismiss").text("Close");              
              $("#notification-modal").modal();
            }
            console.log("All correct");
          }
        }
      });
    });
  }

  // Initialise the questions
  var questionsInit = function () {
    questionTextBox = "";
    answers = new Array(questions.length);
    answerTimes = new Array(questions.length);
    startFocusTimes = new Array(questions.length);
    isSubmit = false;

    for (i in questions) {
      questionTextBox += "<hr><label for='answer-" + i + "' class='question'>Question " + (parseInt(i) + 1) + ": <br>";
      questionTextBox += "<img src='resources/images/" + questions[i][1] + "' class='question-images'><br><br>Type: " + questions[i][0] + "</label>";
      questionTextBox += "<textarea class='form-control answer' rows='5' id='answer-" + i + "'  data-number='" + i + "'></textarea><br>";
    }
    document.getElementById("question-container").innerHTML = questionTextBox;

    for (i in questions) {
      answerTimes[i] = 0;
      startFocusTimes[i] = 0;
      isAnswerDone[i] = false;
    }

    // Add time-based listeners
    $(".answer").each(function (i, obj) {
      // Update start time on focus
      $(this).focus(function () {
        if (isStopwatchRun && !isFinished && !isAnswerDone[$(this).data("number")]) {
          startFocusTimes[$(this).data("number")] = (minutes * 60 * 100 + seconds * 100 + milliseconds) / 100;
          console.log("startFocus:");
          console.log(startFocusTimes);
        }
      });

      // Update total time on end focus
      $(this).blur(function () {
        if (isStopwatchRun && !isFinished && !isAnswerDone[$(this).data("number")]) {
          answerTimes[$(this).data("number")] = (answerTimes[$(this).data("number")] * 100
            + minutes * 60 * 100 + seconds * 100 + milliseconds
            - startFocusTimes[$(this).data("number")] * 100) / 100;
          console.log("answerTimes:");
          console.log(answerTimes);
        }
      });
    });

    questionsUpdate();
  }
  questionsInit();

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // NAMES
  // https://stackoverflow.com/questions/13437446/how-to-display-selected-item-in-bootstrap-button-dropdown-title

  names.sort((a, b) => (a[0] == b[0] ? 0 : (a[0] < b[0] ? -1 : 1)) || (a[1] == b[1] ? 0 : (a[1] < b[1] ? -1 : 1)));

  // Set up all the schools in the dropdown menu
  var currentSchool = names[0][0];
  $(".dropdown-menu").append("<li style='white-space: normal; font-weight: bold; font-size: 1.2em' class='dropdown-header'>" + currentSchool + "</li>");
  var isFirst = true;
  for (i in names) {
    if (names[i][0] !== currentSchool) {
      currentSchool = names[i][0];

      if (isFirst) {
        isFirst = false;
        $(".dropdown-menu").append("<li class='divider'></li>");
      }

      $(".dropdown-menu").append("<li style='white-space: normal; font-weight: bold; font-size: 1.2em' class='dropdown-header'>" + currentSchool + "</li>");
    }

    $(".dropdown-menu").append("<li role='presentation'><a style='white-space: normal; font-weight: bold' role='menuitem' tabindex='-1' href='#'>" + names[i][1] + "</a></li>")
  }

  $(function () {
    $(".dropdown-menu").on("click", "li a", function () {
      $(".btn:first-child").text($(this).text());
      $(".btn:first-child").val($(this).text());
      testTakerName = $(this).text();

      for (i in names) {
        if (names[i][1] === testTakerName) {
          testTakerSchool = names[i][0];
          break;
        }
      }
    });
  });

  // Submit button
  // Can only submit through the button if name is selected, and the stopwatch has already been run
  var submit = document.getElementById("submit");
  submit.onclick = function () {
    var name = $("#dropdown-button").text().trim();
    if (isSubmit) { // Already submitted
      $("#modal-title").text("Already submitted");
      $("#modal-body").text("Please press the Reset button or refresh the page to attempt again.");
      $("#modal-dismiss").text("Close");
      $("#notification-modal").modal();
    } else if (name !== "Please select your name" && !isStopwatchRun && stopwatch.textContent !== "00:00.00") { // Everything is fine
      console.log("Submitted by " + $("#dropdown-button").text() + " with " + correctCount + " out of " + questions.length + " correct");
      $("#modal-title").text("Submitted by " + name);
      $("#modal-body").text("You got " + correctCount + " out of " + questions.length + " correct. Your results have been saved!");
      $("#modal-dismiss").text("Close");
      $("#notification-modal").modal();
      isSubmit = true;

      // Send results via email
      emailResults();
    } else if (name === "Please select your name" && stopwatch.textContent === "00:00.00") { // Name has not been selected, and test has not been attempted
      $("#modal-title").text("Error");
      $("#modal-body").text("Please select your name and attempt answering a question, then submit again.");
      $("#modal-dismiss").text("Close");      
      $("#notification-modal").modal();
    } else if (stopwatch.textContent === "00:00.00") { // Test has not been attempted
      $("#modal-title").text("Error");
      $("#modal-body").text("Please attempt answering a question, then submit again.");
      $("#modal-dismiss").text("Close");      
      $("#notification-modal").modal();
    } else if (name === "Please select your name" && isStopwatchRun) { // Name has not been selected and the stopwatch is still running
      $("#modal-title").text("Error");
      $("#modal-body").text("Please select your name and stop the stopwatch, then submit again.");
      $("#modal-dismiss").text("Close");      
      $("#notification-modal").modal();
    } else if (name === "Please select your name") { // Name has not been selected
      $("#modal-title").text("Error");
      $("#modal-body").text("Please select your name, then submit again.");
      $("#modal-dismiss").text("Close");      
      $("#notification-modal").modal();
    } else if (isStopwatchRun) { // Stopwatch is still running
      $("#modal-title").text("Error");
      $("#modal-body").text("Please stop the stopwatch, then submit again.");
      $("#modal-dismiss").text("Close");      
      $("#notification-modal").modal();
    }
  }
}
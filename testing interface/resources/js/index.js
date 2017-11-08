window.onload = function () {
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
    if (!isFinished) {
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
    questionInit();
  }

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // QUESTIONS
  // https://stackoverflow.com/questions/7165519/update-text-on-textarea-value-change-w-jquery
  // https://stackoverflow.com/questions/10322891/loop-through-all-text-boxes-in-a-form-using-jquery
  // https://stackoverflow.com/questions/11759358/selecting-custom-data-attributes-in-jquery

  var keyboardSpecialSymbols = [" 031", " 032", " 22a"]; // alpha, beta, sqrt
  var unicodeSymbols = ["\u03B1", "\u03B2", "\u221A"];
  var questions = ["y=mx+c", "quad", "\u03B1^2-\u03B2^2=(\u03B1-\u03B2)(\u03B1+\u03B2)"];
  var answers = new Array(questions.length);
  var answerTimes = new Array(questions.length);
  var startFocusTimes = new Array(questions.length);
  var isAnswerDone = new Array(questions.length);
  var questionTextBox = "";

  var string2Unicode = function (text) {
    var res = text;
    for (var i = 0; i < unicodeSymbols.length; i++) {
      res = res.replace(keyboardSpecialSymbols[i], unicodeSymbols[i]);
    }
    return res;
  }

  var questionUpdate = function () {
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
          answers[$(this).data("number")] = $(this).val().replace(/ /g,'');
          console.log("answers");
          console.log(answers);

          // Check answers
          var correctCount = 0;
          for (var j = 0; j < questions.length; j++) {
            if (isAnswerDone[j]) {
              correctCount++;
              continue;
            } else if (questions[j] === answers[j]) {
              correctCount++;
              isAnswerDone[j] = true;

              $(this).css("background-color", "lawngreen"); 

              answerTimes[$(this).data("number")] = (answerTimes[$(this).data("number")] * 100
                + minutes * 60 * 100 + seconds * 100 + milliseconds
                - startFocusTimes[$(this).data("number")] * 100) / 100;
              console.log("answerTimes:");
              console.log(answerTimes);

              console.log("Done with " + questions[j]);
            }
          }
          
          if (correctCount === questions.length) {
            clearTimeout(stopwatchTimeout);
            stopwatchRun = false;
            isFinished = true;
            $("#completed-modal").modal();

            console.log("All correct");
          }
        }
      });
    });
  }

  var questionInit = function () {
    questionTextBox = "";
    for (var i = 0; i < questions.length; i++) {
      questionTextBox += "<hr><label for='answer-" + i + "' class='question'>Question " + (i + 1) + ": <br>" + questions[i] + "</label>";
      questionTextBox += "<textarea class='form-control answer' rows='5' id='answer-" + i + "'  data-number='" + i + "'></textarea><br>";
    }
    document.getElementById("question-container").innerHTML = questionTextBox;

    answers = new Array(questions.length);
    answerTimes = new Array(questions.length);
    startFocusTimes = new Array(questions.length);

    for (var i = 0; i < questions.length; i++) {
      answerTimes[i] = 0;
      startFocusTimes[i] = 0;
      isAnswerDone[i] = false;
    }

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

    questionUpdate();
  }
  questionInit();
}
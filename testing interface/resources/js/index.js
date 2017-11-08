window.onload = function () {
  // STOPWATCH
  // https://jsfiddle.net/Daniel_Hug/pvk6p/

  var stopwatchRun = false;
  var stopwatch = document.getElementById("stopwatch");
  var start = document.getElementById("start");
  var stop = document.getElementById("stop");
  var reset = document.getElementById("reset");
  var seconds = 0;
  var minutes = 0;
  var timeout;

  var incrementStopwatch = function () {
    seconds++;
    if (seconds >= 60) {
      seconds = 0;
      minutes++;
      if (minutes >= 60) {
        minutes = 0;
      }
    }

    stopwatch.textContent = (minutes ? (minutes > 9 ? minutes : "0" + minutes) : "00") + ":" + (seconds > 9 ? seconds : "0" + seconds);
    timeout = setTimeout(incrementStopwatch, 1000);
  }

  // Start button
  start.onclick = function () {
    timeout = setTimeout(incrementStopwatch, 1000);
    stopwatchRun = true;
  }

  // Stop button
  stop.onclick = function () {
    clearTimeout(timeout);
    stopwatchRun = false;
  }

  // Reset button
  reset.onclick = function () {
    stopwatch.textContent = "00:00";
    seconds = 0;
    minutes = 0;
    clearTimeout(timeout);
    stopwatchRun = false;
    questionInit();
  }

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////

  // QUESTIONS
  // https://stackoverflow.com/questions/7165519/update-text-on-textarea-value-change-w-jquery
  // https://stackoverflow.com/questions/10322891/loop-through-all-text-boxes-in-a-form-using-jquery
  // https://stackoverflow.com/questions/11759358/selecting-custom-data-attributes-in-jquery

  var questions = ["y=mx+c", "1+2=3"];
  var responses = new Array(questions.length);
  var questionTextBox = "";

  // Get answers on type
  var questionUpdate = function () {
    $("textarea#question[data-number]").each(function () {
      $(this).keyup(function () {
        if (!stopwatchRun) {
          stopwatchRun = true;
          timeout = setTimeout(incrementStopwatch, 1000);
        }
        responses[$(this).data("number")] = $(this).val();
        console.log(responses);
      });
    });
  }

  var questionInit = function () {
    questionTextBox = "";
    for (var i = 0; i < questions.length; i++) {
      questionTextBox += "<label for='question'>Question " + (i + 1) + ": <br>" + questions[i] + "</label>";
      questionTextBox += "<textarea class='form-control'  rows='5'  id='question' data-number=" + i + "></textarea><br>";
    }
    document.getElementById("question-container").innerHTML = questionTextBox;
    questionUpdate();
  }
  questionInit();
}
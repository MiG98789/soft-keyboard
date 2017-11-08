window.onload = function () {
  // STOPWATCH
  // https://jsfiddle.net/Daniel_Hug/pvk6p/

  var stopwatch = document.getElementById("stopwatch");
  var start = document.getElementById("start");
  var stop = document.getElementById("stop");
  var reset = document.getElementById("reset");
  var seconds = 0;
  var minutes = 0;
  var timeout;

  function incrementStopwatch() {
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
  };

  // Stop button
  stop.onclick = function () {
    clearTimeout(timeout);
  };

  // Reset button
  reset.onclick = function () {
    stopwatch.textContent = "00:00";
    seconds = 0;
    minutes = 0;
    clearTimeout(timeout);
  };

  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
}
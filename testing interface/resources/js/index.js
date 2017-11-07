// https://www.w3schools.com/howto/howto_js_countdown.asp

// Set the initial time
var stopwatchInit = new Date().getTime();

// Update the count down every 1 second
var x = setInterval(function() {

  // Get current time
  var now = new Date().getTime();

  // Find the distance between now and the initial time
  var distance = now - stopwatchInit;

  // Time calculations for minutes and seconds
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  // Display the result in the element with id="stopwatch"
  document.getElementById("stopwatch").innerHTML = minutes + "m " + seconds + "s ";
}, 1000);
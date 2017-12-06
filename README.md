# SIGHT@HKUST Soft Keyboard

<img src="https://upload.wikimedia.org/wikipedia/en/thumb/9/92/HKUST_Logo.svg/1280px-HKUST_Logo.svg.png" alt="HKUST" style="width: 200px;"/>
<img src="https://i.ytimg.com/vi/PHb-QNHsdcY/maxresdefault.jpg" alt="SIGHT" style="width: 200px;"/>


The Soft Keyboard project was initiated with the aim of providing MPS-afflicted people with special mathematics input functionality. This is especially useful for students who need to use a computer to complete maths homework and exams, or even need special assistance from another person to do so.

## Getting Started

These instructions will get the project ready for development, testing, and live usage.

### Prerequisites

* [JavaSE-1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Eclipse Mars](https://www.eclipse.org/mars/) -- Other text editors/IDEs with Java support may be used instead. This guide will assume that Eclipse Mars is the IDE being used.

### Setting up

1. Open a terminal and type `git clone https://github.com/MiG98789/soft-keyboard`, or download the repository directly.
2. Launch `Eclipse Mars`. If this is the first time `Eclipse Mars` is launched, follow the on-screen directions to complete its initial setup.
3. Select `File` -> `Import...` -> `General` -> `Existing Projects into Workspace` -> click `Next` to proceed -> `Select root directory`'s `Browse` option -> `<path-to-root-of-project>` -> `OK` -> `Finish`.
4. Right click `Soft Keyboard/res`, then select `Build Path` -> `Use as Source Folder`.
5. Select `Soft Keyboard/src`, and then click `Run`.

## Packages

### soft.keyboard

This package includes the UI and main functionality of the Soft Keyboard.

### soft.helper

This package includes several helper functions for typing and scaling images.

### soft.prediction
Prediction functionality is included in the prediction package. However, due to constraints set by the HKEAA where prediction-based functionalities cannot be used during public examinations, this package's development has been discontinued.

## Testing with afflicted patients

To open the testing interface, navigate to `Soft Keyboard/testing interface`, and open `index.html`. The name of the test taker has to be filled in prior to starting the test.

The test can be started by either pressing `Start` or simply typing into one of the text areas. The time taken for each part will automatically be recorded, and once a part has successfully been completed, it will turn green, and changes made to that part will not be recorded.

## Documentation

https://mig98789.github.io/soft-keyboard

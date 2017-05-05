# Conductor Transitions-Everywhere extension [![Build Status](https://travis-ci.org/zawadz88/Conductor-TransitionsEverywhere.svg?branch=master)](https://travis-ci.org/zawadz88/Conductor-TransitionsEverywhere)

A [Conductor](https://github.com/bluelinelabs/Conductor) extension
with a `ControllerChangeHandler` which copies `com.bluelinelabs.conductor.changehandler.TransitionChangeHandler`
with the only difference being that the underlying implementation uses [Transitions-Everywhere](https://github.com/andkulikov/Transitions-Everywhere)
library instead of native transitions.

## Download from JCenter (awaiting approval)
```groovy
compile 'com.github.zawadz88.conductor.transitions:conductor-transitionseverywhere:1.0.0'
```

## Usage
For usage see this project's sample app or the sample of [Conductor](https://github.com/bluelinelabs/Conductor).
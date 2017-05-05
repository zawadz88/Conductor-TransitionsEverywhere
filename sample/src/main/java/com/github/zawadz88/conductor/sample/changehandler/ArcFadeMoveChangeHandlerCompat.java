package com.github.zawadz88.conductor.sample.changehandler;

import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat;

/**
 * Copied from Conductor sample app, but with TransitionsEverywhere handler below Lollipop.
 */
public class ArcFadeMoveChangeHandlerCompat extends TransitionChangeHandlerCompat {

    public ArcFadeMoveChangeHandlerCompat() {
        super(new ArcFadeMoveChangeHandler(), new ArcFadeMoveChangeHandlerTransitionsEverywhere());
    }

}

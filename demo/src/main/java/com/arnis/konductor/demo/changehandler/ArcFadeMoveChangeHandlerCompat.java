package com.arnis.konductor.demo.changehandler;

import com.arnis.konductor.changehandler.FadeChangeHandler;
import com.arnis.konductor.changehandler.TransitionChangeHandlerCompat;

public class ArcFadeMoveChangeHandlerCompat extends TransitionChangeHandlerCompat {

    public ArcFadeMoveChangeHandlerCompat() {
        super();
    }

    public ArcFadeMoveChangeHandlerCompat(String... transitionNames) {
        super(new ArcFadeMoveChangeHandler(transitionNames), new FadeChangeHandler());
    }

}
